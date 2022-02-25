package entidades;
import java.sql.*;
import datos.Conexion;
import datos.PSParameter;
import datos.PSParameter.Types;
public class Accesos {
	
	public void completarAcceso(int usuarioID,int nocheID,int seguridadID,int estado) {
		 Conexion conn=new Conexion();
        try {
				conn.executeQuery(
			              "Update acceso set"
				              + " seguridadID="+seguridadID
				              +",estadoID="+estado
				              +",hora=current_time()"
			              + " where clienteID = "+usuarioID
			              	+" AND nocheID="+nocheID
			              	+" AND estadoID=1"
			          );
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public void completarAcceso(int clienteID,int nocheID,int seguridadID,int estado,String comentario) {
		// TODO reescribir usando la clase Acceso
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
