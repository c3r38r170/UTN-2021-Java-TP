package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import logica.Usuarios;

@WebServlet("/Empleados")
public class EmpleadosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmpleadosServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int btn = Integer.parseInt(request.getParameter("subm"));

		HttpSession sesionUsuario = (HttpSession) request.getSession();
		Usuario usuario = (Usuario) sesionUsuario.getAttribute("usuario");
		switch (btn) {
		case 1:
			try {

				String nombre = request.getParameter("nombre");
				String contrasena = request.getParameter("ContreasenaActual");
				String email = request.getParameter("email");
				String nickname = request.getParameter("nickname");
				int seleccionado = Integer.parseInt(request.getParameter("select"));
			
				Usuarios.agregarEmpleado(nombre, nickname, contrasena, email, true, usuario.getID(), seleccionado);
			} catch (IOException e2) {

				e2.printStackTrace();
				response.sendError(403);
			}

			break;

		case 2:

			try {

				String nombre = request.getParameter("nombre");
				String contrasena = request.getParameter("ContreasenaActual");
				String email = request.getParameter("email");
				String nickname = request.getParameter("nickname");
				int seleccionado = Integer.parseInt(request.getParameter("select"));

				int id = Usuario.TraerIdEmpleado(nombre, seleccionado);

				Usuarios.Modificar(id, nombre, nickname, contrasena, email);
			} catch (Exception e1) {

				e1.printStackTrace();
				response.sendError(403);
			}

			break;

		case 3:
			int id1;
			try {
				String nombre = request.getParameter("nombre");
				int seleccionado = Integer.parseInt(request.getParameter("select"));
				id1 = Usuarios.traerIDempleado(nombre, seleccionado);
				if (id1 == 0)

				{
					response.sendError(403);
				}
				Usuarios.eliminar(id1);
			} catch (Exception e) {

				e.printStackTrace();
				response.sendError(403);
			}

			break;

		}

	}

}
