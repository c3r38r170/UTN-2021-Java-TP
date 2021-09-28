package servlets;

import javax.sql.*;

import datos.Conexion;
import entidades.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	  int rol = U.verifica_Usuario(contra, UsU);
	  switch(rol) 
	  {
	  case 1: response.sendRedirect("Clientes.html");
	  case 2: response.sendRedirect("SuperAdmins.html");
	  case 3: response.sendRedirect("Seguridad.html");
	  
	  }
	  
	  
	}
	else 
	{
		@SuppressWarnings("unused")
		PrintWriter pw= response.getWriter();
		pw.print("<html> <body>");
		pw.print("<br>");
		pw.print(" <h3>  Usuario incorrectas  </h3> ");
		pw.print("</html> </body>");
	}
	  
	
	
	
	}

}
