package test;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import datos.Conexion;
import datos.PSParameter;
import datos.PSParameter.Types;
import entidades.Usuario;
import utils.Correo;

public class TestClass {
public static void main(String[] args) {
//TODO ponerle de nombre a la casilla el nombre de la app + " - Registro"
	 /*Email email = EmailBuilder.startingBlank()
		    .from(null, "labarbahipolito3@gmail.com")
		    .to(null, "sabudvicco@gmail.com")
		    .withSubject("aaa")
		    .withHTMLText("asdasd")
		    .buildEmail();

	 Mailer m=MailerBuilder
 .withSMTPServer("smtp.gmail.com", 25, "labarbahipolito3@gmail.com", "operacion9").buildMailer();
 m.sendMail(email);*/
			var c=new Conexion();

	     try {
				ResultSet rs = c.preparedSelectStatement
				        		(
				          "select ID,usuario, contraseña, nickname, correo, verificado,rolID from usuarios  where usuario = ? AND verificado=1  ;"
				                  ,new PSParameter("patomano",Types.STRING)
				                 );

	       if(rs.next()) 
	     {
		       var usuario = new Usuario(
		      		 rs.getInt(1)
		      		 ,rs.getString(2)
		      		 ,rs.getString(3)
		      		 ,rs.getString(4)
		      		 ,rs.getString(5)
		      		 ,rs.getBoolean(6)
		      		 ,rs.getInt(7)
		      	);
	    	System.out.println(usuario);   
	     }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
}
