package test;

import java.io.File;
import java.util.Scanner;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import utils.Correo;

public class TestClass {
public static void main(String[] args) {
//TODO ponerle de nombre a la casilla el nombre de la app + " - Registro"
	 Email email = EmailBuilder.startingBlank()
		    .from(null, "labarbahipolito3@gmail.com")
		    .to(null, "sabudvicco@gmail.com")
		    .withSubject("aaa")
		    .withHTMLText("asdasd")
		    .buildEmail();

	 Mailer m=MailerBuilder
 .withSMTPServer("smtp.gmail.com", 25, "labarbahipolito3@gmail.com", "operacion9").buildMailer();
 m.sendMail(email);
			
}
}
