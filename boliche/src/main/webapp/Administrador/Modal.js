var modal = document.getElementById('modal')
var alta = document.querySelector('.alta').addEventListener('click',function(){ modal.style.display='flex'})

var submit = document.getElementById('modal-form')

var checkbox = document.getElementById('habilitar');

var close = document.getElementById('close').addEventListener('click',function(){ modal.style.display='none'})


var datosformularios = new FormData();
	
submit.onsubmit=function(e)
{
	
	sendPOST('Noche',{
		fecha:this['fecha-noche'].value,
		estado: this['habilitar'].value
	})
	/*.then(res=> {
		if(res.ok){var toast = new Toasty(); toast.success("You did something good!"); }
			else{}//TODO
	})*/
	return false;
}

