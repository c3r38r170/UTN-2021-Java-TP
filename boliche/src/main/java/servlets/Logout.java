package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Usuario;


@WebServlet("/Logout")
@MultipartConfig
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Logout() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var session=request.getSession();
		var user = (Usuario)session.getAttribute("usuario");
		if(user!=null)
			session.removeAttribute("usuario");
		
		response.sendRedirect("index.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
