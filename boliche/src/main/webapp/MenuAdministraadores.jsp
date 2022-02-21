<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%@page import="java.sql.Date"%>

<%@ page import="java.util.List" %>
<%@ page import="servlets.Login" %>
<%@ page import= "javax.servlet.http.*" %>
<%@ page import= "entidades.Noche" %>
<%@ page import= "java.util.LinkedList" %>
<%@ page import= "java.util.HashMap" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administradores</title>



    <%@include file="templates/libs.html" %>



<style>



#modal
{
 
  height: 150px;
  display: flex;
  justify-content: center;
  align-items: center;
 display: none;
   
}

#modal-form
{
width:30em;
height: 25vh;
  position: absolute;
  
   
}

 #boton
{
	width:6em;
	height:3em;
	margin-left:12em;
	margin-top:2.1em;
}

#habilita
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




 <table>

<thead>
<tr> 

		<th> Noche  </th>

		<th> habilitada </th>
</tr>
</thead>
<tbody>
<% LinkedList<Noche> noches = Noche.listar();   
Iterator<Noche> it =  noches.iterator();
Noche ns = null;
String estado= null;
while(it.hasNext())
{
	ns= it.next();

%>
<tr data-id= <%= ns.getId()%> >

			<td><%= ns.getFecha() %> </td>
			<% if( ns.isInscripciones()==true){estado= "Habilitado";} else{estado= " No habilitado";} %>
			<td> <%= estado %>  </td>
									
			
</tr>
				
 <%} %>
</tbody>

</table> 

<button class="alta"  >Agregar</button>
 <button>Modificar</button>
 <button> Eliminar</button>
 
<br>
<br>



<div id="modal">

<form id="modal-form">

<fieldset id=modal-fieldset>
 <input type="submit" id="boton"  value="X" style=" display: block;
  width: 25px;
  height: 25px;
  border-radius: 50%;
  background-color: #FFFFFF;
float:right;
" id="close"> 
<br>
<label> Ingrese Fecha de la fiesta : </label> <input type="date"  name=fecha-noche> <!--  esta linea no la soporta el navegador de eclipse --> 
<br>
<br>
 <label>Habilitda para inscripcion : </label> <input type="checkbox" name = habilitar  >
 <br>

<input type="submit" id="boton"  value="Aceptar">  





</fieldset>

</form>

</div>




<script src="Modal.js"></script>
</body>
</html>