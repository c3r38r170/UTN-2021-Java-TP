package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.RespuestaHttp;
import logica.Sesion;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
@MultipartConfig
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		var usuario=request.getParameter("username");
		var contrasenia=request.getParameter("password");
		if(usuario==null || contrasenia==null) {
			new RespuestaHttp(401).responder(response);
		}else new Sesion(request.getSession()).login(
				request.getParameter("username")
				,request.getParameter("password")
			).responder(response);
	}
}
