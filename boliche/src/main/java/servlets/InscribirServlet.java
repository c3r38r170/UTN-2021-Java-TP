package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Rol;
import entidades.Usuario;
import logica.Accesos;
import logica.Sesion;


@WebServlet("/Inscribir")
@MultipartConfig
public class InscribirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		var sesion=new Sesion(request.getSession());
		var res=sesion.verificarUsuario(Rol.Cliente);
		if(res!=null) {
			res.responder(response);
			return;
		}

		var nocheID=request.getParameter("accion");
		if(nocheID==null) {
			response.setStatus(400);
			return;
		}
		
		new Accesos().generar(
				sesion.getUsuario()
				,Integer.parseInt(nocheID)
		).responder(response);
	}

}
