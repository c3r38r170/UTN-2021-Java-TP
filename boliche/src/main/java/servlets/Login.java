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
		
		doGet(request, response);
		String contrasena = request.getParameter("contrasena");
		String nombreUsuario= request.getParameter("usu");
		String nickName= request.getParameter("nickname");
		String mail= request.getParameter("correo");


		Usuario user= (Usuario)Usuario.checkUsernameExistence(nombreUsuario);
		
		if(user == null) 
		{
			response.getWriter().write("Nombre de usuario incorrecto.");
			response.setStatus(404);
			return;
		}
		
		


		if(user.isVerificado() == false   )
		{
			response.getWriter().write("Su usuario se encuentra deshabilitado, verifique su mail .");
			System.out.println();
			response.setStatus(403);
			return;
		}
		
			;
		
			String contraUsuario= user.getContraseña().strip();
		 System.out.println(contraUsuario.contentEquals("con"));
		if( contraUsuario.equals(contrasena) ) 
		{
			
		}
		else 
		{
			System.out.println(contraUsuario+contrasena);
			response.getWriter().write("Contraseña incorrecta.");
			response.setStatus(403);
			return;
		}



		if(user.getContraseña() != null && user.getNombre() !=null)
		{

			int rol = Usuario.getUserRol(contrasena,user.getNombre()); 

			System.out.println(rol);
			switch(rol) 
			{
			case 1:
				HttpSession session = request.getSession();
				session.setAttribute("usuarior", user );
				request.getRequestDispatcher("Cliente.jsp").forward(request, response);
				break;
			
			
			case 2: request.getRequestDispatcher("SuperAdmin.jsp").forward(request, response);
			HttpSession session1 = request.getSession();
			session1.setAttribute("usuarior", user );
			break;
			
			
			
			case 3: 
				request.getRequestDispatcher("Seguridad.jsp").forward(request, response);
			HttpSession session2 = request.getSession();
			HttpSession session3 = request.getSession();
			session2.setAttribute("usuarior", user );
			
			session3.setAttribute("usuariosListar", Usuario.GetUsersForTheNight());
			break;

			}


		}
		else 
		{
			PrintWriter pw= response.getWriter();
			pw.print("<html> <body>");
			pw.print("<br>");
			pw.print(" <h3>  Usuario o contraseña incorrectas  </h3> ");
			pw.print(" <h3>  Verifique los datos  </h3> ");
			pw.print("</html> </body>");
		}




	}
}
