<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
    <%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <LINK href="estiloregistro.css" rel="stylesheet" type="text/css">
<title>registro	Boliche</title>
</head>
<body>

<section>
<div class="filtro">

<h1 id="title">resgistrate para acceder  a cualquier noche</h1> 
<diV id= "mover">
			<form method="post">
<table >

  <tr>
    <td> <label class="color">Nombre </label> </td>
    <td> <input id="input0"  placeholder="ingrese su nombre"></td>
   
  </tr>



  <tr>
    <td><label class="color">Apellido</label></td>
    <td><input id="input1"  placeholder="ingrese suapellido"></td>
    
  </tr>
  
  
  
   <tr>
    <td><label class="color">Edad</label></td>
    <td><input id="input2"  placeholder="ingrese su edad"></td>
  </tr>
  
  
  
  
  <tr>
    <td><label class="color">Email</label></td>
    <td><input type="email" id="input3"  placeholder="ingrese su email"></td>
  </tr>
  
  
  
  
  <tr>
    <td><label class="color">Nick Name</label></td>
    <td><input type="text" id="input4"  placeholder="ingrese su nick name"></td>
  </tr>
  
  
  
  <tr>
    <td><label class="color">Clave</label></td>
    <td><input type="password" id="input"  placeholder="ingrese su clave"></td>
  </tr>
  
  
  
	  <tr>
	 <td>  <label  class="color"> Elija Su Genero</label> </td>
   		<td> <select name="genero">
  <option selected>Seleccione</option>
      <option>Hombre</option>

      <option >Mujer</option>

      <option>Actimel</option>

    </select>		
    </td> 
  </tr>



<tr>
<td> </td>
<th><input id="desplaza" type="submit" value="Registrarse" > <th>

</tr>
</table>						



</form>
</div>

</div>
</section>
</body>
</html>