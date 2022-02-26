package servlets;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Noche;

@MultipartConfig
@WebServlet("/Noches")
public class Noches extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 int  acceso = Integer.parseInt ( (request.getParameter("accion") )) ;
		 
	
		 	
			switch (acceso) {
			case 1:
				try {
					
					int id = Integer.parseInt((request.getParameter("id")));
				
					Noche.eliminar(id);
				} catch (Exception e) {
					response.setStatus(500);
					e.printStackTrace();
				}


				break;

			case 2:

				try {
					String fechaNoche = request.getParameter("fecha");
					Boolean estado = Boolean.parseBoolean((request.getParameter("estado")));

					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

					Noche.Agregar(sd.parse(fechaNoche), estado);
				} catch (Exception e) {
					response.setStatus(500);
					response.getWriter().write("La fecha ya existe.");
				}
				break;

			case 3:
				try {

					int id = Integer.parseInt((request.getParameter("id")));
					String fechaNoche = request.getParameter("fecha");
					Boolean estado = Boolean.parseBoolean((request.getParameter("estado")));
					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");


					Noche.Editar(id, sd.parse(fechaNoche), estado);
				} catch (ParseException e) {
					response.setStatus(500);
					e.printStackTrace();
				}
				break;
			}

		}

	}
