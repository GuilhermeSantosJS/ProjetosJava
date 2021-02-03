<jsp:useBean id="calcula" class="beans.BeanCursoJsp" type="beans.BeanCursoJsp" scope="page" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    

    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="acessoliberado.jsp"><img alt="Inicio" title="Inicio" src="resources/img/inicio.png" width="40px" height="40px"></a>
	<a href="index.jsp"><img alt="Sair" title="Sair" src="resources/img/fechar.png" width="40px" height="40px"></a>
 
 <center style="padding-top: 10%;">
 <h1>Acesso liberado ao sistema!</h1>
 <table>
 <tr>
 <td><a href="salvarUsuario?acao=listartodos"><img src="resources/img/usuario-novo.png" alt="Cadastro de Usuarios" title="Cadastro de Usuarios" width="100px" height="100px"></a></td>
 <td><a href="salvarProduto?acao=listartodos"><img width="100px" height="100px" title="Cadastro de Produto" alt="Cadastro de Produto" src="resources/img/produto-novo.png"> </a></td>
 </tr>
 <tr>
 <td>Cad. Usuario</td>
 <td>Cad. Produto</td>
 </tr>
 </table>
 </center>
 
 
</body>
</html>