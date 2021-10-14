package datos;
import java.sql.*;

public class Conexion1 {

	 final private static String url ="jdbc:mysql://localhost:3306/cde";
	final private static String  usuarios = "root";
	final private static String  contrasena= "admin123";	


	public Connection conectar() {
		Connection con = null;
		try 
		{
			 
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			 System.out.print ("¡Error al cargar el controlador!" + e.toString ());
		}

		try {
			
		con = DriverManager.getConnection(url,usuarios,contrasena);
		
		return con ;
		}
		
		catch(SQLException eq) {System.out.print(eq);}
		
		
		return con ;	
		}




}
	
 

