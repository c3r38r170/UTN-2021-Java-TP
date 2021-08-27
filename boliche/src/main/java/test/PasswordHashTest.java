package test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControladorLogin
 */
@WebServlet("/PasswordHashTest")
@MultipartConfig
public class PasswordHashTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordHashTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String a;
		//a=request.getParameter("password");
		try {
			a=hashPassword(request.getParameter("password"));
			a+=" - "+a.length();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			a=e.getMessage();
			//e.printStackTrace();
		}
		JsonObject value = Json.createObjectBuilder()
		     .add("firstName", "John")
		     .add("lastName", "Smith")
		     .add("age", 25)
		     .add("address", Json.createObjectBuilder()
		         .add("streetAddress", "21 2nd Street")
		         .add("city", "New York")
		         .add("state", "NY")
		         .add("postalCode", "10021"))
		     .add("phoneNumber", Json.createArrayBuilder()
		         .add(Json.createObjectBuilder()
		             .add("type", "home")
		             .add("number", "212 555-1234"))
		         .add(Json.createObjectBuilder()
		             .add("type", "fax")
		             .add("number", "646 555-4567")))
		     .build();
		response.getWriter().append(a);
	}

	private String hashPassword(String password) throws GeneralSecurityException {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return byteArrayToBase64String(salt)+":"+byteArrayToBase64String(
			SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(
				new PBEKeySpec(password.toCharArray(), salt, 65536, 128)
			).getEncoded()
		);
	}
	
	private String byteArrayToBase64String(byte[] array) {
		return new String(Base64.getEncoder().encode(array));
	}
}
