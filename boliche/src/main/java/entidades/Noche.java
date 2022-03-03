package entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import datos.Conexion;
import datos.PSParameter;
import datos.PSParameter.Types;

public class Noche {

 
	public static  int Editar(int id, Date fecha, boolean estado) {
		int columns = 0;
		var conn = new Conexion();
		try {

			columns = conn.preparedStatement("Update noche set fecha=?  , inscripcion = ? where id = ?    ;",
					new PSParameter[] { new PSParameter( new java.sql.Date(fecha.getTime()), Types.DATE), new PSParameter(estado, Types.BOOLEAN),
							new PSParameter(id, Types.INT)

					});
			return columns;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return columns;

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
			
			e.printStackTrace();
		}
		return hoy;
	}
	public static LinkedList<Noche> listar() {

		LinkedList<Noche> listFiesta = new LinkedList<Noche>();
		try {

			ResultSet rs = null;
			Conexion cn = new Conexion();
			rs = cn.executeSelect("select * from noche ORDER BY fecha DESC");

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
	private int id;

	private Date fecha;
	
	
	private boolean inscripcionAbierta;

		
	

	public Noche() {}


	public Noche(int ID) throws SQLException {
		this.id=ID;
		Conexion con=new Conexion();
			ResultSet rs = con.executeSelect("SELECT * FROM noche where ID="+ID);
			rs.next();
			this.fecha=rs.getDate("fecha");
			this.inscripcionAbierta=rs.getBoolean("inscripcion");
	}

	public Noche(int id,Date fecha, boolean inscripciones) {

		this.id = id;
		this.fecha = fecha;
		this.inscripcionAbierta = inscripciones;
	}

	public Noche(Date fecha, boolean inscripcionAbierta) throws SQLException {
		this.inscripcionAbierta=inscripcionAbierta;
		this.fecha=fecha;
		
		var con = new Conexion();

		con.preparedStatement("INSERT INTO noche (fecha,inscripcion) value(?,?) ;  ",
				new PSParameter[] { new PSParameter( new java.sql.Date(fecha.getTime()), Types.DATE), new PSParameter(inscripcionAbierta, Types.BOOLEAN) });

		this.id=con.lastInsertID();
	}
	
	public void eliminar() throws SQLException {

		ResultSet rs = null;
		Conexion conn = new Conexion();

			int columns = conn.preparedStatement("Delete from noche where ID = ?;",
					new PSParameter[] { new PSParameter(this.id, Types.INT)

					});

	}

	public Date getFecha() {
		return fecha;
	}
	
	public int getID() {
		return id;
	}

	public boolean isInscripciones() {
		return inscripcionAbierta;
	}


	public void setFecha(Date fechaNueva) throws SQLException {
		if(fechaNueva.equals(fecha))
			return;
		
		new Conexion().preparedStatement("Update noche set fecha=? where id = ?    ;",
				new PSParameter[] { new PSParameter( new java.sql.Date(fechaNueva.getTime()), Types.DATE),
						new PSParameter(this.id, Types.INT)

				});
	}


	public void setInscripcionAbierta(boolean inscripcionAbierta) throws SQLException {
		if(inscripcionAbierta==this.inscripcionAbierta)
			return;
		
		new Conexion().preparedStatement("Update noche set inscripcion = ? where id = ?    ;",
				new PSParameter[] { new PSParameter(inscripcionAbierta, Types.BOOLEAN),
						new PSParameter(this.id, Types.INT)

				});
	}

}




