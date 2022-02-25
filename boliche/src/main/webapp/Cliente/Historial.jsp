<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "entidades.Usuario" %>
<%@page import="java.util.Iterator"%>
<%@ page import="java.util.List" %>
<%@ page import= "entidades.Acceso" %>
<%@ page import= "entidades.Estado" %>
<%@ page import= "entidades.Rol" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	if(session.getAttribute("usuario")==null || ((Usuario)session.getAttribute("usuario")).getRol()!=Rol.Cliente){
		
		response.sendRedirect("/");
	}
	Usuario usuarioActual=((Usuario)session.getAttribute("usuario"));
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>BoliSystem - Historial</title>

<%@include file="../templates/libs.html" %>

<link rel=stylesheet href=../css/sistema-de-disenio/indice.css type="text/css">
<link rel=stylesheet href=../css/sistema-de-disenio/mensaje.css type="text/css">

<style>

.indice small{
	display:block;
}

<%

List<Estado> estados= Estado.listar();
Iterator<Estado> estIt = estados.iterator();
Estado est=null;
while(estIt.hasNext()){
	est=estIt.next();
%>
.estado<%=est.getID()%>{
	background:#<%=est.getColor()%>80;
}
.estado<%=est.getID()%> > span:before{
	content:'<%=est.getDescripcion()%>';
}
<%
}

%>
</style>
	
</head>
<body>

<h1>Registro</h1>

<%

	List<Acceso> historial = Acceso.historialDe(usuarioActual.getID());
	Iterator<Acceso>it = historial.iterator();
	Acceso ac = null;
	
	if(it.hasNext()){
		%><div class=indice><%
		do{
			ac=it.next();
			%><div class="estado<%=ac.getEstadoID()%>">
				<h2><%=new SimpleDateFormat("dd/MM/yyyy").format(ac.getNoche().getFecha()) %></h2>
				<small><%=ac.getHora()%></small>
				<span></span>
			<%
				String comentario=ac.getComentario();
				if(comentario!=null){
					%>
					<p><%=comentario%></p>
					<%
				}
			%>
			</div><%
		}while(it.hasNext());
		%></div><%
	}else{
		%><p class=mensaje>Todavía no viniste, chinwenwencha. 😉</p><%
	}
%>

</body>
</html>