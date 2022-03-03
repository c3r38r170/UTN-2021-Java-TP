
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%@page import="java.sql.Date"%>
<%@page import="java.util.Iterator"%>
<%@ page import="java.util.List" %>
<%@ page import="servlets.LoginServlet" %>
<%@ page import= "javax.servlet.http.*" %>
<%@ page import= "entidades.Noche" %>
<%@ page import= "java.util.LinkedList" %>
<%@ page import= "java.util.HashMap" %>  
<%@ page import= "entidades.Rol" %>
<%@ page import= "entidades.Usuario" %>

<%
	if(session.getAttribute("usuario")==null || ((Usuario)session.getAttribute("usuario")).getRol()!=Rol.Administrador){
		response.sendRedirect("/");
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administradores</title>


    <%@include file="../templates/libs.html" %>
	<%@ include file ="templates/nav.html" %>

<link rel=stylesheet type=text/css href=../css/sistema-de-disenio/modal.css>
<link rel=stylesheet type=text/css href=css/noches-tabla.css>

<style>

.modal
{
 display: none;  
}
 .boton
{
	width:6em;
	height:3em;
	margin-left:12em;
	margin-top:2.1em;
}

.habilita
{
	margin-left: 4em;
}

.close
{
  
   display: block;
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

</style>


</head>
<body>

<center> <button class="alta" style="margin-bottom:50px;"  >Agregar</button></center>


 <table>

<thead>
<tr> 

		<th> Noche  </th>

		<th> Inscripción </th>
</tr>
</thead>
<tbody>
<%

LinkedList<Noche> noches = Noche.listar();   
Iterator<Noche> it =  noches.iterator();
Noche ns = null;
String estado= null;
while(it.hasNext())
{
	ns= it.next();

%>
<tr data-id=<%= ns.getID() %>>

			<td><%= ns.getFecha() %> </td>
			<% // TODO reemplazar por un tick y ordenar de mayor a menor %>
			<td data-habilitada=<%= ns.isInscripciones()?1:0 %>></td>
									
			<td > <i class="fa-solid fa-pen-to-square" onclick="editar(this)"></i> </td>
			<td><i class="fa-solid fa-trash-can" onclick="eliminar(this)"></i></td>
</tr>
				
 <%} %>
</tbody>

</table> 

 
<br>
<br>



<div class="modal" id="modal" >

<form class="modal-form" id="modal-form">

<fieldset id=modal-fieldset>
 <input type="submit" class="close"  id="close" value="X" style=" display: block;
  width: 25px;
  height: 25px;
  border-radius: 50%;
  background-color: #FFFFFF;
float:right;
"> 
<br>
<label> Ingrese fecha de la fiesta : </label> <input type="date"  name=fecha-noche> <!--  esta linea no la soporta el navegador de eclipse --> <!-- Y bueno no lo uses. -->
<br>
<br>
 <label>Habilitada para inscripción : </label> <input type="checkbox" name = habilitar  >
 <br>

<input type="submit"   class="boton"   value="Aceptar">  





</fieldset>

</form>

</div>





	<div class="modal" id="modal-editar"    >

		<form class="modal-form" id="modal-form-editar">

			<fieldset class=modal-fieldset>
			
				<input type="submit" class="close" value="X"
					style="display: block; width: 25px; height: 25px; border-radius: 50%; background-color: #FFFFFF; float: right;">
				<br> <label> Ingrese Fecha de la fiesta : </label> <input
					type="date" name=fecha-noche>
				<!--  esta linea no la soporta el navegador de eclipse -->
				<br> <br> <label>Habilitda para inscripcion : </label> <input
					type="checkbox" name=habilitar> <br> <input
					type="submit" id="editarBtn" class="boton"
					value="Aceptar">





			</fieldset>

		</form>


	</div>




	<script src="Modal.js" defer></script>
</body>
</html>