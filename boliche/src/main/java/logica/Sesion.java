package logica;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import entidades.Rol;
import entidades.Usuario;

public class Sesion {
	public Sesion(HttpSession sesion) {
		super();
		this.sesion = sesion;
		this.usuario=(Usuario) sesion.getAttribute("usuario");
	}

	private HttpSession sesion;
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public RespuestaHttp login(String nombreUsuario, String contrasena) {
		
		
		Usuario user;
		try {
			user = new Usuario(nombreUsuario);
		} catch (SQLException e) {
			return new RespuestaHttp(404,"Nombre de usuario incorrecto.");
		}
		


		if(user.isVerificado() == false   )
		{
			return new RespuestaHttp(403,"Su usuario se encuentra deshabilitado, verifique su mail.");
		}
		
		
			String contraUsuario= user.getContraseña();
			// TODO hashing y eso
		if( contraUsuario.equals(contrasena) ) 
		{
			RespuestaHttp res = new RespuestaHttp();

			Rol rol = user.getRol(); 
			
		
			sesion.setAttribute("usuario", user );
			this.usuario=user;
			String redirección="";
			
			switch(rol) 
			{
			case Administrador:
				redirección="Administrador";
			break;
			case Seguridad:
				redirección="Seguridad";
			break;
			case Cliente:
				redirección="Cliente";
				break;
			case Desconocido:
					res.setCodigoDeEstado(500);
					break;
			default: // Para que pase esto esta clase tiene que estar desactualizada.
				res.setCodigoDeEstado(501);
				break;
			}
			res.setMensaje(redirección);
			return res;
		}
		else 
		{
			return new RespuestaHttp(403, "Contraseña incorrecta.");
		}
	}

	public RedireccionHttp logout() {
		//var user = (Usuario)sesion.getAttribute("usuario");
		if(usuario!=null)
			sesion.removeAttribute("usuario");
		
		return new RedireccionHttp("index.jsp");
	}

	public RespuestaHttp verificarUsuario() {
		return usuario==null?
				new RespuestaHttp(401)
				:null;
	}

	public RespuestaHttp verificarUsuario(Rol rol) {
		if(usuario==null)
			return new RespuestaHttp(401);
		if(usuario.getRol()!=rol)
			return new RespuestaHttp(403);
		return null;
	}
}
