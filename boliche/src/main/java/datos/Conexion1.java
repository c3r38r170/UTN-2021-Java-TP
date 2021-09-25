package datos;
import java.sql.*;
public class Conexion1 {



	
	private static String url ="jdbc:mysql://localhost:3306/cde";
	private static String  usuarios = "root";
	private static String  contrasena= "admin123";	


	public static Connection conectar() {
		Connection con = null;
		try {
			 con = DriverManager.getConnection(url,usuarios,contrasena);}
		
		catch(SQLException eq) {System.out.print(eq);}
		
		
		return con;	
		}
}
	


