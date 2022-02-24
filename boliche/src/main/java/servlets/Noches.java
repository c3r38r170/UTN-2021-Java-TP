package servlets;
import entidades.Noche;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
@WebServlet("/Noches")
public class Noches extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String fecha=  request.getParameter("fecha");
		 Boolean b = Boolean.parseBoolean (request.getParameter("estado"));
		 response.getWriter().append(fecha+' '+b.toString());
        
         // response.getWriter().append(fecha);
         
        // SimpleDateFormat formato = new SimpleDateFormat("YYYY/MM/DD"); 
         //Date fech = null;
	
		
		
	}

}
