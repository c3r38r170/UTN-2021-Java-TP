package entidades;
import datos.Conexion1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import datos.Conexion;
import datos.Conexion1;
import datos.PSParameter;
import datos.PSParameter.Types;

public class Usuario{
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
	
	
	public Usuario(int ID,String nombre, String contraseña, String nickname, String mail, boolean verificado,int rol) {
		super();
		this.nombre = nombre;
		this.rol=rol;
		this.contraseña = contraseña;
		this.nickname = nickname;
		this.mail= mail;
		this.verificado = verificado;
		this.ID = ID;
	}
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public  static  Usuario checkUsernameExistence(String usu ) {
		
     Usuario usuario=null;
		 try{
				
				     Conexion conn=new Conexion();
				     ResultSet rs = conn.preparedSelectStatement
				            		(
						          "select ID,usuario, contraseña, nickname, correo, verificado,rolID from usuarios  where usuario = ? AND verificado=1  ;"
						                  ,new PSParameter(usu,Types.STRING)
				                     );
				       if(rs.next()) 
				     {
					       usuario = new Usuario(
					      		 rs.getInt(1)
					      		 ,rs.getString(2)
					      		 ,rs.getString(3)
					      		 ,rs.getString(4)
					      		 ,rs.getString(5)
					      		 ,rs.getBoolean(6)
					      		 ,rs.getInt(7)
					      	);
				    	   
				     }

				      
				
			}
			catch(SQLException ex ) {System.out.print(ex.getMessage()+"error ");}
		 return usuario;

	}
	
	public static  String checkPasswordExistence(String pass ,String nombre) {
		
		Connection cn = null;
		ResultSet rs = null;
		PreparedStatement s= null;
		 String password = null;
		 
		try {
			Conexion conn=new Conexion();
            rs=conn.preparedSelectStatement
            		(
            				"select contraseña  from usuarios  where contraseña = ? and usuario =? ;"
		                  , new PSParameter[] {
				                new PSParameter(pass,Types.STRING),new PSParameter(nombre,Types.STRING)
	                                           }
                     );
            
            while(rs.next()) 
            {  
            	password = rs.getString(1); 
            }
            return password;
		}
		
		catch(SQLException e) {System.out.print(e+"error en getcontrasena"); return "-1";}
		
		catch(Exception ex) {System.out.print(ex+"error en getcontrasena"); return "-1";}
		
		
	}
	
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
	


	
	
	
	
	
	//TODO wtf???
	public Usuario getUsuarioData(String usU, String contra) {
		
		Connection cn = null;
		ResultSet rs = null;
		PreparedStatement ps= null;
		
		
		
		try {
			//TODO no
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
			    	mail=rs.getString(7);
			    	verificado=rs.getBoolean(8);
			    	
			    	Usuario usus = new Usuario(rs.getInt(6),nombre,contraseña,nickname,mail,verificado,rol);
					return usus;
		   }
			//TODO wtf?
			Usuario usus = new Usuario(0,nombre,contraseña,nickname,mail,verificado,rol);
			return usus;
		}
		    catch(SQLException ex) { System.out.println(ex+"error en usuariodata"); return null;}
			catch(Exception e) {System.out.print("error"+ e); return null;}
		
	
		   
  }
	public static   LinkedList<Usuario>  GetUsersForTheNight()
	{
		 LinkedList<Usuario> linkusuario = new    LinkedList<Usuario> ();
			ResultSet rs = null;
			PreparedStatement ps= null;
			try 
			{
				Conexion cn = new Conexion();
				rs=cn.executeSelect("select u.ID,u.usuario,u.nickname,u.correo from usuarios u join acceso ac on ac.clienteID = u.ID where  ac.fecha = CURDATE()");
				
			 		while(rs.next())
			 		{
			 			Usuario u = new Usuario(rs.getInt(1),rs.getString(2),null,rs.getString(3),rs.getString(4),false,0);
			 			linkusuario.add(u);
			 		}
			}
			catch(Exception e){System.out.println(e.getMessage());}
		 
		      return linkusuario;
	}
	
	//TODO sacar, las pruebas se hacen en la clase testing
	public static void main(String[] args) 
	{
		try {

					for(Usuario U :Usuario.GetUsersForTheNight() ) 
					{
						System.out.println(U.getID());
					}
		}
		catch(Exception e) {System.out.println(e+"eee");}
	}

}

