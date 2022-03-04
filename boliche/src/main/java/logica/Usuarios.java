package logica;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import datos.Conexion;
import entidades.Usuario;

public class Usuarios {
	public static HttpServletResponse response;

	public  static  RespuestaHttp agregar(String usuario, String contrasena, String emial,String nickname  ) throws IOException 
	{
		try {
			if(usuario.length()<4) {
			response.getWriter().write("El nombre de usuario no puede tener menos de 4 caracteres.");
			response.setStatus(400);
			return null ;
		}
		if(Pattern.matches(usuario, "\s")) {
			response.getWriter().write("El nombre de usuario no puede tener espacios en blanco.");
			response.setStatus(400);
			return null ;
		}
		if(nickname.length()<4) {
			response.getWriter().write("El nick no puede tener menos de 4 caracteres.");
			response.setStatus(400);
			return null;
		}
		if(contrasena.length()<8) {
			response.getWriter().write("La contraseña no puede tener menos de 8 caracteres.");
			response.setStatus(400);
		
		return null;}
		
		Usuario.agregar(usuario,nickname,contrasena, emial, true,1,3);
		
		return new RespuestaHttp();
		}
		catch (Exception e) {return  new RespuestaHttp(500,"Error en el servidor ");}
		
			}
		
	public  static  RespuestaHttp Modificar(int id, String Usuarios,String nickname,String Contrasena,String email) 
	{
		
		
		try {
			if(Usuarios.length()<4) {
				response.getWriter().write("El nombre de usuario no puede tener menos de 4 caracteres.");
				response.setStatus(400);
				return null ;
			}
			if(Pattern.matches(Usuarios, "\s")) {
				response.getWriter().write("El nombre de usuario no puede tener espacios en blanco.");
				response.setStatus(400);
				return null ;
			}
			if(nickname.length()<4) {
				response.getWriter().write("El nick no puede tener menos de 4 caracteres.");
				response.setStatus(400);
				return null;
			}
			if(Contrasena.length()<8) {
				response.getWriter().write("La contraseña no puede tener menos de 8 caracteres.");
				response.setStatus(400);
			
			return null;}
			
			
			Usuario.ActualizarUsuario(id, Usuarios, nickname, Contrasena, email);
			return new RespuestaHttp();
		}
		catch (Exception e) {return new RespuestaHttp(500,"error en el servidor");}
	
		
	}
	
	
	
	
	
	
	
	public  static  RespuestaHttp eliminar (int id) 
	{
		
		
		try {
			Usuario.EliminarEmpleado(id);
			return new RespuestaHttp();
		}
		catch (Exception e) {return new RespuestaHttp(500,"error en el servidor");}
	
		
	}
	
	
	public static void  verificarExistenciaUsuario(String nombreUsuario) throws IOException 
	{
		int result =Usuario.verificarExistenciaUsuario(nombreUsuario);
		
		if(result == 1) 
		{
			response.getWriter().write("Este usuario ya esta en uso");
			response.setStatus(400);
		}
	}
	
	

	
	
	
	
	public  static  RespuestaHttp agregarEmpleado(String usuario, String contrasena, String emial,String nickname,boolean verificado,  int creador , int rol ) throws IOException 
	{
		try {
			if(usuario.length()<4) {
			response.getWriter().write("El nombre de usuario no puede tener menos de 4 caracteres.");
			response.setStatus(400);
			return null ;
		}
		if(Pattern.matches(usuario, "\s")) {
			response.getWriter().write("El nombre de usuario no puede tener espacios en blanco.");
			response.setStatus(400);
			return null ;
		}
		if(nickname.length()<4) {
			response.getWriter().write("El nick no puede tener menos de 4 caracteres.");
			response.setStatus(400);
			return null;
		}
		if(contrasena.length()<8) {
			response.getWriter().write("La contraseña no puede tener menos de 8 caracteres.");
			response.setStatus(400);
		
		return null;}
		
		Usuario.agregar(usuario,nickname,contrasena, emial, true,creador,rol);
		
		return new RespuestaHttp();
		}
		catch (Exception e) {return  new RespuestaHttp(500,"Error en el servidor ");}
		
			}
		
	
	
	
	public static int traerIDempleado(String nombre, int seleccionado) {
		try {
		if(nombre.length()<3) {
			response.getWriter().write("El nombre de usuario no puede tener menos de 4 caracteres.");
			response.setStatus(400);
			return 0 ;
		}
		if(Pattern.matches(nombre, "\s")) {
			response.getWriter().write("El nombre de usuario no puede tener espacios en blanco.");
			response.setStatus(400);
			return 0 ;
		}
			
		
		
		
		int id1 =Usuario.TraerIdEmpleado(nombre, seleccionado);
		
		return id1;
		}
		catch(Exception e) {response.setStatus(400);}
		return 0;
		
		
	}

	public static LinkedList<Usuario> clientes() throws SQLException{
		var lista=new LinkedList<Usuario>();
		
		var rs=new Conexion().executeSelect("SELECT * FROM usuarios WHERE rolID=3 ORDER BY nickname ASC");
		while(rs.next()) {
			lista.add(new Usuario(rs));
		}
		
		return lista;
	}
	
	public static RespuestaHttp habilitar(int clienteID,boolean habilitado) {
		try {
			new Usuario(clienteID).setVerificado(habilitado);
			return new RespuestaHttp();
		} catch (SQLException e) {
			return new RespuestaHttp(500,e.getLocalizedMessage());
		}
	}
}