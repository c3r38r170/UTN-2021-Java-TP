package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/cde";
	public static final String USUARIO = "root";
	public static final String CLAVE = "";

	
	static {
		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador" + e);
			e.printStackTrace();
		}
	}
	public Connection conectar() {
		
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
		} catch (SQLException e) {
			System.out.println("Error en la conexión" + e);
			e.printStackTrace();
		}
		
		return conexion;
	}
}
