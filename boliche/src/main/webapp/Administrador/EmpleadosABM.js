var nombre = document.getElementById("nombre")
var nickname = document.getElementById("nickname")
var mail = document.getElementById("mail")
var contrasena = document.getElementById("contrasena")
var select = document.getElementById("selec")



var submi = document.getElementById("submi").disabled = true

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

	
	 submi.disabled = true
		
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

var baja = document.getElementById("modificar").onclick= function()
{
	
	
	lblselec.style.display= "block";	
	lblcontra.style.display= "block";	
	lblmail.style.display= "block";	
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
	
}