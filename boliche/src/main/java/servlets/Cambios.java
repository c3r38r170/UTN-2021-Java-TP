package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Rol;
import entidades.Usuario;


@WebServlet("/Cambios")
public class Cambios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Cambios() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	      
		
		HttpSession session = (HttpSession) request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		String ContreasenaActual = request.getParameter("ContreasenaActual");
		String nuevaContrasena = request.getParameter("nuevaContrasena");

		if (usuario == null) {
			response.getWriter().write("Nombre de usuario incorrecto.");
			response.setStatus(404);
			return;

		}

		if (usuario.isVerificado() == false) {
			response.getWriter().write("Su usuario se encuentra deshabilitado, verifique su mail.");
			System.out.println();
			response.setStatus(403);
			return;
		}
		String contraseña = request.getParameter("ContreasenaActual");
		if(contraseña!= usuario.getContraseña() ) 
		{
			response.getWriter().write("Contraseña incorrecta.");
			response.setStatus(403);
			return;	
		}
		
		
		
		
		if (usuario.getRol() == Rol.Seguridad || usuario.getRol() == Rol.Cliente ) {

			if (ContreasenaActual == nuevaContrasena) {

				String nombre = request.getParameter("nombre");
				String nickname = request.getParameter("nickname");
				String email = request.getParameter("email");

				Usuario.ActualizarUsuario(usuario.getID(), nickname, nuevaContrasena, email, nombre);
				response.getWriter().write("Sus Datos han cambiado 🤠");
			} 
			else 
				{response.getWriter().write("Su Contraseñas son distintas, verifique que sean igales");}
				
			

		}
		else 
		{
			String nombre = request.getParameter("nombre");
			String nickname = request.getParameter("nickname");
			String email = request.getParameter("email");
			String contraseña = request.getParameter("ContreasenaActual");
			int rol = Integer.parseInt(request.getParameter("rol")) ;
			
			Usuario.agregar(nombre,nickname,contraseña,email,true,usuario.getID(),rol);
					
			
			
			
		}
	}

}
