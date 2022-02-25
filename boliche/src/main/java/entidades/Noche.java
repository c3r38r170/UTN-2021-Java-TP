package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import datos.Conexion;
import datos.PSParameter;
import datos.PSParameter.Types;
@SuppressWarnings("unused")

public class Noche {

	public int getId() {
		return id;
	}


	public Date getFecha() {
		return fecha;
	}


	public boolean isInscripciones() {
		return inscripciones;
	}

	int id;
	Date fecha;
	boolean inscripciones;

	public Noche(int id, Date fecha, boolean inscripciones) {

		this.id = id;
		this.fecha = fecha;
		this.inscripciones = inscripciones;
	}
	
	
	public Noche() {}

		
	

	public void Agregar(Date fecha, boolean estado) {

		var con = new Conexion();

		try {
			int columnsafected = con.preparedStatement("INSERT INTO noche (fecha,inscripcion) value(fecha,estado) ;  ",
					new PSParameter[] { new PSParameter(fecha, Types.DATE), new PSParameter(estado, Types.BOOLEAN) });
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public int Editar(int id, Date fecha, boolean estado) {
		int columns = 0;
		var conn = new Conexion();
		try {

			columns = conn.preparedStatement("Update noche set fecha=?  , inscripcion = ? where id = ?    ;",
					new PSParameter[] { new PSParameter(fecha, Types.DATE), new PSParameter(estado, Types.BOOLEAN),
							new PSParameter(id, Types.INT)

					});
			return columns;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return columns;

	}

	public static LinkedList<Noche> listar() {

		LinkedList<Noche> listFiesta = new LinkedList<Noche>();
		try {

			ResultSet rs = null;
			Conexion cn = new Conexion();
			rs = cn.executeSelect("select * from noche");

			while (rs.next()) {
				int id = rs.getInt(1);
				Date fecha = rs.getDate(2);
				boolean inscripciones = rs.getBoolean(3);
				Noche f = new Noche(id, fecha, inscripciones);
				listFiesta.add(f);
				
			}
			if(listFiesta== null) {System.out.println("es nulo");}
			return listFiesta;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listFiesta;

	}

	public void eliminar(int id) {

		ResultSet rs = null;
		Conexion conn = new Conexion();
		try {

			@SuppressWarnings("unused")
			int columns = conn.preparedStatement("Update noche set inscripcion= 0 where ID = ?    ;",
					new PSParameter[] { new PSParameter(id, Types.INT)

					});

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	

	public static Noche hoy() {
		Conexion con=new Conexion();
		ResultSet rs;
		Noche hoy=null;
		try {
			rs = con.executeSelect("SELECT * FROM noche WHERE fecha=CURDATE()");
			rs.next();
			hoy=new Noche(rs.getInt(1),rs.getDate(2),rs.getBoolean(3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hoy;
	}

	public int getID() {
		return id;
	}

	public static LinkedList<Noche> habilitadasPara(int clienteID){
		LinkedList<Noche> habilitadas=new LinkedList<Noche>();
		ResultSet rs = null;
		PreparedStatement ps= null;
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
					+ " WHERE ac.clienteID IS NULL";
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

}




