package test;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Envia_Mails {

	 String asunto = "Mail de Confirmacion Boliche";
     String mensaje = "codigo";
     String receptores=" mail receptro";//Usuario@gmail.com

	
	//private String password;

     public void enviamails(String asunto,String mensaje,String receptor) 
     { 
    	sendEmail(asunto,mensaje,receptor) ;
     }
	
	

    
    
     
     private Session session;
	private void propiedades()
	{
		
		Properties props = new Properties();
		
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
 
		session = Session.getDefaultInstance(props);
	}
	
	
	
	
	private void sendEmail(String asunto,String mensaje,String receptores)
	{
		 
		propiedades();
		try{
//conostruyo mensaje
			final String  correoRemitente =" aca va el correo ";//TODO crear el correo
			final String passRemitente= " aca va la contrasena del mail";
			
			 MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(correoRemitente));
	      
	            message.addRecipients(Message.RecipientType.TO, receptores);
	            message.setSubject(asunto);
	            message.setText(mensaje);

	            Transport t = session.getTransport("smtp");
	            t.connect(correoRemitente, passRemitente);
	            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));

// Cierra  la conexion del puerto
	            t.close();
	         
				} catch (MessagingException e)
				{
						
						System.out.print("error :  "+e);
				}
		
    }
}
