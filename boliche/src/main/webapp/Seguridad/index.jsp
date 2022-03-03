<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "entidades.Rol" %>
<%@ page import= "entidades.Usuario" %>
<%
	if(session.getAttribute("usuario")==null || ((Usuario)session.getAttribute("usuario")).getRol()!=Rol.Seguridad){
		response.sendRedirect("/");
	}
%><!DOCTYPE html>
<html>
<head>
	
	<%@include file="../templates/libs.html" %>
	<%@include file="css/index.html" %>
	
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>

	<%@include file="templates/nav.html" %>

</body>
</html>