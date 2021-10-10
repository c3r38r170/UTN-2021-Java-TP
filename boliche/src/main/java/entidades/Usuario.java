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
	private int rol;
	private boolean verificado;
	
	
	public Usuario(String nombre, String contraseña, String nickname, String mail, boolean verificado,int rol) {
		super();
		this.nombre = nombre;
		this.rol=rol;
		this.contraseña = contraseña;
		this.nickname = nickname;
		this.mail= mail;
		this.verificado = verificado;
	}
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("null")
	public String getByUsuarios(String usu ) {
		
		Connection cn = null;
		ResultSet rs = null;
		 String urios = null;
		
		
		try {
			Conexion1 con = new Conexion1();
			PreparedStatement s= null;
			cn=con.conectar();
			s= cn.prepareStatement("select usuario  from usuarios  where usuario = ?  ;");
			s.setString(1,usu);
			rs=s.executeQuery();
			while(rs.next()) 
			{  urios = rs.getString(1); }
					
		     }
			
		catch(SQLException e) {System.out.print(e+"error en getusuario"); return "-1";}
		
		catch(Exception ex ) {System.out.print(ex+"error en getuusario");return "-1";} 
		
		return urios;
		
	}
	
	
	@SuppressWarnings("null")
	public  String GETcontrasena(String pass ) {
		
		Connection cn = null;
		ResultSet rs = null;
		PreparedStatement s= null;
		 String password = null;
		 
		try {
			 Conexion1 con = new Conexion1();
			cn = con.conectar();
			s= cn.prepareStatement("select contraseña  from usuarios  where contraseña = ? ;");
			s.setString(1,pass);
			rs=s.executeQuery();
			while(rs.next()) 
			{  password = rs.getString(1); }
			
		}
		
		catch(SQLException e) {System.out.print(e+"error en getcontrasena"); return "-1";}
		
		catch(Exception ex) {System.out.print(ex+"error en getcontrasena"); return "-1";}
		
		return password;
	}
	
	@SuppressWarnings({ "unused", "null" })
	public int verifica_Usuario(String contra , String usua)  
	{	
		
		
		PreparedStatement ps= null;
		
		 String usuarios =null ;
		 String cont = null;
		
		 int result;
	
	try {
		 Conexion1 con = new Conexion1();
		Connection cn = null;
		ResultSet rs = null ;	
		 int rol = 0;
		cn= con.conectar();
			ps= cn.prepareStatement("select rolID from usuarios  where contraseña = ? and usuario = ? ;");
			
			ps.setString(1,contra);
			ps.setString(2,usua);
			rs=ps.executeQuery();
			
			while(rs.next()) 
			{ // usuarios = rs.getString(1);
			    //cont= rs.getString(2);
			    rol= (int)rs.getInt(1);
			    
			    return rol;
			}
			return rol;
		}
			catch(SQLException ex) { System.out.println(ex+"error en verifica usuario"); return -1;}
			catch(Exception e) {System.out.println(e+"error en verifica usuario");return -1;}
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

public Usuario getUsuarioData(String usU, String contra) {
		
		Connection cn = null;
		ResultSet rs = null;
		PreparedStatement ps= null;
		String contras = null, 
				usuario = null,
				nickname = null,
				mail = null;
		int rolID = 0, ID , creadorID ;
		boolean v = false;
		try {
			 Conexion1 con = new Conexion1();
			cn=con.conectar();
			ps= cn.prepareStatement("select  contraseña ,usuario,nickname,rolID,creadorID, ID ,Mails,Verificado from usuarios  where contraseña = ? and usuario = ?  ;");
			rs=ps.executeQuery();
			ps.setString(1,contra);
			ps.setString(2,usU);
			while(rs.next()) 
			{  		contras= rs.getString(1);
			     	usuario= rs.getString(2);
			     	nickname=rs.getString(3);
			     	rolID= rs.getInt(4);
			    	mail=rs.getString(5);
			    	v=rs.getBoolean(6);
			    	
			    	
		   }
	}
			
			catch(Exception e) {System.out.print("error"+e);}
			Usuario usus = new Usuario( usuario,contras,nickname,mail,v,rolID);
		    return usus;
	}
}
