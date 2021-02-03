<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">


</head>
<body>
<a href="acessoliberado.jsp"><img alt="Inicio" title="Inicio" src="resources/img/inicio.png" width="40px" height="40px"></a>
	<a href="index.jsp"><img alt="Sair" title="Sair" src="resources/img/fechar.png" width="40px" height="40px"></a>
	<center>
		<h1>Cadastro de Telefones</h1>
		<h3 style="color:orange"></h3>
	</center>
	<center><h2 style="color:orange">${msg}</h2></center>
	<div class="form-page">
		<form action="salvarTelefones" method="post" id="formUser"
			class="form-login" onsubmit=" return validarCampos()? true : false">
			<ul class="form-style-1">
				<li>

					<table>
						<tr>
							<td>Usuario:</td>
							<td><input type="text"  id="user" name="user"
								 class="field-long" value="${userEscolhido.id}"></td>
								 
						    <td><input type="text" id="nome" name="nome"
								 class="field-long" value="${userEscolhidoNome.nome}"></td>
								
							</tr>	
							
							
							<tr>
							<td>Número:</td>
							<td><input type="text" id="numero" name="numero"></td>
							<td>
							<select id="tipo" name="tipo" style="width: 175px">
							<option>Casa</option>
							<option>Trabalho</option>
						    <option>Celular</option>
						    <option>Outros</option>  
						    
							</select>
							</td>
							</tr>
							
							
						<tr>
						    <td></td>
						    
							<td>
							<input type="submit" value="Salvar" style="width: 184px">
							</td>
							<td>
							<input type="submit" value="Voltar" onclick="document.getElementById('formUser').action = 'salvarTelefones?acao=voltar'" style="width: 175px">
							</td>
							
						</tr>

					</table>
				</li>
			</ul>

		</form>
	</div>

	<div class="container">
		<table class="responsive-table">

			<caption>Usuários Cadastrados</caption>
			<tr>
				<th>Id</th>
				<th>Numero</th>
				<th>Tipo</th>
				<th>Delete</th>
			</tr>

			<c:forEach items="${telefones}" var="fone">
				<tr>
					<td><c:out value="${fone.id}"></c:out></td>
					<td><c:out value="${fone.numero}"></c:out></td>
					<td ><c:out value="${fone.tipo}"></c:out></td>
					<td><a href="salvarTelefones?user=${fone.usuario}&acao=deleteFone&foneId=${fone.id}" onclick="return confirm('Confirmar a exclusão?');"><img
							src="resources/img/excluir.png" alt="excluir" title="Excluir"
							width="20px" height="20px"></a></td>
					
				</tr>
			</c:forEach>


		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("login").value == '') {
				alert('Informe o login');
				return false;
			}

			
			return true;

		}
		
		
		
	</script>

</body>
</html>