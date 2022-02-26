package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Acceso;
import entidades.Usuario;


@WebServlet("/Inscribir")
@MultipartConfig
public class Inscribir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Inscribir() {
        super();
    }
 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sess = request.getSession();
		Acceso ac=new Acceso(((Usuario) sess.getAttribute("usuario")).getID(),Integer.parseInt(request.getParameter("nocheID")));
		response.getWriter().append(""+ac.getID());
	}

}
