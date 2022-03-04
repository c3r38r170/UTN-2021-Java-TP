package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Rol;
import logica.Accesos;
import logica.Sesion;
import logica.Usuarios;

@WebServlet("/Bloquear")
@MultipartConfig
public class BloquearServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		var sesion=new Sesion(request.getSession());
		var res=sesion.verificarUsuario(Rol.Administrador);
		if(res!=null) {
			res.responder(response);
			return;
		}

		String clienteID=request.getParameter("clienteID");
		String habilitado=request.getParameter("habilitado");
		if(clienteID==null || habilitado==null) {
			response.setStatus(400);
			return;
		}
		
		Usuarios.habilitar(
				Integer.parseInt(clienteID)
				, Boolean.parseBoolean(habilitado)
		)
			.responder(response);
	}

}
