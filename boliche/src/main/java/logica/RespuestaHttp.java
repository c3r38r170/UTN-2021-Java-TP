package logica;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class RespuestaHttp implements IRespondeHttp{
	public RespuestaHttp(int codigoDeEstado, String mensaje) {
		super();
		this.codigoDeEstado = codigoDeEstado;
		this.mensaje = mensaje;
	}
	public RespuestaHttp() {
		super();
	}
	int codigoDeEstado=200;
	public void setCodigoDeEstado(int statusCode) {
		this.codigoDeEstado = statusCode;
	}
	public void setMensaje(String message) {
		this.mensaje = message;
	}
	String mensaje="";
	@Override
	public void responder(HttpServletResponse response) {
		response.setStatus(codigoDeEstado);
		try {
			response.getWriter().write(mensaje);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
