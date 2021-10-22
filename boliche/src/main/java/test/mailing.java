package test;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
public class mailing {

	public static void main(String[] args)  
	{
		 try {
	            /*Envia_Mails m = new Envia_Mails();
	            m.enviar("sabudvicco@gmail.com", "hola","soy un mensaje desde java");*/
			 
			 //Mailer mailer = ;
			 
			 Email email = EmailBuilder.startingBlank()
				    .from("lollypop", "labarbahipolito3@gmail.com")
				    .to("C. Cane", "sabudvicco@gmail.com")
				    .withSubject("hey")
				    .withPlainText("We should meet up! ;)")
				    .buildEmail();

			 Mailer m=MailerBuilder
       .withSMTPServer("smtp.gmail.com", 25, "labarbahipolito3@gmail.com", "operacion9").buildMailer();
       m.sendMail(email);

	            System.out.println("Se ha enviado!!");
	        } catch (Exception ex) {
	            System.out.println(ex);
	        }
	}

}
