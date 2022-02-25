var modal = document.getElementById('modal')
var alta = document.querySelector('.alta').addEventListener('click', function() { modal.style.display = 'flex' })

var submit = document.getElementById('modal-form')
var idmodi;

//var close = document.getElementById('close').addEventListener('click',function(){ modal.style.display='none'})
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
		idmodi=tr.dataset.id;
	let formulario = gEt("modal-form-editar")
	formulario["fecha-noche"].value = tr.children[0].innerText
	modal.style.display = "flex";
	

}
var datosformularios = new FormData();

submit.onsubmit = function(e) {
	var habilitado = this['habilitar'].checked;
	var valuefecha = this['fecha-noche'].value;
	sendPOST('../Noches', {
		fecha: valuefecha,
		estado: habilitado,
		accion: 2
	})
		.then(res => {
			if (res.ok) {
				res.text()
					.then(idfecha => {
						addElement(SqS("tbody"), ["TR", {
							dataset: { id: idfecha }, children: [
								["TD", { innerText: valuefecha }],
								["TD", { innerText: habilitado ? "habilitado" : "No habilitado "  }],
								["TD",{children:[["i",{classList:["fa-solid","fa-pen-to-square"],onclick:function(){editar(this)} }]]}],
								["TD",{children:[["i",{classList:["fa-solid","fa-trash-can"],onclick:function(){eliminar(this)} }]]}],
							]
						}])
					})
			}
			else { }//TODO
		})
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

		}
		)
}


function modificar() {
	console.log(idmodi)
let modal = gEt("modal-form-editar")
	sendPOST('../Noches', {
		id: idmodi
		, accion: 3
		, fecha: modal["fecha-noche"].value
		, estado:  modal["habilitar"].ckecked

	})

}

/*function Agregar()
{
		let modal = gEt("modal-form")
	sendPOST('Noches', {
		
		 accion: 2
		, fecha: modal["fecha-noche"].value
		, estado:  modal["habilitar"].ckecked

	})
}
*/
