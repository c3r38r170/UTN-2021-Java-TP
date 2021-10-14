package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Conexion;
import datos.PSParameter;

/**
 * Servlet implementation class Registro
 */
@WebServlet("/registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registro() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreUsuario=request.getParameter("usuario");
		String contraseña=request.getParameter("contraseña");
		String nickname=request.getParameter("nickname");
		if(nombreUsuario.length()<4) {
			response.getWriter().write("El nombre de usuario no puede tener menos de 4 caracteres.");
			response.setStatus(400);
			return;
		}
		if(nombreUsuario.length()<4) {
			response.getWriter().write("El nick no puede tener menos de 4 caracteres.");
			response.setStatus(400);
			return;
		}
		if(contraseña.length()<8) {
			response.getWriter().write("La contraseña no puede tener menos de 8 caracteres.");
			response.setStatus(400);
		}
		var con = new Conexion();
		
		try {
			var usuarios=con.preparedSelectStatement("SELECT COUNT(*) FROM usuarios WHERE `usuario`=?", new PSParameter[] {
					new PSParameter(nombreUsuario, PSParameter.Types.STRING)
			});
			if(usuarios.getInt(1)>0) {
				response.getWriter().write("El nombre de usuario ya existe.");
				response.setStatus(400);
				return;
			}
			var secreto=new StringBuilder();
			for(int i=0;i<75;i++)
				secreto.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz".charAt((int)Math.floor(Math.random()*75)));
			con.preparedStatement("INSERT INTO `usuarios` (`usuario`,`contraseña`,`nickname`,`rolID`,`correo`,`secreto`) VALUES (?,?,?,4,?,"+secreto.toString()+")", new PSParameter[] {
					new PSParameter(nombreUsuario)
					,new PSParameter(contraseña)
					,new PSParameter(nickname)
					,new PSParameter(request.getParameter("correo"))
			});
			//TODO masndar correo
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
