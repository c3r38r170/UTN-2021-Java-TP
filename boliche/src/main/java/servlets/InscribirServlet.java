package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Usuario;
import logica.Accesos;


@WebServlet("/Inscribir")
@MultipartConfig
public class InscribirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		new Accesos().generar(
				(Usuario) request.getSession().getAttribute("usuario")
				,Integer.parseInt(request.getParameter("nocheID"))
		).responder(response);
	}

}
