
/*
var nombre = document.getElementById("nombre")
var nickname = document.getElementById("nickname")
var mail = document.getElementById("mail")
var contrasena = document.getElementById("contrasena")
var select = document.getElementById("selec")


var formulario = document.getElementById("formulario")



//var submi = document.getElementById("submi").style.display= "none";	





var lblnombre =  document.getElementById("lblnombre")
var lblnickname =  document.getElementById("lblnickname")
var lblmail= document.getElementById("lblmail")
var lblcontra= document.getElementById("lblcontra")
var lblselec= document.getElementById("lblselec")






var alta = document.getElementById("alta").onclick= function()
{
	lblselec.style.display= "block";	
	lblcontra.style.display= "block";	
	lblmail.style.display= "block";	
	lblnickname.style.display= "block";	
	lblnombre.style.display= "block";	
	
	nombre.style.display= "block";	
	nickname.style.display= "block";
	mail.style.display= "block";
	contrasena.style.display= "block";
	select.style.display= "block";

<<<<<<< HEAD
   
			

=======
	
	 submi.disabled = true
>>>>>>> 780235913e9525fa4feecce59547c4a400f2b2bf
		
	return false
		

 }

var baja = document.getElementById("baja").onclick= function()
{
	lblnombre.style.display= "block";	
	lblselec.style.display= "block";	
	
	nombre.style.display= "block";	
	select.style.display= "block";
	//submi.style.display= "block";
	 submi.disabled = true
	return false
}



var modificar= document.getElementById("modificar").onclick= function()
{
	
	
	lblselec.style.display= "block";	
	lblcontra.style.display= "block";	
	lblmail	
	lblnickname.style.display= "block";	
	lblnombre.style.display= "block"
	
	
	nombre.style.display= "block";	
	nickname.style.display= "block";
	mail.style.display= "block";
	contrasena.style.display= "block";
	select.style.display= "block";
	//submi.style.display= "block";
	 submi.disabled = true
	return false
<<<<<<< HEAD
}*/



var alta = document.getElementById('formularioAlta').style.display="none"
var modificacion = document.getElementById('formularioModificacion').style.display="none"
var baja = document.getElementById('formularioBaja').style.display="none"

function mostrarAlta()
{ 
	document.getElementById('formularioModificacion').style.display="none"
    document.getElementById('formularioBaja').style.display="none"

	 document.getElementById('formularioAlta').style.display="block"
}

function mostrarModificacion()
{
	 document.getElementById('formularioBaja').style.display="none"

	 document.getElementById('formularioAlta').style.display="none"
	document.getElementById('formularioModificacion').style.display="block"
}

function mostrarEliminar()
{
	 document.getElementById('formularioAlta').style.display="none"
	document.getElementById('formularioModificacion').style.display="none"
	document.getElementById('formularioBaja').style.display="block"
}


=======
	
}
>>>>>>> 780235913e9525fa4feecce59547c4a400f2b2bf
