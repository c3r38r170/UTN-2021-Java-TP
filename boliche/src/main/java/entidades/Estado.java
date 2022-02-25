package entidades;

import java.sql.ResultSet;
import java.util.LinkedList;

import datos.Conexion;

public class Estado {

	public int getID() {
		return ID;
	}

	public String getColor() {
		return color;
	}

	public String getDescripcion() {
		return descripcion;
	}

	int ID;
	String descripcion;
	String color;
	
	public Estado(int iD, String descripcion, String color) {
		super();
		ID = iD;
		this.descripcion = descripcion;
		this.color = color;
	}
	
	public static LinkedList<Estado> listar(){

		LinkedList<Estado> estados = new LinkedList<>();
		try {

			ResultSet rs = null;
			Conexion cn = new Conexion();
			rs = cn.executeSelect("select * from estado");

			while (rs.next()) {
				estados.add(new Estado(rs.getInt(1), rs.getString(2),  rs.getString(3)));
				
			}
			return estados;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return estados;

	}
	
}
