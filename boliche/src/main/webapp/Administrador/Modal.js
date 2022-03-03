var modal = document.getElementById('modal')
var alta = document.querySelector('.alta').addEventListener('click', function() { modal.style.display = 'flex' })

function cerrarModalPropio() {
	this.closest(".modal").style.display = "none";
	return false;
}
for (let close of [...SqS(".close", { n: ALL })]) {
	close.onclick = cerrarModalPropio;

}
function editar(icono) {
	var tr = icono.closest("[data-id]")
	let modal = gEt("modal-editar")
	modal.dataset.id = tr.dataset.id
		
	let formulario = gEt("modal-form-editar")
	formulario["fecha-noche"].value = tr.children[0].innerText
	formulario["habilitar"].checked = !!+tr.children[1].dataset.habilitado
	
	modal.style.display = "flex";
}
var datosformularios = new FormData();

document.getElementById('modal-form').onsubmit = function(e) {
	var habilitado = this['habilitar'].checked;
	var valuefecha = this['fecha-noche'].value;
	let ok;
	sendPOST('../Noches', {
		fecha: valuefecha,
		estado: habilitado,
		accion: 2
	})
		.then(res => {
			ok=res.ok;
			return res.text();
					
		})
		.then(idfecha => {
			if(ok){
			// TODO lógica de posicionamiento según fecha, ir de arriba a abajo preguntando si es menor
						SqS("tbody").prepend(createElement(["TR", {
							dataset: { id: idfecha }, children: [
								["TD", { innerText: valuefecha }],
								["TD", { innerText: habilitado ? "Habilitado" : "No habilitado " , dataset:{habilitado:+habilitado} }],
								["TD",{children:[["i",{classList:["fa-solid","fa-pen-to-square"],onclick:function(){editar(this)} }]]}],
								["TD",{children:[["i",{classList:["fa-solid","fa-trash-can"],onclick:function(){eliminar(this)} }]]}],
							]
						}]))
						
				this.parentNode.style.display='none';
		
			}
			else {
				toast.error("Ha ocurrido un error inesperado, reintente más tarde.");
			}//TODO
		});
		
	return false;
}
function eliminar(icono) {


	if (!confirm("Esto no se podra deshacer")) { return false }

	let tr = icono.closest("tr");

	sendPOST('../Noches', {
		id: tr.dataset.id
		, accion: 1


	})
		.then(res => {
			if (res.ok) { tr.remove() }
			else res.text().then(toast.error())
			//TODO even better
		}
		)
}


document.getElementById('modal-form-editar').onsubmit = function() {
	let id=this.parentNode.dataset.id
		,fecha=this["fecha-noche"].value
		,estado=this["habilitar"].checked;
	sendPOST('../Noches', {
		id
		, accion: 3
		, fecha
		, estado

	})
		.then(res => {
			if(res.ok){
				let editado=SqS('tr[data-id="'+id+'"]');
				editado.children[0].innerText=fecha;
				editado.children[1].dataset.habilitado=+estado;
				editado.children[1].innerText=estado?'Habilitado':'No habilitado';
		
				this.parentNode.style.display='none';
			}
			else {
				res.text().then(txt=>{		
					toast.error(txt);
				})
			}//TODO
		});
		
		return false;
}
