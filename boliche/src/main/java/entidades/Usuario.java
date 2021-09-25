package entidades;
import datos.Conexion1;

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

	@SuppressWarnings("null")
	public String getByUsuarios(String usu ) {//TODO cambiar por prepare statment 
		
		Connection cn = null;
		ResultSet rs = null;
		 String urios = null;
		PreparedStatement s= null;
		
		try {
			Conexion1.conectar();
			s= cn.prepareStatement("select usuario  from usuarios  where usuario = "+usu+"  ;");
			s.executeQuery();
			while(rs.next()) 
			{  urios = rs.getString(1); }
					
		     }
			
		catch(SQLException e) {System.out.print(e); return "-1";}
		
		catch(Exception ex ) {System.out.print(ex);return "-1";} 
		
		return urios;
		
	}
	
	
	@SuppressWarnings("null")
	public  String GETcontrasena(String pass ) {
		Conexion conexio = new Conexion();
		Connection cn = null;
		ResultSet rs = null;
		PreparedStatement s= null;
		 String password = null;
		 
		try {
			Conexion1.conectar();
			s= cn.prepareStatement("select usuario  from usuarios  where contraseña = "+pass+"  ;");
			s.executeQuery();
			while(rs.next()) 
			{  password = rs.getString(1); }
			
		}
		
		catch(SQLException e) {System.out.print(e); return "-1";}
		
		catch(Exception ex) {System.out.print(ex); return "-1";}
		
		return password;
	}
	
	@SuppressWarnings({ "unused", "null" })
	public int verifica_Usuario(String con , String usua)  
	{	
		Conexion1.conectar();
		Connection cn = null;
		ResultSet rs = null;
		PreparedStatement ps= null;
		
		 String usuarios =null ;
		 String contra = null;
		 int rol = 0;
		 int result;
	
	try {
			Conexion1.conectar();
			ps= cn.prepareStatement("select usuario, contraseña ,rol from usuarios  where contraseña = "+con+"and usuario ="+usua+"   ;");
			ps.executeQuery();
			while(rs.next()) 
			{  usuarios = rs.getString(1);
			    contra = rs.getString(2);
			    rol= rs.getInt(3);
			    
			}
		}
			catch(SQLException ex) {  result=-1;}
			catch(Exception e) {result = -1;}
			
			return rol;
			
		
		
		
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
/*	public Usuario getCreador() {
		// Conectar a la base de datos y traer el creador, si hay uno.
		return null;
	}*/
}
