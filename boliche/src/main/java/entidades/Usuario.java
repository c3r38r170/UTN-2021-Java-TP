package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datos.Conexion;

public class Usuario {
	private String nombre;
	private String contraseña;
	private String nickname;
	private String mail;
	private boolean verificado;
	
	public Usuario(String nombre, String contraseña, String nickname, String mail, boolean verificado) {
		super();
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.nickname = nickname;
		this.mail= mail;
		this.verificado = verificado;
	}
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public  static  String getByUsuarios(String usu ) {//TODO cambiar por prepare statment 
		Conexion conexio = new Conexion();
		Connection cn = null;
		ResultSet rs = null;
		 String urio;
		PreparedStatement s= null;
		
		try {
			conexio.conectar();
			s= cn.prepareStatement("select usuario  from usuarios  where usuario = "+usu+"  ;");
			while(rs.next()) 
			{ String urio = rs.getString(1); }
					
		     }
		
		catch(SQLException e) {System.out.print(e);}
		return urio;//crea nuevo usuario y devolverlo
	}
	
	
	public  String GETcontrasena(String pass ) {
		Conexion conexio = new Conexion();
		Connection cn = null;
		ResultSet rs = null;
		PreparedStatement s= null;
		 String password;
		//TODO cambiar por prepare statment 
		try {
			conexio.conectar();
			s= cn.prepareStatement("select usuario  from usuarios  where contrasena = "+pass+"  ;");
			s.executeUpdate();
			while(rs.next()) 
			{  password = rs.getString(1); }
			
		}
		
		catch(SQLException e) {System.out.print(e);}
		return password;
	}
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Usuario getCreador() {
		// Conectar a la base de datos y traer el creador, si hay uno.
		return null;
	}
}
