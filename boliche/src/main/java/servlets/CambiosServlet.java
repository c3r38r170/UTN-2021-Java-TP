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
import logica.Usuarios;


@WebServlet("/Cambios")
public class CambiosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public CambiosServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO automatic stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

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
		
		
		

		
		if (usuario.getRol() == Rol.Seguridad || usuario.getRol() == Rol.Cliente ) {

			

				String nombre = request.getParameter("nombre");
				String nickname = request.getParameter("nickname");
				String email = request.getParameter("email");

				Usuarios.Modificar(usuario.getID(),nombre,nickname,nuevaContrasena,email);
				response.sendRedirect("index.jsp");
				response.getWriter().write("Sus Datos han cambiado");
				
			
			
				
			

		}
		else 
		{
		
		try {
			
			String nombre = request.getParameter("nombre");
			String nickname = request.getParameter("nickname");
			String email = request.getParameter("email");
			String contraseña11 = request.getParameter("ContreasenaActual");
			int rol = Integer.parseInt(request.getParameter("rol")) ;
			
			
			Usuarios.agregarEmpleado(nombre,nickname,nuevaContrasena,email,true,1,rol);
					
			response.setContentType("text/html");
			
			
				Thread.sleep(5*1000);
				response.sendRedirect("index.jsp");
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			
			
			
			
		}
	}

}
