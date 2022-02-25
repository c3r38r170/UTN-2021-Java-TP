package datos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Scanner;

public class Conexion{

	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/";
	private static String USUARIO ;
	private static String CLAVE ;
	private Connection conn;
	
	static {
		try(Scanner s=new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("credenciales/base-de-datos.credenciales"))){
		//try(Scanner s=new Scanner(new File("./base-de-datos.credenciales"))){
			Class.forName(CONTROLADOR);
			Conexion.URL+=s.nextLine();//nombre de la base de datos
			Conexion.USUARIO=s.nextLine();
			Conexion.CLAVE=s.nextLine();
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador" + e);
			e.printStackTrace();
		}
	}
	
	public Conexion() {//TODO probar date y boolean
		try {
			conn = DriverManager.getConnection(URL, USUARIO, CLAVE);
		} catch (SQLException e) {
			System.out.println("Error en la conexión: "+e.getMessage());
		}
	}

	public ResultSet executeSelect(String query) throws SQLException {
		Statement s =conn.createStatement();
		return s.executeQuery(query);
	}
	public int executeQuery(String query) throws SQLException {
		Statement s =conn.createStatement();
		return s.executeUpdate(query);
	}

	public ResultSet preparedSelectStatement(String query,PSParameter parametroUnico) throws SQLException{
		return this.preparedSelectStatement(query, new PSParameter[] {parametroUnico});
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
			case BOOLEAN: s.setBoolean(i+1, (boolean)param.getParametro());
				break;
			case DATE:s.setDate(i+1, (Date)param.getParametro());
			break;
			case TIME:
				s.setTime(i+1, (Time)param.getParametro());
			break;
			default:
				break;
					
				
			}
		}
			
		return s.executeQuery();
	}

	public int preparedStatement(String query,PSParameter parametroUnico) throws SQLException {
		return this.preparedStatement(query, new PSParameter[] {parametroUnico});
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
				case BOOLEAN:
			    s.setBoolean(i+1, (boolean)param.getParametro());
					break;
				case DATE:
			    s.setDate(i+1, (Date)param.getParametro());
					break;
				case TIME:
					s.setTime(i+1, (Time)param.getParametro());
				break;
				default:
				break;
			}
		}
		
		return s.executeUpdate();
	}
	
	public int lastInsertID() throws SQLException {
		//TODO reemplazar en todos lados
		return primerFila("SELECT LAST_INSERT_ID();").getInt(1);
	}
	
	public ResultSet primerFila(String query) throws SQLException {
		ResultSet rs=executeSelect(query);
		rs.next();
		return rs;
	}
	
	
	
}
