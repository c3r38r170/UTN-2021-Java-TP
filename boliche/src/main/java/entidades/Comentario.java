package entidades;
import  datos.Conexion;
import  java.sql.*;

public class Comentario {
	

	@SuppressWarnings("static-access")
	public Comentario(int i, String st) {
		
			Conexion conexio = new Conexion();
			Connection cn = null;
			
			ResultSet rs = null;
			PreparedStatement s= null;
			
			try {
				cn = conexio.conectar();
				s= cn.prepareStatement("INSERT INTO comentario (comentario) VALUES (?)" ,s.RETURN_GENERATED_KEYS);
				
				//	s.setInt(1,i);
				    s.setString(1, st);
				    s.executeUpdate();
				
				
				
				}
			catch (SQLException e) {
				e.printStackTrace();}
			}
}
 
		