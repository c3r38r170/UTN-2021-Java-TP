package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Conexion;
import datos.PSParameter;


@WebServlet("/Registro")
@MultipartConfig
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Registro() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var secreto=request.getParameter("secreto");
		if(secreto==null)
			response.sendRedirect("/");
		else{
			var con=new Conexion();
			try {
				var usuarioAVerificar=con.preparedSelectStatement("SELECT `ID` FROM `usuarios` WHERE `verificado`=0 AND `secreto`=?;", new PSParameter(secreto));
				usuarioAVerificar.last();
				if(usuarioAVerificar.getRow()>1) {
					request.getRequestDispatcher("/");
					return;
				} 
					
				con.preparedStatement("UPDATE `usuarios` SET `verificado`=1 WHERE `ID`=?;", new PSParameter(usuarioAVerificar.getInt(1)));
				request.getRequestDispatcher("/registrado.jsp");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String nombreUsuario=request.getParameter("usuario");
		String contraseña=request.getParameter("contrasena");
		String nickname=request.getParameter("nickname");
		if(nombreUsuario.length()<4) {
			response.getWriter().write("El nombre de usuario no puede tener menos de 4 caracteres.");
			response.setStatus(400);
			return;
		}
		if(Pattern.matches(nombreUsuario, "\s")) {
			response.getWriter().write("El nombre de usuario no puede tener espacios en blanco.");
			response.setStatus(400);
			return;
		}
		if(nickname.length()<4) {
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
			var usuarios=con.preparedSelectStatement("SELECT COUNT(*) FROM `usuarios` WHERE `usuario`=?",new PSParameter(nombreUsuario));
			usuarios.next();
			if(usuarios.getInt(1)>0) {
				response.getWriter().write("El nombre de usuario ya existe.");
				response.setStatus(400);
				return;
			}
			/*var secreto=new StringBuilder();
			for(int i=0;i<75;i++)
				secreto.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz".charAt((int)Math.floor(Math.random()*62)));*/
			var correo=request.getParameter("correo");
			//con.preparedStatement("INSERT INTO `usuarios` (`usuario`,`contraseña`,`nickname`,`rolID`,`correo`,`secreto`) VALUES (?,?,?,4,?,'"+secreto.toString()+"')", new PSParameter[] {
			con.preparedStatement("INSERT INTO `usuarios` (`usuario`,`contraseña`,`nickname`,`rolID`,`correo`,`verificado`) VALUES (?,?,?,4,?,1)", new PSParameter[] {
					new PSParameter(nombreUsuario)
					,new PSParameter(contraseña)
					,new PSParameter(nickname)
					,new PSParameter(correo)
			});
			response.getWriter().write("¡Registro completado! Ya puede ingresar al sistema.");
			/*try{
				//Le enviaríamos un correo electrónico con un link que verifique el "secreto", y le mostraríamos un mensaje diciendo que debe revisar su casilla de correo.
				Correo.enviar(correo, "Registro en el sistema del boliche", "Para completar el registro, diríjase al siguiente <a href=\""+request.getServerName()+"/Registro?secreto="+secreto+"\">enlace</a>.");
				response.getWriter().write("¡Registro completado! Confirme la dirección de correo electrónico para continuar.");
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
