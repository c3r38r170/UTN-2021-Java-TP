package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion{

	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/cde";
	private static final String USUARIO = "root";
	private static final String CLAVE = "";
	
	private Connection conn;
	
	static {
		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador" + e);
			e.printStackTrace();
		}
	}
	
	public Conexion() {
		try {
			conn = DriverManager.getConnection(URL, USUARIO, CLAVE);
		} catch (SQLException e) {
			System.out.println("Error en la conexión" + e);
			e.printStackTrace();
		}
	}

	public ResultSet executeSelect(String query) throws SQLException {
		Statement s =conn.createStatement();
		return s.executeQuery(query);
	}
	
	public ResultSet preparedSelectStatement(String query,PSParameter[] parametros) throws SQLException{
		PreparedStatement s= null;
		
		s= conn.prepareStatement(query ,Statement.RETURN_GENERATED_KEYS);
		for(int i=0;i<parametros.length;i++) {
			PSParameter param=parametros[i];
			switch(param.getTipo()) {
				case STRING:
			    s.setString(i+1, (String)param.getParametro());
					break;
				case INT:
			    s.setInt(i+1, (int)param.getParametro());
					break;
			}
		}
			
		return s.executeQuery();
	}
	
	public int preparedStatement(String query,PSParameter[] parametros) throws SQLException {
		PreparedStatement s= null;
		
		s= conn.prepareStatement(query ,Statement.RETURN_GENERATED_KEYS);
		for(int i=0;i<parametros.length;i++) {
			PSParameter param=parametros[i];
			switch(param.getTipo()) {
				case STRING:
			    s.setString(i+1, (String)param.getParametro());
					break;
				case INT:
			    s.setInt(i+1, (int)param.getParametro());
					break;
			}
		}
		
		return s.executeUpdate();
	}
	
}
