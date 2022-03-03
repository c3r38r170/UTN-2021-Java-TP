package logica;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class RedireccionHttp implements IRespondeHttp{
	public RedireccionHttp(String ruta) {
		super();
		this.ruta = ruta;
	}


	String ruta;
	

	@Override
	public void responder(HttpServletResponse response) {
		try {
			response.sendRedirect(ruta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
