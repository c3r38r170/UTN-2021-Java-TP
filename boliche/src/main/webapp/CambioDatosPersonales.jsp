<%@page import="java.text.NumberFormat.Style"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entidades.Usuario" %>
 <%@ page import="entidades.Rol" %>
<%
	if(session.getAttribute("usuario")==null){
		response.sendRedirect("/");
	}
%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style >
#Titulo
	{
	margin-left: 4.2em;
	}
	
	#formulario input
	{
		margin-left:1em;
		width:80%;
		font-size:30px;
	}
	
	#cambiar{margin-left:8em !important; 
	width:40% !important;
	margin-left:5em !important;
	margin-top:2em;}
	#formulario label
	{
	font-size: 20px;
	font-weight: 900;
	}
	.nuevac{display:block;}
	
	.rol
	{
		display: none;
		
	} 
</style>

	<%
	Usuario usuario=(Usuario) session.getAttribute("usuario"); 
	
	

	
	if(usuario.getRol()==Rol.Administrador)
	{%>
	
	<style >
	.rol
	{
		visibility: visible;
		
	} 
	
	.nuevac
	{
		display:none;
	}
	
	</style>
	<%} %>
	



 <%@include file="../templates/libs.html" %>
 
</head>

<body>
				<h1 id="Titulo">😈Actualizar datos 😈</h1>
 <form action="Cambios" id="formulario" method="post">
 <fieldset>
 <label>Ingrese nuevo nombre completo :  </label> <input type="text"  minlength="5" name="nombre"  value ="<%=usuario.getNombre()%>">
 
 <label>Ingrese nuevo nickname🍆:  </label> <input type="text"  minlength="3" name="nickname" value ="<%=usuario.getNickname()%>"      >
 <br>
 <label>Ingrese nuevo email asociado:   </label> <input type="email"  minlength="8" name="email"  value ="<%=usuario.getMail()%>" >
 <br>
 <label>Ingrese contraseña actual:  </label> <input type="text"  minlength="8" name="ContreasenaActual" value ="<%=usuario.getContraseña() %> "   >
 <br>
 <label class="nuevac">Ingrese nueva contraseña:  </label> <input type="text"  minlength="8" name="nuevaContrasena" class="nuevac">
 <br>
 
 <label class="rol">Ingrese Rol del nuevo usuario:  </label> <input type="number" min="1" max="4"  name="rol" class="rol">
 
 <input type= "submit" id="cambiar">
 
 </fieldset>
 </form>

</body>
</html>