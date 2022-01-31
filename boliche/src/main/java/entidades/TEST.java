package entidades;
import entidades.Usuario;
import datos.*;
import datos.PSParameter.Types;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.*;
public class TEST {

	private  String nombre;
	private String contraseña;
	private String nickname;
	private String mail;
	private int rol;
	private boolean verificado;
	private int ID;
	
	

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public boolean isVerificado() {
		return verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;}
	
public Usuario getUsuarioData(String usU, String contra) {

	
	try {
		
		String nombre = null ;
	  	   String contraseña = null ;
	  	   String nickname = null; 
	  	   int rol = 0;
	  	   String email = null;
	  	   boolean verificado = false; 
		
		     Conexion conn=new Conexion();
		           var rs = conn.preparedSelectStatement
		            		(
				          "select usuario, contraseña, nickname, rolID, correo, verificado, secreto  from usuarios  where usuario = ?  ;"
				                  , new PSParameter[] {
						                new PSParameter(usU,Types.STRING)
			                                           }
		                     );
		
		           
		       while(rs.next()) 
		     {  
		    	    nombre = rs.getString(1);
		    	    contraseña =rs.getString(2);
		    	    nickname =rs.getString(3);
		    	    rol= rs.getInt(4);
		    	    email= rs.getString(5);
		    	    verificado = rs.getBoolean(6);
			
		
		     }
                                   ;
		       Usuario usuario = new Usuario(0,nombre,contraseña,nickname,email,verificado,rol);
		       return usuario;
		
	}	
	
	catch(SQLException e) {System.out.print(e+"error "); return null;}
	
	catch(Exception ex ) {System.out.print(ex+"error ");return null;} 
	
	
}

		    
			


	


public static void main(String[] args) 
{
	try {
TEST	t  = new TEST();
Usuario uss = new Usuario();
uss= t.getUsuarioData( "k", "con");
if(uss!=null) {
System.out.println(uss.isVerificado());
        }
	}
	catch(Exception e) {System.out.println(e+"eee");}
}



}
