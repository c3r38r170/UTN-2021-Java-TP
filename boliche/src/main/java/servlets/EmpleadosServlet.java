package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;


@WebServlet("/Empleados")
public class EmpleadosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EmpleadosServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int seleccionado=Integer.parseInt(  request.getParameter("select"));
		String  nombre= request.getParameter("nombre");
		String contrasena = request.getParameter("ContreasenaActual");
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		
		int btn= Integer.parseInt(request.getParameter("btn")) ;
		
		HttpSession sesionUsuario = (HttpSession) request.getSession();
		Usuario usuario=(Usuario) sesionUsuario.getAttribute("usuario"); 		
		switch(btn) 
		{
		case 1: 
			Usuario.agregar(nombre, nickname ,contrasena, email, true,usuario.getID() , seleccionado);
			
			break;
		
		case 2: 
			
			int id =Usuario.TraerIdEmpleado(nombre, seleccionado);
			
			Usuario.ActualizarUsuario(id, nombre, nickname, contrasena, email);
			
			break;
		
		
		case 3: 
			int id1 =Usuario.TraerIdEmpleado(nombre, seleccionado);
			
			Usuario.ActualizarUsuario(id1, nombre, nickname, contrasena, email);
			Usuario.EliminarEmpleado (id1) ;
			break;
			
		}
		
		
		
	}

}
