<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
    <%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <LINK href="estiloregistro.css" rel="stylesheet" type="text/css">
<title>registro	Boliche</title>
<script type="text/javascript">

function sendPOST(url,data,returnType=null,otherOptions=null){
	if(!(data instanceof FormData)){
		let tempfd=new FormData;
		for(let key in data){
			let value=data[key];
			if(value != null && !(value instanceof File) && typeof value == "object"){ //I think it works for both objects and arrays
				for(let pair of JSONAsURLEncodedStringIterator(value,key))
					tempfd.append(...pair);
			}else tempfd.append(key,value);
		}
		data=tempfd;
	}
	
	let options={
		method:'POST'
		,body:data
	};
	if(otherOptions)
		Object.assign(options,otherOptions);
	
	return returnType?
		fetch(url,options).then(r=>r[returnType]())
		:fetch(url,options);
}

	addEventListener('DOMContentLoaded',()=>{
		document.forms[0].onsubmit=function(e){
			sendPOST('backend',{nombre:this.nombre.value})
				.then(res=>res.text())
				.then(res=>alert(res));
			e.preventDefault();
			return false;
		};
	});
</script>
</head>
<body>

<section>
<div class="filtro">

<h1 id="title">resgistrate para acceder  a cualquier noche</h1> 
<diV id= "mover">
			<form method="post" action="backend">
<table >

  <tr>
    <td> <label class="color">Nombre </label> </td>
    <td> <input id="input0"  placeholder="ingrese su nombre" name="nombre" required></td>
   
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