package entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import datos.Conexion;

public class Acceso {

	private int ID;
	private int clienteID;
	private int nocheID;
	private int seguridadID;
	private int comentarioID;
	private int estadoID;
	private Time hora;
	
	public Acceso(int ID) {
		this.ID=ID;
		Conexion con=new Conexion();
		try {
			ResultSet rs = con.executeSelect("SELECT * FROM acceso WHERE ID="+ID);
			rs.next();
			this.clienteID=rs.getInt("clienteID");
			this.nocheID=rs.getInt("nocheID");
			this.seguridadID=rs.getInt("seguridadID");
			this.comentarioID=rs.getInt("comentarioID");
			this.estadoID=rs.getInt("estadoID");
			this.hora=rs.getTime("hora");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Acceso(int clienteID, int nocheID) {
		this.clienteID=clienteID;
		this.nocheID=nocheID;
		
		Conexion con=new Conexion();
		try {
			con.executeQuery("INSERT INTO acceso (clienteID,nocheID) VALUES ("+clienteID+","+nocheID+")");
			ID=con.lastInsertID();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int getID() {
		return ID;
	}
}
