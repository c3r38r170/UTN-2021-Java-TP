<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@ page import="java.util.List" %>
<%@ page import= "entidades.Usuario" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import= "entidades.Noche" %>
<%
	if(session.getAttribute("usuario")==null || ((Usuario)session.getAttribute("usuario")).getRol()!=4){
		
		response.sendRedirect("/");
	}
	Usuario usuarioActual=((Usuario)session.getAttribute("usuario"));
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>BoliSystem - Noches</title>

<%@include file="../templates/libs.html" %>

<link rel=stylesheet href=../css/sistema-de-disenio/indice.css type="text/css">
<link rel=stylesheet href=../css/sistema-de-disenio/modal.css type="text/css">
<link rel=stylesheet href=../css/sistema-de-disenio/mensaje.css type="text/css">

<link rel=stylesheet href=css/noches.css type="text/css">
	
<script src="js/noches.js" defer></script>
	
</head>
<body>

<h1>Elegí a qué noche querés anotarte.</h1>
<%

	List<Noche> nochesHabilitadas = Noche.habilitadasPara(usuarioActual.getID());
	Iterator<Noche>it = nochesHabilitadas.iterator();
	Noche nox = null;
	
	if(it.hasNext()){
		%><div class=indice><%
		do{
			nox=it.next();
			%><button value=<%=nox.getId()%>><%=new SimpleDateFormat("dd/MM/yyyy").format(nox.getFecha()) %></button><%
		}while(it.hasNext());
		%></div><%
	}else{
		%><p class=mensaje>¡No hay noches disponbles! Revisá más tarde.</p><%
	}
%>

<div id=modal>
	<form id=modal-form>
		<input type=hidden name=noche id=modal-noche>
		<fieldset id=modal-fieldset>
			<h3 id=modal-titulo>¿Querés anotarte para la noche del <span id=modal-titulo-noche></span>?</h3>
			<div id=modal-botones>
				<button class="bttn-pill bttn-success" id=modal-pasa>
					<span class="fas fa-check"></span>
				</button>
				<button type=button class="bttn-pill bttn-danger" onclick="gEt('modal').click()">
					<span class="fas fa-times"></span>
				</button>
			</div>
		</fieldset>
	</form>
</div>

</body>
</html>