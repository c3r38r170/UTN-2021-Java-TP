package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Accesos
 */
@WebServlet("/accesos")
@MultipartConfig
public class Accesos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accesos() {
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
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int usuarioID=Integer.parseInt(request.getParameter("usuarioID"));
		String comentario=request.getParameter("comentario");
		int accion= Integer.parseInt(   request.getParameter("accion")  );
		entidades.Accesos ac = new entidades.Accesos();
		
	try {
		if(accion == 1) 
			{

				ac.DeleteUsersThatGotAcces(usuarioID);
			}

		if(accion == 0 ) 
			{
				ac.SendCommentToGil(usuarioID, comentario);
			}
		}
	catch(Exception e) {System.out.println(e);}
		
	}
}
