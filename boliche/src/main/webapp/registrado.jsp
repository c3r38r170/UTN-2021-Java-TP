<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>Usuario Registrado</title>
<script type="text/javascript" src="https://unpkg.com/@c3r38r170/c3tools"></script>
<script type="text/javascript">
	var segundos=10;
	addEventListener('DOMContentLoaded',()=>{
		setInterval(()=>gEt('segundos').innerText=--segundos,1000);
		// TODO mandar a login
		setTimeout(()=>W.location='/',10000);
	});
</script>

</head>
<body>

<h1>¡Felicitaciones!</h1>
<p>Ha sido registrado, en <span id=segundos>10</span> segundos será redirigido al inicio.</p>

</body>

</html>