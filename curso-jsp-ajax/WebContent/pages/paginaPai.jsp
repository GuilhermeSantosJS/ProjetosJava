<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<h1>Página Pai load JQuery</h1>
<input type="button" value="Carregar página" onclick="carregar();">

<div id="mostrarPaginaFilha"></div> <!-- Local de carregamento da pagina filha -->

</body>

<script type="text/javascript">
function carregar(){
  $("#mostrarPaginaFilha").load('paginaFilha.jsp'); // carrega load pagina em JQuery
	
}

</script>

</body>
</html>