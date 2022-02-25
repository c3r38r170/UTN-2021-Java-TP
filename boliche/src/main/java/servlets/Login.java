package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Rol;
import entidades.Usuario;

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
		request.setCharacterEncoding("UTF-8");
		
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
			// TODO hashing y eso
		if( contraUsuario.equals(contrasena) ) 
		{

			Rol rol = user.getRol(); 
			HttpSession session = request.getSession();
			
		
			session.setAttribute("usuario", user );
			String redirección="";
			
			switch(rol) 
			{
			case Administrador:
				redirección="Administrador";
			break;
			case Seguridad:
				redirección="Seguridad";
			break;
			case Cliente:
				redirección="Cliente";
				break;
			case Desconocido:
					response.setStatus(500);
					break;
			default: // Para que pase esto esta clase tiene que estar desactualizada.
				response.setStatus(501);
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
