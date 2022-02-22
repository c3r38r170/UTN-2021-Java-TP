package entidades;
import java.sql.*;
import datos.Conexion;
import datos.PSParameter;
import datos.PSParameter.Types;
public class Accesos {

	public void DeleteUsersThatGotAcces(int idUsuario, int seguridadID) 
	{
		@SuppressWarnings("unused")
		ResultSet rs = null;
		 Conexion conn=new Conexion();
         try {
			  @SuppressWarnings("unused")
			int k = conn.preparedStatement
			 		(
			              "Update acceso set estadoID=1  , seguridadID=? where clienteID = ?    ;"
			                  , new PSParameter[] 
			                		  {
			                			new PSParameter(seguridadID,Types.INT),
					                    new PSParameter(idUsuario,Types.INT)
					                   
			                          }
			          );
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void SendCommentToGil(int IDusuario,String comentario, int idseguridad) 
	{//inserta comentario
		@SuppressWarnings("unused")
		int  columnsafected;
		int lastid = 0;
		Conexion conn=new Conexion();
        try {
        	columnsafected  = conn.preparedStatement
			 		(
			              "INSERT INTO comentario(comentario) VALUES (?) ;"
			                  , new PSParameter[] 
			                		  {
					                     new PSParameter(comentario,Types.STRING)
			                          }
			          );
			  
			  
	 // busca ultimo id
			  ResultSet  rs = conn.executeSelect("SELECT LAST_INSERT_ID();");
	            		
			  while (rs.next()) 
			  {
				lastid= rs.getInt(1);  
			  }
			  rs=null;//TODO close?
			  
	//inserta comentario en el usuario que accede
			  
			  
			   columnsafected = conn.preparedStatement
				 		(
				              "INSERT INTO acceso (comentarioID) value( ? ) where clienteID = ?   "
				                  , new PSParameter[] 
				                		  {
						                     new PSParameter(lastid,Types.INT),
						                     new PSParameter( IDusuario ,Types.INT)
				                          }
				          );		  
			  
			   
			   //instarar id seguridad que hiso comentario
			   int k = conn.preparedStatement
				 		(
				              "Update acceso set seguridadID =? where clienteID = ?    ;"
				                  , new PSParameter[] 
				                		  {
						                    new PSParameter(IDusuario,Types.INT),
						                    new PSParameter(idseguridad,Types.INT)
				                          }
				          );
			  
		} catch (SQLException e) {e.printStackTrace();}
			
			
		
	}
	
	
	
	
	//TODO nocheID
	@SuppressWarnings("unused")
	public void AddPeopleToQueue(int IDusuario) 
	{
		Conexion conn=new Conexion();
		try {
			int columnsafected = conn.preparedStatement
				 		(
				              "INSERT INTO acceso (clienteID,fecha,hora) values ( ? , current_date() , current_time() );"
				                  , new PSParameter[] 
				                		  {
						                     new PSParameter(IDusuario ,Types.INT)
						                     
				                          }
				          );
		} catch (SQLException e) {e.printStackTrace();}
			
			
				  
	}
	
	public void completarAcceso(int usuarioID,int nocheID,int seguridadID,int estado) {
		 Conexion conn=new Conexion();
        try {
				conn.executeQuery(
			              "Update acceso set"
				              + " seguridadID="+seguridadID
				              +",estadoID="+estado
			              + " where clienteID = "+usuarioID
			              	+" AND nocheID="+nocheID
			          );
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public void completarAcceso(int clienteID,int nocheID,int seguridadID,int estado,String comentario) {
		completarAcceso(clienteID,nocheID,seguridadID,estado);
		if(comentario==null || comentario.isBlank())
			return;
		Conexion conn=new Conexion();
		try {
			conn.preparedStatement("INSERT INTO comentario (comentario) VALUES (?)",new PSParameter(comentario));
			ResultSet rs=conn.executeSelect("SELECT LAST_INSERT_ID()");
			rs.next();
			int comentarioID=rs.getInt(1);
			conn.executeQuery("UPDATE acceso SET comentarioID="+comentarioID+" WHERE clienteID="+clienteID+" AND nocheID="+nocheID);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
