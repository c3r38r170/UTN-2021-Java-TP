<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@ page import= "entidades.Usuario" %>
<%@ page import= "entidades.Rol" %>
<%@ page import= "logica.Usuarios" %>
<%@ page import= "java.util.LinkedList" %>

<%
	if(session.getAttribute("usuario")==null || ((Usuario)session.getAttribute("usuario")).getRol()!=Rol.Administrador){
		response.sendRedirect("/");
	}else{
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administradores - Bloquear</title>

    <%@include file="../templates/libs.html" %>
	<%@ include file ="templates/nav.html" %>

	<script src="js/bloquear.js" defer></script>
	
</head>
<body>

<input list=usuarios type=text id=busqueda>


 <table>

<thead>
<tr> 
		<th> Cliente</th>

		<th>Habilitado</th>
</tr>
</thead>
<tbody>
<%

LinkedList<Usuario> usuarios = Usuarios.clientes();
Iterator<Usuario> it =  usuarios.iterator();
Usuario u = null;
while(it.hasNext())
{
	u= it.next();

%>
<tr>

			<td><%= u.getNickname() %> </td>
			<td><input type=checkbox value=<%= u.getID() %> <%= u.isVerificado()?"checked":"" %>></td>
</tr>
				
 <%} %>
</tbody>

</table> 

</body>
</html>
<%
	}
%>