SqS('table').onchange=(e)=>{
	let t=e.target;
	if(t.tagName=='INPUT'){
		t.disabled=true;
		sendPOST('../Bloquear',{clienteID:t.value,habilitado:t.checked})
			.then(res=>{
				if(!res.ok){
					t.checked=!t.checked;
					res.text()
						.then(txt=>toast.error(txt.length>50?'Ha ocurrido un error inesperado, reintente más tarde.':txt));
				}
					t.disabled=false;
			});
	}
}

gEt('busqueda').oninput=function(){
	for(let tr of [...SqS('tbody').children])
		tr.style.display=(tr.innerText.indexOf(this.value)==-1)?'none':'';
}