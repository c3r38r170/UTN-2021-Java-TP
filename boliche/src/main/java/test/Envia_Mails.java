package test;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Envia_Mails 
{
	
	public void enviar(String correo_receptorinput,String asunto, String Mensaje) 
	{

	 try {
	        Properties propiedades = new Properties();
	        propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
	        propiedades.setProperty("mail.smtp.starttls.enable", "true");
	        propiedades.setProperty("mail.smtp.port", "25");
	        propiedades.setProperty("mail.smtp.auth", "true");
	        
	        
	        
	        String correo_emisor = "labarbahipolito3@gmail.com";
	        String contraseña_emisor = "operacion9";
	        
	        String correo_receptor = correo_receptorinput;
	     
	        String mensaje = "Hola, soy un mensaje desde java, puto al que le llega este mensaje.";
	        
	        Session sesion = Session.getDefaultInstance(propiedades);
	        MimeMessage message = new MimeMessage(sesion);
	        message.setFrom(new InternetAddress(correo_emisor));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo_receptor));
	        message.setSubject(asunto);
	        message.setText(mensaje);
	        
	        Transport transporte = sesion.getTransport("smtp");
	        transporte.connect(correo_emisor,contraseña_emisor);
	        transporte.sendMessage(message , message.getRecipients(Message.RecipientType.TO));
	        transporte.close();
	        
	       
	        
	        } 
	 		  catch (AddressException ex) {
	           System.out.print(ex);
	        } catch (MessagingException exs) {
		           System.out.print(exs);}
	}
		
}

