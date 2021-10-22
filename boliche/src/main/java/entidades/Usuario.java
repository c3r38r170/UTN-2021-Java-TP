package entidades;
import datos.Conexion1;
import datos.PSParameter;
import datos.PSParameter.Types;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datos.Conexion;

public class Usuario{
	private  String nombre;
	private String contraseña;
	private String nickname;
	private String mail;
	private int rol;
	private boolean verificado;
	
	

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
	public  static  Usuario checkUsernameExistence(String usu ) {
		
		Connection cn = null;
		ResultSet rs = null;
		 String urios = null;

  	   
		
		
		try {
			
			String nombre = null ;
		  	   String contraseña = null ;
		  	   String nickname = null; 
		  	   int rol = 0;
		  	   String email = null;
		  	   boolean verificado = false; 
			
			     Conexion conn=new Conexion();
			            rs = conn.preparedSelectStatement
			            		(
					          "select usuario, contraseña, nickname, rolID, correo, verificado, secreto  from usuarios  where usuario = ?  ;"
					                  , new PSParameter[] {
							                new PSParameter(usu,Types.STRING)
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
			       Usuario usuario = new Usuario(nombre,contraseña,nickname,email,verificado,rol);
			       return usuario;
			
		}	
		
		catch(SQLException e) {System.out.print(e+"error "); return null;}
		
		catch(Exception ex ) {System.out.print(ex+"error ");return null;} 
		
		
	}
	
	
	
	
	
	
	
	@SuppressWarnings("null")
	public static  String checkPasswordExistence(String pass ) {
		
		Connection cn = null;
		ResultSet rs = null;
		PreparedStatement s= null;
		 String password = null;
		 
		try {
			Conexion conn=new Conexion();
            conn.preparedSelectStatement
            		(
            				"select contraseña  from usuarios  where contraseña = ? ;"
		                  , new PSParameter[] {
				                new PSParameter(pass,Types.STRING)
	                                           }
                     );
            
            while(rs.next()) 
            {  
            	password = rs.getString(1); 
            }
		    
		}
		
		catch(SQLException e) {System.out.print(e+"error en getcontrasena"); return "-1";}
		
		catch(Exception ex) {System.out.print(ex+"error en getcontrasena"); return "-1";}
		
		return password;
	}
	
	
	
	
	
	
	@SuppressWarnings({ "unused", "null" })
	public static int getUserRol(String contra , String usua)  
	{	
		
		
		PreparedStatement ps= null;
		
		 String usuarios =null ;
		 String cont = null;
		 int Rol = 0;
		
		 int result;

		try {
			Conexion conn=new Conexion();
            ResultSet rs = conn.preparedSelectStatement
            		(
            				"select rolID from usuarios  where contraseña = ? and usuario = ? ;"
		                  , new PSParameter[] {
				                new PSParameter(contra,Types.STRING),new PSParameter(usua,Types.STRING)
	                                           }
                     );
		    
		
			while(rs.next()) 
			{ 
			    Rol= (int)rs.getInt(1);
			    
			    return Rol;
			}
			return Rol;
		}
		
			catch(SQLException ex) { System.out.println(ex+"error en verifica usuario"); return 0;}
			catch(Exception e) {System.out.println(e+"error en verifica usuario");return 0;}
	    }
	


	
	
	
	
	
	
	public Usuario getUsuarioData(String usU, String contra) {
		
		Connection cn = null;
		ResultSet rs = null;
		PreparedStatement ps= null;
		
		
		
		try {
			 Conexion1 con = new Conexion1();
			
			cn=con.conectar();
			ps= cn.prepareStatement("select  contraseña ,usuario,nickname,rolID,creadorID, ID ,Mails,Verificado from usuarios  where contraseña = ? and usuario = ?  ;");
			
			ps.setString(1,contra);
			ps.setString(2,usU);
			rs=ps.executeQuery();
			while(rs.next()) 
			{  		contraseña= rs.getString(1);
			     	nombre= rs.getString(2);
			     	nickname=rs.getString(3);
			     	rol= rs.getInt(4);
			    	mail=rs.getString(5);
			    	verificado=rs.getBoolean(6);
			    	
			    	Usuario usus = new Usuario(nombre,contraseña,nickname,mail,verificado,rol);
					return usus;
		   }
			Usuario usus = new Usuario(nombre,contraseña,nickname,mail,verificado,rol);
			return usus;
		}
		    catch(SQLException ex) { System.out.println(ex+"error en usuariodata"); return null;}
			catch(Exception e) {System.out.print("error"+ e); return null;}
		
	
		 
			
		   
}


}

