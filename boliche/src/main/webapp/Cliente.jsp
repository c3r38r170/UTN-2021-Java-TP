<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="servlets.Login" %>
<%@ page import= "javax.servlet.http.*" %>
<%@ page import= "entidades.Usuario" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoliSystem - Bienvenidos</title>
	<%@include file="templates/libs.html" %>
</head>
<body>
<% 
		HttpSession sessio = request.getSession();
		Usuario us =(Usuario)session.getAttribute("usuarior");
		
%>
<h1>Bienvendos al boliche "good vibes" <%= us.getNombre()%> </h1>

<h3>
 Desde  las  3:00 AM estamos esperandolo </h3>
 
<h3><a href="Entradas.jsp" target="_blank">Reserva tu entrada aqui!</a></h3>

<footer>

</footer>
</body>
</html>