package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Rol;
import logica.Accesos;
import logica.Sesion;

@WebServlet("/accesos")
@MultipartConfig
public class AccesosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		var sesion=new Sesion(request.getSession());
		var res=sesion.verificarUsuario(Rol.Seguridad);
		if(res!=null) {
			res.responder(response);
			return;
		}
		
		var accesoID=request.getParameter("accesoID");
		var accion=request.getParameter("accion");
		if(accesoID==null || accion==null) {
			response.setStatus(400);
			return;
		}
		
		new Accesos().evaluar(
				Integer.parseInt(accesoID)
				,request.getParameter("comentario")
				,Integer.parseInt(accion)==1?2:3
				,sesion.getUsuario()
		).responder(response);
	}
}
