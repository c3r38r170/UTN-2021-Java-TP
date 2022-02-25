<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="entidades.Usuario" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<%
	HttpSession sesionUsuario = (HttpSession) request.getSession();
	Usuario usuario=(Usuario) sesionUsuario.getAttribute("usuario"); 
	
	if(usuario.getRol()==1||usuario.getRol()==2)
	{%>
	<style >
	.rol
	{
		visibility: visible;
	} </style>
	<%} %>
	

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
	.rol
	{
		visibility: hidden;
	}
	</style>


 <%@include file="../templates/libs.html" %>
 
</head>

<body>
				<h1 id="Titulo">😈Actualizar datos 😈</h1>
 <form action="Cambios" id="formulario">
 <fieldset>
 <label>Ingrese nuevo nombre completo :  </label> <input type="text"  minlength="5" name="nombre"  value ="<%=usuario.getNombre() %> ">
 
 <label>Ingrese nuevo nickname🍆:  </label> <input type="text"  minlength="5" name="nickname" value ="<%=usuario.getNickname() %> "      >
 <br>
 <label>Ingrese nuevo email asociado:   </label> <input type="email"  minlength="8" name="email"  value ="<%=usuario.getMail() %> " >
 <br>
 <label>Ingrese contraseña actual:  </label> <input type="text"  minlength="8" name="ContreasenaActual" value ="<%=usuario.getContraseña() %> "   >
 <br>
 <label>Ingrese nueva contraseña:  </label> <input type="text"  minlength="8" name="nuevaContrasena">
 <br>
 
 <label class="rol">Ingrese Rol del nuevo usuario:  </label> <input type="number" min="1" max="4"  name="rol" class="rol">
 
 <input type= "submit" id="cambiar">
 
 </fieldset>
 </form>

</body>
</html>