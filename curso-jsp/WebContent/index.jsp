<jsp:useBean id="calcula" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" scope="page" />
	

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
<link  rel="stylesheet" href="resources/css/estilo.css">

</head>
<body>

	<div class="login-page">
          <center><h3>Projeto Didático</h3></center><br/>
          <center><h1>JSP + Servlet + JDBC</h1></center>
          <center><span><b>USUÁRIO:</b> admin <br/> <b>SENHA:</b> admin</span></center><br/>
           <div class="form">
			<form action="LoginServlet" method="post" class="login-form">
				Login: <input type="text" id="login" name="login"> <br />
				Senha: <input type="password" id="senha" name="senha"> <br />
				<button type="submit" value="Logar">Logar</button>
			</form>
			
	</div>
	<center><h3><a style="text-decoration: none;" href="https://www.javaavancado.com/">Formação Java Web</a></h3></center>
		</div>
</body>
</html>