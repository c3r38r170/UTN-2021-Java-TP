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

@WebServlet("/accesos")
@MultipartConfig
public class AccesosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AccesosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new Accesos().evaluar(
				Integer.parseInt(request.getParameter("accesoID"))
				,request.getParameter("comentario")
				,Integer.parseInt(   request.getParameter("accion")  )==1?2:3
				,(Usuario) request.getSession().getAttribute("usuario")
		).responder(response);
	}
}
