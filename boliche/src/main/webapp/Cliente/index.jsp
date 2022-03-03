<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="servlets.LoginServlet" %>
<%@ page import= "javax.servlet.http.*" %>
<%@ page import= "entidades.Usuario" %>
<% 
		HttpSession sessio = request.getSession();
		Usuario us =(Usuario)session.getAttribute("usuario");
		if(us==null)
			response.sendRedirect("/");
		else{
		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoliSystem - Bienvenidos</title>
	<%@include file="../templates/libs.html" %>
	<link rel=stylesheet href=../css/sistema-de-disenio/indice.css type="text/css">
	<link rel=stylesheet href=../css/sistema-de-disenio/mensaje.css type="text/css">
	<link rel=stylesheet href=../css/sistema-de-disenio/salir.css type="text/css">
</head>
<body>
<h1>¡Bienvendo al boliche "good vibes", <%= us.getNombre()%>!</h1>

<p class=mensaje>
 Desde  las  3:00 AM estamos esperandolo </p>

<div class=indice>
	<a href="Noches.jsp">Reserva tu entrada aquí!</a>
	<a href="Historial.jsp">Historial</a>
	<a href="../CambioDatosPersonales.jsp">Editar datos</a>
	<a href="../Logout" class=salir>Salir</a>
</div>

<footer>

</footer>
</body>
</html>
<% 
		}
%>