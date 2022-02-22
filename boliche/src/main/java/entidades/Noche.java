package entidades;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import datos.Conexion;

public class Noche {

	int ID;
	Date fecha;
	
	public Noche(int ID, Date date) {
		this.ID=ID;
		fecha=date;
	}

	public static Noche hoy() {
		Conexion con=new Conexion();
		ResultSet rs;
		Noche hoy=null;
		try {
			rs = con.executeSelect("SELECT * FROM noche WHERE fecha=CURDATE()");
			rs.next();
			hoy=new Noche(rs.getInt(1),rs.getDate(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hoy;
	}

	public int getID() {
		return ID;
	}
	
}
