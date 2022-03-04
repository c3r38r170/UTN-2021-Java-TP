<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "entidades.Rol" %>
<%@ page import= "entidades.Usuario" %>
<%
	if(session.getAttribute("usuario")==null || ((Usuario)session.getAttribute("usuario")).getRol()!=Rol.Administrador){
		response.sendRedirect("/");
	}else{
%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificacion de empledados</title>
 <%@include file="../templates/libs.html" %>

	<style>
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
	
	#fila{
	display:inline-block;
	margin-left:  9em;
	
	}
	button
	{
		margin-left: 1em;
		width:80px;
		height: 2em;
		background-color: 0096FF !important; 
	}
	#selec
	{
		width:20em;
		font-size: 1em;
	}
	
	.submi
	{
		width:8em;
	}
	
	i
	{
	width:30 px;
	heigth:2em;
	}
	
	</style>
</head>
<body>
<body>
				<h1 id="Titulo">Empleados</h1>
 <form action="../Empleados" id="formularioAlta" method="post"> 
 <fieldset>
 
 
 <label id="lblnombre" >Ingrese nombre completo :  </label> <input type="text"  minlength="5" name="nombre" class="ocultar"  id="nombre" >
 
 <label id="lblnickname" >Ingrese nickname:  </label> <input type="text"  minlength="3" name="nickname"   class="ocultar"  id="nickname"    >
 <br>
 <label id="lblmail">Ingrese email:   </label> <input type="email"  minlength="8" name="email"  class="ocultar"  id="mail" >
 <br>
 <label id="lblcontra" >Ingrese contraseña:  </label> <input type="text"  minlength="8" name="ContreasenaActual"  class="ocultar"  id="contrasena"  >
 <br>


 
 
 
 <label id="lblselec" >Seleccione Cargo actual  </label>
 
 <select name="select" id="selec" class="ocultar" >
  <option value="1">Administrador</option>
  <option value="2" selected>Seguridad</option>
 
</select>
 
<<<<<<< HEAD
 <button type="submit"class="submi" value=1 name="subm" > <i class="fa-solid fa-circle-check"></i></button> 
  </fieldset>
 </form>
 
 
 
 
  <form action="../Empleados" id="formularioModificacion" method="post"> 
 <fieldset>
 
 
 <label id="lblnombre" >Ingrese nombre completo :  </label> <input type="text"  minlength="5" name="nombre" class="ocultar"  id="nombre" >
 
 <label id="lblnickname" >Ingrese nickname:  </label> <input type="text"  minlength="3" name="nickname"   class="ocultar"  id="nickname"    >
 <br>
 <label id="lblmail">Ingrese email:   </label> <input type="email"  minlength="8" name="email"  class="ocultar"  id="mail" >
 <br>
 <label id="lblcontra" >Ingrese contraseña:  </label> <input type="text"  minlength="8" name="ContreasenaActual"  class="ocultar"  id="contrasena"  >
 <br>


 
 
 
 <label id="lblselec" >Seleccione Cargo actual  </label>
 
 <select name="select" id="selec" class="ocultar" >
  <option value="1">Administrador</option>
  <option value="2" selected>Seguridad</option>
 
</select>
 
 <button type="submit"class="submi" value=2 name="subm" > <i class="fa-solid fa-circle-check"></i></button>
  </fieldset>
 </form>
 
 
 
 
 
 
  <form action="../Empleados" id="formularioBaja" method="post"> 
 <fieldset>
 
 
 <label id="lblnombre" >Ingrese nombre completo :  </label> <input type="text"  minlength="5" name="nombre" class="ocultar"  id="nombre" >
 
 
 <label id="lblselec" >Seleccione Cargo actual  </label>
 
 <select name="select" id="selec" class="ocultar" >
  <option value="1">Administrador</option>
  <option value="2" selected>Seguridad</option>
 
</select>
 <button type="submit"class="submi" value=3 name="subm" > <i class="fa-solid fa-circle-check"></i></button>
  </fieldset>
 </form>
 
 
 
 
 
=======
 <input  type="submit"   id="submi" >
>>>>>>> 780235913e9525fa4feecce59547c4a400f2b2bf
 
<div id="fila">
 <button class="fa-solid fa-plus" value=1  name="btn" id="alta"  onclick="mostrarAlta()" ></button>
  <button class="fa-solid fa-arrows-rotate" value=2  name="btn" id="modificar" onclick="mostrarModificacion()"  ></button>
  <button class="fa-solid fa-trash" value=3 name="btn" id="baja"   onclick="mostrarEliminar()"></button></div>
 

  
 

 
 <script src="EmpleadosABM.js"></script>
</body>
</html>
<%
	}
%>