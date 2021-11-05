<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.Date"%>

<%@ page import="java.util.List" %>
<%@ page import="servlets.Login" %>
<%@ page import= "javax.servlet.http.*" %>
<%@ page import= "entidades.Usuario" %>
<%@ page import= "java.util.LinkedList" %>
<%@ page import= "java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
<style>
		body{padding: -1em;font-family: Arial;
		}
		
		#menu{
		position:relative;
			background-color: #000;
			
			
			
				

		}
		
		.tablefix
		{
		margin-botton: 5em;
		margin-top: 2em;
		}
		
		table, th, td 
		{
  				border: 1px solid black;
 				table-layout: fixed;
  				width: 100%;
 				border-collapse: collapse;
 		}
		
		#menu ul{
			list-style: none;
		
			padding: 0;
		}

		#menu ul li{
			display: inline-block;
			
		}

		#menu ul li a{
			color: white;
			display: block;
			padding: 20px 20px;
		
			text-decoration: none;
		}

		#menu ul li a:hover{
			background-color: #42B881;
		}
	
	#fixTextArea
	{
		display:block;
		margin-left: 50%;
		margin-left: 50%;
	}
		
	</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<div id="menu">
		<ul>
			<li><a href="Seguridad.jsp">menu</a></li>
			<li><a href="Listados.jsp">Listado de ingresos </a></li>
			
			
		</ul>
	</div>
	
	
	
	
	<%
		List<Usuario> lisUsus = Usuario.GetUsersForTheNight();
	Iterator<Usuario>it = lisUsus.iterator();
	Usuario us = null;
	
	while(it.hasNext())
	{
		us=it.next();
	
	
	 %>
	 <form action="ListadoDarAcceso" method="post">
	 <div class="tablefix">
    <table class="tableStyle">
    <tbody>
     <tr>
    <th>Nombre</th>
    <th>Nickname</th>
    <th>Correo electronico</th>
    <th> Acciones</th>
  </tr>
    <tr>
    <td><%=us.getNombre() %> </td>
    <td> <%=us.getNickname() %> </td>
    <td>  <%=us.getMail() %></td>
    <td> 
         <input type="submit" name="btn-acceso"   value="Verificado"  />
        
      
    	<input type="submit" name="btn-comentario" value="Comentar"  /> 
    	
    	
     </tr>
  
    </tbody>
    </table>    
   
       <%} %>
       </div>
       </form>
</body>
</html>	