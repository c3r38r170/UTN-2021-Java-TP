package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
//import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

//TODO maybe abstract
public class Correo {
	private static String DIRECCIÓN;
	private static String CONTRASEÑA;
	
	static {
		//File credencialesFile=new File("./correo.credenciales");
		Scanner s=new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("credenciales/correo.credenciales"));
		DIRECCIÓN=s.nextLine().trim();
		CONTRASEÑA=s.nextLine().trim();
	}
	
	public static void enviar(String para,String asunto,String mensaje) throws Exception {
		if(Correo.CONTRASEÑA==null || Correo.DIRECCIÓN==null)
			throw new FileNotFoundException("No se pudieron obtener las credenciales para enviar el correo.");

    /*Properties prop = new Properties();
    prop.put("mail.smtp.host", "smtp.gmail.com");
    prop.put("mail.smtp.port", "587");
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.starttls.enable", "true"); //TLS
    
    Session session = Session.getInstance(prop,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Correo.DIRECCIÓN, Correo.CONTRASEÑA);
                }
            });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(Correo.DIRECCIÓN));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(para)
        );
        message.setSubject(asunto);
        message.setText(mensaje);

        Transport.send(message);

        System.out.println("Done");

    } catch (Exception e) {
        e.printStackTrace();
    }*/
		
		/*HtmlEmail mail = new HtmlEmail();
    mail.setHostName("smtp.gmail.com");
    mail.setSmtpPort(25);
    mail.setAuthenticator(new DefaultAuthenticator(Correo.DIRECCIÓN,Correo.CONTRASEÑA));

    mail.setFrom(Correo.DIRECCIÓN, " - Registro");
    mail.addTo(para, null);
    mail.setSubject(asunto);
    mail.setHtmlMsg(mensaje);//"<p style='font-size:16px;color:green'>Here is your example</p>"
    mail.send();*/
		
		//TODO ponerle de nombre a la casilla el nombre de la app + " - Registro"
		Email email = EmailBuilder.startingBlank()
			.from(null, Correo.DIRECCIÓN)
			.to(null, para)
			.withSubject(asunto)
			.withHTMLText(mensaje)
			.buildEmail();

		Mailer m=MailerBuilder.withSMTPServer("smtp.gmail.com", 25, Correo.DIRECCIÓN, Correo.CONTRASEÑA).buildMailer();
	  m.sendMail(email);
				
	}
}
