<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.Date"%>

<%@ page import="java.util.List" %>
<%@ page import="servlets.Login" %>
<%@ page import= "javax.servlet.http.*" %>
<%@ page import= "entidades.Usuario" %>
<%@ page import= "java.util.LinkedList" %>
<%@ page import= "java.util.HashMap" %>
<%
	if(session.getAttribute("usuarior")==null || ((Usuario)session.getAttribute("usuarior")).getRol()!=3){
		
		response.sendRedirect("/");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script type="text/javascript" src="https://unpkg.com/@c3r38r170/c3tools"></script>
	<script type="text/javascript">
	addEventListener('DOMContentLoaded',()=>{
		gEt('usuarios-noche').onclick=function(e){
			let tr=e.target.closest('tr');
			if(!tr)
				return;
			
			gEt('modal-form').usuario.value=tr.dataset.id;
			
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
		gEt('modal-form').onsubmit=function(){
			sendPOST('accesos',{
				comentario:this.comentario.value.trim()
				,accion:this.accion.value
				,usuarioID:this.usuario.value
			})
				.then(res=>{
					this.firstElementChild.disabled=false;
					if(res.ok){
						Toastify({
							duration: 1500
							,text:' '
							,stopOnFocus:false
							,className:'fas fa-check'
							,style:{
								background:'lime'
							}
						}).showToast();
						this.parentNode.style.display='none';
						SqS('tr[data-id="'+this.dataset.id+'"]').remove();
					}else{
						Toastify({
							text: "Ha ocurrido un error, intente nuevamente.",
							duration: 1500
							,stopOnFocus:false
							,style:{
								background:'red'
							}
						}).showToast();
					}
				});
			this.firstElementChild.disabled=true;
			
			return false;
		}
		gEt('modal-checkbox').onchange=function(){
			gEt('modal-comentario').disabled=!this.checked;
		}
	});
	</script>


<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<link rel = "stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/bttn.css/0.2.4/bttn.css">
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
		body{padding: -1em;font-family: Arial;
		}
		
		#menu{
		position:relative;
			background-color: #000;
			
			
			
				

		}
		
		.tablefix
		{
		margin-botton: 5em;
		margin-top: 2em;
		}
		
		table, th, td 
		{
  				border: 1px solid black;
 				table-layout: fixed;
  				width: 100%;
 				border-collapse: collapse;
 		}
		
		#menu ul{
			list-style: none;
		
			padding: 0;
		}

		#menu ul li{
			display: inline-block;
			
		}

		#menu ul li a{
			color: white;
			display: block;
			padding: 20px 20px;
		
			text-decoration: none;
		}

		#menu ul li a:hover{
			background-color: #42B881;
		}
	
	#fixTextArea
	{
		display:block;
		margin-left: 50%;
		margin-left: 50%;
	}
		
		/*Modal*/
		#modal{
		  position: absolute;
	    inset: 0px;
	    background: rgba(0, 0, 0, 0.3);
	    display: none;
		}
			#modal-form{
		    margin: auto;
		    background: white;
		    padding: 1.5rem;
		    border-radius: 1rem;
    	}
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
		
	</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<div id="menu">
		<ul>
			<li><a href="Seguridad.jsp">menu</a></li>
			<li><a href="Listados.jsp">Listado de ingresos </a></li>
			
			
		</ul>
	</div>
	
	
	
	
	 <!-- <form action="ListadoDarAcceso" method="post"> -->
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
		List<Usuario> lisUsus = Usuario.GetUsersForTheNight();
	Iterator<Usuario>it = lisUsus.iterator();
	Usuario us = null;
	
	while(it.hasNext())
	{
		us=it.next();
	
	
	 %>
    <tr data-id=<%=us.getID() %>>
    <td><%=us.getNombre() %> </td>
    <td> <%=us.getNickname() %> </td>
     </tr>
  
       <%} %>
    </tbody>
    </table>    
   
       </div>
       <!-- </form> -->
       <div id=modal>
       	<form id=modal-form>
       		<input type=hidden name=usuario>
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
       
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
</body>
</html>	