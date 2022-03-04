package logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import datos.Conexion;
import entidades.Noche;

public class Noches {

	public static LinkedList<Noche> habilitadasPara(int clienteID){
		LinkedList<Noche> habilitadas=new LinkedList<Noche>();
		ResultSet rs = null;
		try 
		{
			Conexion cn = new Conexion();
			String query="select n.ID,n.fecha"
					+ " from noche n"
					+ " LEFT join acceso ac on"
					+ " ac.nocheID = n.ID"
					+ " AND ac.estadoID=1"
					+ " AND n.inscripcion = 1"
					+ " AND ac.clienteID="+clienteID
					+ " WHERE ac.clienteID IS NULL"
					+ " AND n.fecha>=CURDATE()"
					+ " ORDER BY n.fecha DESC";
			rs=cn.executeSelect(query);
			
		 		while(rs.next())
		 		{
		 			Noche u = new Noche(rs.getInt(1),rs.getDate(2),true);
		 			habilitadas.add(u);
		 		}
		}
		catch(Exception e){System.out.println(e.getMessage());}
	 
	      return habilitadas;
	}

	public static RespuestaHttp eliminar(int nocheID) {
		try {
			new Noche(nocheID).eliminar();
			return new RespuestaHttp();
		} catch (Exception e) {
			e.printStackTrace();
			return new RespuestaHttp(500,e.getLocalizedMessage());
		}
	}

	public static RespuestaHttp agregar(String fecha, boolean inscripcionesAbiertas) {
		try {
			var n=new Noche(new SimpleDateFormat("yyyy-MM-dd").parse(fecha),inscripcionesAbiertas);
			return new RespuestaHttp(200,""+n.getID());
		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaHttp(400,"La fecha ya existe.");
		} catch (ParseException e) {
			return new RespuestaHttp(400,"Fecha inválida.");
		}
	}

	public static RespuestaHttp editar(int ID, String fecha, boolean inscripcionesAbiertas) {
		try {
			var n=new Noche(ID);
			n.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
			n.setInscripcionAbierta(inscripcionesAbiertas);
			return new RespuestaHttp();
		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaHttp(400,"La fecha ya existe.");
		} catch (ParseException e) {
			e.printStackTrace();
			return new RespuestaHttp(400,"Fecha inválida.");
		}
	}
}
