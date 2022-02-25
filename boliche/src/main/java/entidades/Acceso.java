package entidades;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Instant;
import java.util.LinkedList;

import datos.Conexion;
import datos.PSParameter;

public class Acceso {

	private int ID;
	private int clienteID;
	private Noche noche;
	public Noche getNoche() {
		return noche;
	}


	private int seguridadID;
	private String comentario;
	public String getComentario() {
		return comentario;
	}


	private int estadoID;
	public int getEstadoID() {
		return estadoID;
	}


	private Time hora;
	
	public Time getHora() {
		return hora;
	}

	public Acceso(int ID) {
		this.ID=ID;
		Conexion con=new Conexion();
		try {
			ResultSet rs = con.executeSelect(
					"SELECT *"
					+ " FROM acceso ac"
					+ " LEFT JOIN comentario com ON ac.comentarioID = com.ID"
					+ " WHERE ac.ID="+ID);
			rs.next();
			//TODO constructor para esto
			this.clienteID=rs.getInt("clienteID");
			this.noche=new Noche(rs.getInt("nocheID"));
			this.seguridadID=rs.getInt("seguridadID");
			this.comentario=rs.getString("comentario");
			this.estadoID=rs.getInt("estadoID");
			this.hora=rs.getTime("hora");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Acceso(int clienteID, int nocheID) {
		this.clienteID=clienteID;
		this.noche=new Noche(nocheID);
		Time t=new Time(Instant.now().toEpochMilli());
		this.hora=t;
		
		Conexion con=new Conexion();
		try {
			con.preparedStatement("INSERT INTO acceso (clienteID,nocheID,hora) VALUES ("+clienteID+","+nocheID+",?)",new PSParameter(t));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int getID() {
		return ID;
	}
	
	
	public static LinkedList<Acceso> historialDe(int clienteID){

		LinkedList<Acceso> historial=new LinkedList<Acceso>();
		ResultSet rs = null;
		PreparedStatement ps= null;
		try 
		{
			Conexion cn = new Conexion();
			String query="select ac.ID"
					+ " from acceso ac"
					+ " INNER JOIN noche nox ON ac.nocheID = nox.ID"
					+ " WHERE ac.clienteID="+clienteID
					+ " ORDER BY nox.fecha DESC, ac.hora DESC";
			rs=cn.executeSelect(query);
			
		 		while(rs.next())
		 		{
		 			Acceso a = new Acceso(rs.getInt(1));
		 			historial.add(a);
		 		}
		}
		catch(Exception e){System.out.println(e.getMessage());}
	 
	      return historial;
	}
	
}
