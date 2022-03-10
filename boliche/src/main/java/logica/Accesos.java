package logica;

import java.sql.SQLException;

import entidades.Acceso;
import entidades.Usuario;

public class Accesos {
	
	public RespuestaHttp evaluar(int accesoID, String comentario, int accion, Usuario seguridad) {
		RespuestaHttp respuesta=new RespuestaHttp();

		Acceso ac= new Acceso(accesoID);
		try {
			ac.setEstado(accion,seguridad.getID());
			if(!(comentario==null || comentario.isBlank()))
				ac.setComentario(comentario);
		} catch (SQLException e) {
			respuesta.setCodigoDeEstado(500);
			respuesta.setMensaje(e.getMessage());
		}
		
		return respuesta;
	}

	public RespuestaHttp generar(Usuario cliente, int accesoID) {
		RespuestaHttp respuesta=new RespuestaHttp();

		Acceso ac;
		try {
			ac = new Acceso(cliente.getID(),accesoID);
			respuesta.setMensaje(""+ac.getID());
		} catch (SQLException e) {
			respuesta.setCodigoDeEstado(500);
			respuesta.setMensaje(e.getLocalizedMessage());
		}
		
		return respuesta;
	}
}
