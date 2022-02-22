package servlets;

import javax.sql.*;
import com.mysql.cj.Session;
import javax.servlet.http.*; 
import datos.Conexion;
import entidades.Usuario;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax .servlet.HttpConstraintElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
@MultipartConfig
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		// TODO no funciona dueño
		
		String contrasena = request.getParameter("password");
		String nombreUsuario= request.getParameter("username");


		Usuario user= Usuario.checkUsernameExistence(nombreUsuario);
		
		if(user == null) 
		{
			response.getWriter().write("Nombre de usuario incorrecto.");
			response.setStatus(404);
			return;
		}
		
		


		if(user.isVerificado() == false   )
		{
			response.getWriter().write("Su usuario se encuentra deshabilitado, verifique su mail.");
			System.out.println();
			response.setStatus(403);
			return;
		}
		
			;
		
			String contraUsuario= user.getContraseña();
		if( contraUsuario.equals(contrasena) ) 
		{

			int rol = Usuario.getUserRol(contrasena,user.getNombre()); 
			HttpSession session = request.getSession();
			session.setAttribute("usuarior", user );
			session.setAttribute("usuario", user );
			String redirección="";
			
			switch(rol) 
			{
			case 1:
				redirección="SuperAdmin.jsp";
				break;
			case 2:
				redirección="Administrador.jsp";
			break;
			case 3:
				redirección="Seguridad.jsp";
			break;
			case 4:
				redirección="Cliente.jsp";
				break;

			}
			response.getWriter().write(redirección);

		}
		else 
		{
			response.getWriter().write("Contraseña incorrecta.");
			response.setStatus(403);
			return;
		}




	}
}
