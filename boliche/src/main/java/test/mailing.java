package test;
import java.io.IOException;
import test.Envia_Mails;
import java.security.InvalidParameterException;
import javax.mail.MessagingException;
public class mailing {

	public static void main(String[] args)  
	{
		 try {
	            Envia_Mails m = new Envia_Mails();
	            m.enviar("labarbahipolito@gmail.com", "hola","soy un mensaje desde java");

	            System.out.println("Se ha enviado!!");
	        } catch (Exception ex) {
	            System.out.println(ex);
	        }
	}

}
