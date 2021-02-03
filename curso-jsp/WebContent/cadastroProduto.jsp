<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Produto</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
<script src="resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="resources/javascript/jquery.maskMoney.min.js" type="text/javascript"></script>
</head>
<body>
<a href="acessoliberado.jsp"><img alt="Inicio" title="Inicio" src="resources/img/inicio.png" width="40px" height="40px"></a>
<a href="index.jsp"><img alt="Sair" title="Sair" src="resources/img/fechar.png" width="40px" height="40px"></a>
	<center>
		<h1>Cadastro de Produto</h1>
	<h3 style="color: orange;">${msg}</h3>
	</center>
		<div class="form-page">
	<form action="salvarProduto" method="post" id="formProduto" id="form-login" onsubmit="return validarCampos() ? true : false">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${produto.id}" class="field-long" style="width: 50px"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${produto.nome}" maxlength="10" style="width: 200px"></td>
					</tr>

					<tr>
						<td>Quantidade:</td>
						<td><input type="text" id="quantidade" name="quantidade"
							value="${produto.quantidade}" maxlength="10"  style="width: 200px"></td>
					</tr>
					<tr>
						<td>Valor R$:</td>
						<td><input type="text" id="valor" name="valor" data-thousands="." data-decimal="," data-precision="1"
							value="${produto.valorEmTexto}" maxlength="7" style="width: 200px"></td>
							
					</tr>
					
					<tr>
					
					<td>Categoria:</td>
					<td>
					
					<select id="categorias" name="categoria_id">
					<c:forEach items="${categorias}" var="cat">
					   <option value="${cat.id}" id="${cat.id}"
					   <c:if test="${cat.id == produto.categoria_id}">
					   <c:out value="selected=selected"/>					   
					   </c:if>>
					   ${cat.nome}
					   </option>
		
					</c:forEach>
					
					
					</select>
					</td>
					
					</tr>
					
					
					
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar" style="width: 90px"> <input type="submit"  value="Cancelar" onclick="document.getElementById('formProduto').action = 'salvarProduto?acao=reset'" style="wdith: 130px"></td>
					</tr>
				</table> 

			</li>
		</ul>
	</form>
	</div>

	<div class="container">
		<table class="responsive-table">
			<caption>Lista de Produtos</caption>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Quantidade</th>
				<th>Valor R$</th>
				<th>Delete</th>
				<th>Editar</th>
			</tr>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td style="width: 150px"><c:out value="${produto.id}">
						</c:out></td>
					<td style="width: 150px"><c:out value="${produto.nome}">
						</c:out></td>
					<td><c:out value="${produto.quantidade}"></c:out></td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${produto.valor}"></fmt:formatNumber></td>
                    
					<td><a href="salvarProduto?acao=delete&produto=${produto.id}" onclick="return confirm('Confirmar a exclusão?');"><img
							src="resources/img/excluir.png" alt="excluir" title="Excluir"
							width="20px" height="20px"> </a></td>
					<td><a href="salvarProduto?acao=editar&produto=${produto.id}"><img
							alt="Editar" title="Editar" src="resources/img/editar.png"
							width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
    function validarCampos(){
    	if (document.getElementById("nome").value == '') {
			alert('Informe o Nome');
			return false;
		}

		else if (document.getElementById("quantidade").value == '') {
			alert('Informe a Quantidade');
			return false;
		}

		else if (document.getElementById("valor").value == '') {
			alert('Informe o valor');
			return false;
		}

		return true;

	}
    
   
</script>

</body>

<script type="text/javascript">

  $(function() {
    $('#valor').maskMoney();
  })
  
  $(document).ready(function(){
	  $("#quantidade").keyup(function(){
		  $("#quantidade").val(this.value.match(/[0-9]*/));
	  });
  });
  
</script>


</html>