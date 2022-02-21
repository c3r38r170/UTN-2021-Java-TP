package test;
import java.sql.Connection;

import datos.Conexion;
public class pruebaCOnexion 
{
Connection c=null;
public void conexion() 
{
	c= datos.Conexion1.conectar();
	System.out.println(c);
}


public static void main(String[] args) {
	//pruebaCOnexion co = new pruebaCOnexion();
	
	 Conexion con = new Conexion();
	 System.out.println(con);
	
}
}
