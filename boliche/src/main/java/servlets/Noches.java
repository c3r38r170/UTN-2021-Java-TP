package servlets;
import entidades.Noche;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import entidades.Noche;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
@WebServlet("/Noches")
public class Noches extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String fecha=  request.getParameter("fecha");
		 Boolean b = Boolean.parseBoolean (request.getParameter("estado"));
		 int  acceso = Integer.parseInt ( (request.getParameter("acceso") )) ;
		 
		 response.getWriter().append(fecha+' '+b.toString());

			switch (acceso) {
			case 1:
				try {
					int id = Integer.parseInt((request.getParameter("id")));
					Noche.eliminar(id);
				} catch (Exception e) {
					e.printStackTrace();
				}

				break;

			case 2:

				try {
					String fechaNoche = request.getParameter("fecha");
					Boolean estado = Boolean.parseBoolean((request.getParameter("estado")));

					SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");

					Noche.Agregar(sd.parse(fechaNoche), estado);
				} catch (Exception e) {

				}
				break;

			case 3:
				try {

					int id = Integer.parseInt((request.getParameter("id")));
					String fechaNoche = request.getParameter("fecha");
					Boolean estado = Boolean.parseBoolean((request.getParameter("estado")));
					SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");

					Noche.Editar(id, sd.parse(fechaNoche), estado);
				} catch (ParseException e) {

					e.printStackTrace();
				}
				break;
			}
			// response.getWriter().append(fecha);

			// SimpleDateFormat formato = new SimpleDateFormat("YYYY/MM/DD");
			// Date fech = null;

		}

	}
