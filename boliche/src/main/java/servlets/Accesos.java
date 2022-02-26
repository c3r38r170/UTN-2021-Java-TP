package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Acceso;
import entidades.Noche;
import entidades.Usuario;

@WebServlet("/accesos")
@MultipartConfig
public class Accesos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Accesos() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accesoID=Integer.parseInt(request.getParameter("accesoID"));
		String comentario=request.getParameter("comentario");
		int accion= Integer.parseInt(   request.getParameter("accion")  );
		
		//TODO revisar parámetros y sesión
		HttpSession sessio = request.getSession();

		Acceso ac= new Acceso(accesoID);
		try {
			ac.setEstado(accion==1?2:3,((Usuario) sessio.getAttribute("usuario")).getID());
			if(!(comentario==null || comentario.isBlank()))
				ac.setComentario(comentario);
		} catch (SQLException e) {
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}
		
	}
}
