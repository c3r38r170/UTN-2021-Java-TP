package logica;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import entidades.Noche;

public class Noches {

	public static RespuestaHttp eliminar(int nocheID) {
		try {
			new Noche(nocheID).eliminar();
			return new RespuestaHttp();
		} catch (Exception e) {
			e.printStackTrace();
			return new RespuestaHttp(500,e.getLocalizedMessage());
		}
	}

	public static RespuestaHttp agregar(String fecha, boolean inscripcionesAbiertas) {
		try {
			new Noche(new SimpleDateFormat("yyyy-MM-dd").parse(fecha),inscripcionesAbiertas);
			return new RespuestaHttp();
		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaHttp(400,"La fecha ya existe.");
		} catch (ParseException e) {
			return new RespuestaHttp(400,"Fecha inválida.");
		}
	}

	public static RespuestaHttp editar(int ID, String fecha, boolean inscripcionesAbiertas) {
		try {
			var n=new Noche(ID);
			n.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
			n.setInscripcionAbierta(inscripcionesAbiertas);
			return new RespuestaHttp();
		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaHttp(400,"La fecha ya existe.");
		} catch (ParseException e) {
			e.printStackTrace();
			return new RespuestaHttp(400,"Fecha inválida.");
		}
	}
}
