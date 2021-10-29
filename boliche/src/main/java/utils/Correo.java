package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

//TODO maybe abstract
public class Correo {
	private static String correo;
	private static String contraseña;
	
	public static void enviar(String para,String asunto,String mensaje) throws Exception {
		if(Correo.contraseña==null || Correo.correo==null)
			if(!Correo.obtenerCredenciales())
				throw new FileNotFoundException("No se pudieron obtener las credenciales para enviar el correo.");

		//TODO cambiar nombre de la casilla
		 Email email = EmailBuilder.startingBlank()
			    .from(null, Correo.correo)
			    .to(null, para)
			    .withSubject(asunto)
			    .withHTMLText(mensaje)
			    .buildEmail();
	
		 Mailer m=MailerBuilder
	  .withSMTPServer("smtp.gmail.com", 25, Correo.correo, Correo.contraseña).buildMailer();
	  m.sendMail(email);
				
	}

	private static boolean obtenerCredenciales() {
		File credencialesFile=new File("./.credenciales");
		try {
			Scanner s=new Scanner(credencialesFile);
			correo=s.nextLine();
			contraseña=s.nextLine();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}
}
