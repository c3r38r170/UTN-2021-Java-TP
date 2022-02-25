var indice=SqS('.indice')

if(indice){
	indice.onclick=e=>{
		let target=e.target;
		if(target.tagName!='BUTTON')
			return;
		
		gEt('modal-titulo-noche').innerText=target.innerText;
		gEt('modal-noche').value=target.value;
		
		let modal=gEt('modal');
		modal.style.display='flex';
	}
	gEt('modal').onclick=function(e){
	    if(e.target==this)
	        this.style.display='none';
	}
	gEt('modal-form').onsubmit=function(){
	 let nocheID=this['modal-noche'].value;
		sendPOST('../Inscribir',{nocheID})
			.then(res=>{
				if(res.ok){
					toast.success('Ya te inscribiste, ¡no faltes!');
					SqS('[value="'+nocheID+'"]').remove();
					if(!indice.children.length)
						indice.outerHTML='<p class=mensaje>¡No hay noches disponibles! Revisá más tarde.</p>';
				}else{
					toast.error("Ha ocurrido un error, intente nuevamente.");
				}
				this.parentNode.click();
			});
		
		return false;
	}
}