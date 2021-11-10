package entidades;
import java.sql.*;
import datos.Conexion;
import datos.PSParameter;
import datos.PSParameter.Types;
public class Accesos {

	public void DeleteUsersThatGotAcces(int idUsuario) 
	{
		@SuppressWarnings("unused")
		ResultSet rs = null;
		 Conexion conn=new Conexion();
         try {
			  @SuppressWarnings("unused")
			int k = conn.preparedStatement
			 		(
			              "DELETE FROM acceso WHERE clienteID = ?    ;"
			                  , new PSParameter[] 
			                		  {
					                    new PSParameter(idUsuario,Types.INT)
			                          }
			          );
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void SendCommentToGil(int IDusuario,String comentario) 
	{//inserta comentario
		@SuppressWarnings("unused")
		int  columnsafected;
		int lastid = 0;
		Conexion conn=new Conexion();
        try {
        	columnsafected  = conn.preparedStatement
			 		(
			              "INSERT INTO comentario(comentario) VALUE (?) ;"
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
			  rs=null;
			  
	//inserta comentario en el usuario que accede
			  
			  
			   columnsafected = conn.preparedStatement
				 		(
				              "INSERT INTO acceso (comentarioID) value( ? ) where clienteID = ?  "
				                  , new PSParameter[] 
				                		  {
						                     new PSParameter(lastid,Types.INT),
						                     new PSParameter( IDusuario ,Types.INT)
				                          }
				          );		  
			  
			  
		} catch (SQLException e) {e.printStackTrace();}
			
			
		
	}
	
	
	
	
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
	
	
	
}
