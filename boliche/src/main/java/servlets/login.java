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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		String Usuario= request.getParameter("usu");
		String nickName= request.getParameter("nick");
		String mail= request.getParameter("correo");
		
	Usuario  U = new Usuario();
	String UsU= U.getByUsuarios(Usuario);
	String contra =U.GETcontrasena(contrasena);
	
	if(UsU != "-1" && contra != "-1") 
	{
		
	  int rol = U.verifica_Usuario(contra,UsU); 
	
	  switch(rol) 
	  {
	  case 1:
	  HttpSession session = request.getSession();
	  Usuario sesionU = U.getUsuarioData(UsU,contra);
	  
	   session.setAttribute("usuarior",sesionU );
	   System.out.println(sesionU.getNombre());
	   request.getRequestDispatcher("Cliente.jsp").forward(request, response);
	  break;
	  case 2: request.getRequestDispatcher("SuperAdmin.jsp").forward(request, response); break;
	  case 3: request.getRequestDispatcher("Seguridad.jsp").forward(request, response); break;
	  
	  }
	  
	  
	}
	else 
	{
		@SuppressWarnings("unused")
		PrintWriter pw= response.getWriter();
		pw.print("<html> <body>");
		pw.print("<br>");
		pw.print(" <h3>  Usuario o contraseña incorrectas  </h3> ");
		pw.print(" <h3>  Verifique los datos  </h3> ");
		pw.print("</html> </body>");
	}
	  
	
	
	
	}
	}
