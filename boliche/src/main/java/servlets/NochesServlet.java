package servlets;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.Noches;
import logica.RespuestaHttp;

@MultipartConfig
@WebServlet("/Noches")
public class NochesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 int  acceso = Integer.parseInt ( (request.getParameter("accion") )) ;
		 
		 RespuestaHttp res = new RespuestaHttp(400,"Acción inválida.");
	
		 	
			switch (acceso) {
			case 1:
				res=Noches.eliminar(Integer.parseInt((request.getParameter("id"))));
				break;

			case 2:

					res=Noches.agregar(
							request.getParameter("fecha")
							, (boolean)Boolean.parseBoolean((request.getParameter("estado")))
					);
				break;

			case 3:
				
				res=Noches.editar(
						Integer.parseInt(request.getParameter("id"))
						, request.getParameter("fecha")
						, Boolean.parseBoolean((request.getParameter("estado")))
				);
				break;
			}

			res.responder(response);
			
		}

	}
