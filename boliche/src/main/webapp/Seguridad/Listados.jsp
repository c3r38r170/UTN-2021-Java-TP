<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.Date"%>

<%@ page import="java.util.List" %>
<%@ page import="servlets.LoginServlet" %>
<%@ page import= "javax.servlet.http.*" %>
<%@ page import= "entidades.Usuario" %>
<%@ page import= "entidades.Acceso" %>
<%@ page import= "entidades.Rol" %>
<%@ page import= "java.util.LinkedList" %>
<%@ page import= "java.util.HashMap" %>
<%
	if(session.getAttribute("usuario")==null || ((Usuario)session.getAttribute("usuario")).getRol()!=Rol.Seguridad){
		
		response.sendRedirect("/");
	}else{
	String mensajeNoHay=
		"<tr id=no-hay>"
			+"<td colspan=2> No hay ingresos en espera. </td>"
		+"</tr>";
%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>BoliSystem - Accesos</title>

	<%@include file="../templates/libs.html" %>
	<script type="text/javascript">
	addEventListener('DOMContentLoaded',()=>{
		gEt('usuarios-noche').onclick=function(e){
			let tr=e.target.closest('tr');
			if(!tr || tr.id=='no-hay')
				return;
			
			gEt('modal-form').acceso.value=tr.dataset.id;
			
			gEt('modal-nombre').innerText=tr.children[0].innerText;
			gEt('modal-form').checkbox.checked=false;
			let comentario=gEt('modal-comentario');
			comentario.disabled=true;
			comentario.value='';
			
			let modal=gEt('modal');
			modal.style.display='flex';
		};
		gEt('modal').onclick=function(e){
		    if(e.target==this)
		        this.style.display='none';
		}
		gEt('modal-botones').onclick=function(e){
			let t=e.target;
			if(t.tagName=='SPAN')
				t=t.parentNode;
			if(t.tagName=='BUTTON')
				t.form.accionValue=t.value;
		}
		gEt('modal-form').onsubmit=function(){
			let accesoID=this.acceso.value;
			sendPOST('../accesos',{
				comentario:this.comentario.value.trim()
				,accion:this.accionValue
				,accesoID
			})
				.then(res=>{
					this.firstElementChild.disabled=false;
					if(res.ok){
						toast.success('✔️');
						this.parentNode.style.display='none';
						SqS('tr[data-id="'+accesoID+'"]').remove();
						let tbody=SqS('tbody')
						if(!tbody.children.length)
							tbody.innerHTML=`<%=mensajeNoHay%>`;
					}else toast.error("Ha ocurrido un error, intente nuevamente.");
				});
			this.firstElementChild.disabled=true;
			
			return false;
		}
		gEt('modal-checkbox').onchange=function(){
			gEt('modal-comentario').disabled=!this.checked;
		}
	});
	</script>

	<%@include file="css/index.html" %>
	
	<link rel=stylesheet href=../css/sistema-de-disenio/modal.css type="text/css">
<style>

	/*Modal*/
#modal-comentario{
	resize:vertical;
	display:block;
	margin:.5rem auto;
}
#modal-botones{
	display: flex;
	justify-content: space-evenly;
}
	#modal-botones > button{
  	width: 5rem;
	}
		
#no-hay{
	background:#BBB;
  text-align: center;
}
		
	</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%@include file="templates/nav.html" %>
	
	<div class="tablefix">
    <table class="tableStyle">
    <thead>
     <tr>
    <th>Nombre</th>
    <th>Nickname</th>
  </tr>
  </thead>
    <tbody id=usuarios-noche>
	<%
		List<Acceso> lisUsus = Acceso.pendientesEstaNoche();
	Iterator<Acceso>it = lisUsus.iterator();
	Acceso ac = null;
	if(it.hasNext()){
		while(it.hasNext()){
			ac=it.next();
			Usuario us = new Usuario(ac.getClienteID());
	%>
	    <tr data-id=<%=ac.getID() %>>
		    <td><%=us.getNombre() %> </td>
		    <td> <%=us.getNickname() %> </td>
	    </tr>
	<%
	  }
	}else{
	%>
	<%=mensajeNoHay%>
	<%
	}
	%>
    </tbody>
    </table>    
   
       </div>
       <!-- </form> -->
       <div id=modal>
       	<form id=modal-form>
       		<input type=hidden name=acceso>
       		<fieldset id=modal-fieldset>
       			<h3 id=modal-nombre></h3>
       			<label>
       				<input type=checkbox name=checkbox id=modal-checkbox>
       				<span>Agregar comentario</span>
       			</label>
       			<textarea name=comentario disabled=true id=modal-comentario></textarea>
       			<div id=modal-botones>
       				<button value=1 name=accion class="bttn-pill bttn-success" id=modal-pasa>
       					<span class="fas fa-check"></span>
       				</button>
       				<button value=0 name=accion class="bttn-pill bttn-danger" id=modal-queda>
       					<span class="fas fa-times"></span>
       				</button>
       			</div>
       		</fieldset>
       	</form>
       </div>

</body>
</html>	
<%
	}
%>