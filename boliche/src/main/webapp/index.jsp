<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "entidades.Rol" %>
<%@ page import= "entidades.Usuario" %>
<%
	if(session.getAttribute("usuario")!=null){
		String ruta;
		switch(((Usuario)session.getAttribute("usuario")).getRol()){
		case Cliente:
			ruta="Cliente";
			break;
		case Seguridad:
			ruta="Seguridad";
			break;
		case Administrador:
			ruta="Administrador";
			break;
		default:
			ruta="Logout";
			break;
		}
		response.sendRedirect(ruta);
	}else{
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>BoliSystem - Acceso</title>

	<%@include file="templates/libs.html" %>
	
	<link rel=stylesheet href=css/sistema-de-disenio/modal.css type="text/css">
	<style type="text/css">

#modal-form > fieldset{
   display: grid;
   gap: .5rem;
}
#main-botones{
	display:flex;
}
	#registrarse{
		margin:auto;
	}

	</style>
	
	<script type="text/javascript">
		/*funciones registro*/
		function abrirRegistro(){
			gEt('modal').style.display='flex';
		}
		function cerrarRegistro(){
			gEt('modal').style.display='none';
		}
	
		/* handlers*/
		addEventListener('DOMContentLoaded',()=>{
			gEt('main-form').onsubmit=function(){
				let estadoOk;
				let fs=this.parentNode;
				fs.disabled=true;
				sendPOST('Login',{
					username:this.user.value,
					password:this.pass.value
				})
					.then(res=>{
						estadoOk=res.ok;
						return res.text();
					})
					.then(texto=>{
						if(estadoOk)
							W.location=texto;
						else toast.error(texto);
					})
					.finally(()=>fs.disabled=false);
				return false;
			}
			gEt('registrarse').onclick=abrirRegistro;
			gEt('modal').onclick=function(e){
				if(e.target==this)
					cerrarRegistro();
			}
			gEt('modal-form').onsubmit=function(){
				let ok;
				let fs=this.firstElementChild;
				fs.disabled=true;
				sendPOST('Registro',{
					usuario:this['modal-user'].value
					,contrasena:this['modal-pass'].value
					,nickname:this['modal-name'].value
					,correo:this['modal-mail'].value
				})
					.then(res=>{
						ok=res.ok;
						return res.text();
					})
					.then(texto=>{
						if(ok){
							toast.success(texto);
							cerrarRegistro();
							this.reset();
						}else toast.error(texto);
					})
					.finally(()=>fs.disabled=false);
				return false;
			};
		})
	</script>
</head>
<body>

<fieldset id=main>
	<form id=main-form>
		<label for="user">Nombre de usuario</label>
		<input type="text" name="user" id="user" pattern="^[^\s]*$" minlength=4 required>
		<label for="pass">Contraseña</label>
		<input type="password" name="pass" id="pass" minlength=8 required>
	</form>
	<div id=main-botones>
		<button form=main-form class="bttn-pill bttn-primary">Entrar</button>
		<button id=registrarse class="bttn-pill bttn-primary">Registrarse</button>
	</div>
</fieldset>

<div id=modal>
	<form id=modal-form>
		<fieldset>
			<label for="modal-name">Nombre y Apellido</label>
			<input type="text" name="modal-name" id="modal-name" minlength=4 required>
			<label for="modal-user">Nombre de usuario</label>
			<input type="text" name="modal-user" id="modal-user" pattern="^[^\s]*$" minlength=4 required>
			<label for="modal-pass">Contraseña</label>
			<input type="password" name="modal-pass" id="modal-pass" minlength=8 required>
			<label for="modal-mail">Correo Electrónico</label>
			<input type="email" name="modal-mail" id="modal-mail" required>
			<button id=modal-registrarse class="bttn-pill bttn-primary">Registrarse</button>
		</fieldset>
	</form>
</div>

</body>
</html>
<%
	}
%>