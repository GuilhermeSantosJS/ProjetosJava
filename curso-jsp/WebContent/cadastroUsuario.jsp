<%@page import="beans.BeanCursoJsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">


<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>


</head>
<body>
	<a href="acessoliberado.jsp"><img alt="Inicio" title="Inicio" src="resources/img/inicio.png" width="40px" height="40px"></a>
	<a href="index.jsp"><img alt="Sair" title="Sair" src="resources/img/fechar.png" width="40px" height="40px"></a>
	<center>
		<h1>Cadastro de usuário</h1>
		<h3 style="color: orange">${msg}</h3>
		<h3 style="color: orange">${msg_senha}</h3>
	</center>
	<div class="form-page">
		<form action="salvarUsuario" method="post" id="formUser"
			class="form-login" onsubmit=" return validarCampos()? true : false"
			enctype="multipart/form-data">
			<ul class="form-style-1">
				<li>

					<table>
						<tr>
							<td>Código:</td>
							<td><input type="text" readonly="readonly" id="id" name="id"
								value="${user.id}" class="field-long"></td>


							<td>Cep:</td>
							<td><input type="text" id="cep" name="cep"
								onblur="consultaCep();" value="${user.cep}"
								placeholder="Informe um CEP valido " maxlength="9"></td>

						</tr>

						<tr>
							<td>Login:</td>
							<td><input type="text" id="login" name="login"
								value="${user.login}" placeholder="Informe o seu login" maxlength="10"></td>

							<td>Rua:</td>
							<td><input type="text" id="rua" name="rua"
								value="${user.rua}" placeholder="Informe o nome da sua Rua" maxlength="50"></td>

						</tr>

						<tr>
							<td>Senha:</td>
							<td><input type="password" id="senha" name="senha"
								value="${user.senha}" placeholder="Informe sua senha" maxlength="10"></td>

							<td>Bairro:</td>
							<td><input type="text" id="bairro" name="bairro"
								value="${user.bairro}"
								placeholder="Informe o nome do seu bairro" maxlength="50"></td>

						</tr>

						<tr>
							<td>Nome:</td>
							<td><input type="text" id="nome" name="nome"
								value="${user.nome}" placeholder="Informe o nome do usuário" maxlength="50"></td>

							<td>Cidade:</td>
							<td><input type="text" id="cidade" name="cidade"
								value="${user.cidade}"
								placeholder="Informe o nome da sua cidade" maxlength="50"></td>

						</tr>
						
						

						<tr>

							<td>Estado:</td>
							<td><input type="text" id="estado" name="estado"
								value="${user.estado}" placeholder="Informe o seu Estado" maxlength="20"></td>

						</tr>

						<tr>
							<td>IBGE:</td>
							<td><input type="text" id="ibge" name="ibge" value="${user.ibge}" placeholder="Informe o numero do IBGE" maxlength="20"></td>
						    <td>Ativo:</td>
						    <td><input type="checkbox" id="ativo" name="ativo" 
						    <%
						    if (request.getAttribute("user") != null) {
						    	
						        BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
						        if(user.isAtivo()){
						        	out.print(" ");
						        	out.print("checked=\"checked\"");
						        	out.print(" ");
						        }
						    }
						   
						    %>
						    
						    
						    ></td>
						</tr>

						<tr>
						       <td>Perfil:</td>
							<td>
							<select id="perfil" name="perfil" style="width: 185px">
							<option value="nao_informado">[--SELECIONE--]</option>
							<option value="administrador" selected="selected"
							<%
						    if (request.getAttribute("user") != null) {
						    	
						        BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
						        if(user.getPerfil().equalsIgnoreCase("administrador")){
						        	out.print(" ");
						        	out.print("selected=\"selected\"");
						        	out.print(" ");
						        }
						    }
						   
						    %>
							
							
							
							>Administrador</option>
							
							<option value="secretario" selected="selected">
							
							<%
						    if (request.getAttribute("user") != null) {
						    	
						        BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
						        if(user.getPerfil().equalsIgnoreCase("secretario")){
						        	out.print(" ");
						        	out.print("selected=\"selected\"");
						        	out.print(" ");
						        }
						    }
						   
						    %>
							
							
							
							Secretário(a)</option>
							
							
							<option value="gerente"  selected="selected">
							
							<%
						    if (request.getAttribute("user") != null) {
						    	
						        BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
						        if(user.getPerfil().equalsIgnoreCase("gerente")){
						        	out.print(" ");
						        	out.print("selected=\"selected\"");
						        	out.print(" ");
						        }
						    }
						   
						    %>
							
							
							Gerente</option>
							
							
							
							<option value="funcionario" selected="selected"
							
							<%
						    if (request.getAttribute("user") != null) {
						    	
						        BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
						        if(user.getPerfil().equalsIgnoreCase("funcionario")){
						        	out.print(" ");
						        	out.print("selected=\"selected\"");
						        	out.print(" ");
						        }
						    }
						   
						    %>
							
							
					
							>Funcionário</option>
							
							
							</select>
							</td>	
						
						
							
							<td>Curriculo:</td>
							<td><input type="file" name="curriculo" value="curriculo">
								</td>
							
                         </tr>
                         
                         
                         
						<tr>
								<td>Sexo:</td>
							<td>
							<input type="radio" name="sexo"
							
							<%
							if(request.getAttribute("user") != null){
								BeanCursoJsp user =  (BeanCursoJsp) request.getAttribute("user");
								
								if(user.getSexo().equalsIgnoreCase("masculino")){
									out.print("");
									out.print("checked=\"checked\"");
									out.print("");
								}
							}
							%>
							
							 value="masculino">Masculino</input>
							 
							 
							<input type="radio" name="sexo" 
							<%
							if(request.getAttribute("user") != null){
								BeanCursoJsp user =  (BeanCursoJsp) request.getAttribute("user");
								
								if(user.getSexo().equalsIgnoreCase("feminino")){
									out.print("");
									out.print("checked=\"checked\"");
									out.print("");
								}
							}
							%>
							
							
							value="feminino">Feminino</input>
							
							
							</td>
							
							<td>Foto:</td>
							<td><input type="file" name="foto"></td>
						
							
						</tr>
							
						<tr>
							<td></td>
							<td><input type="submit" value="Salvar"
								style="width: 173px;"></td>
							<td></td>
							<td><input type="submit" value="Cancelar"
								onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"
								style="width: 173px;"></td>
						</tr>

					</table>
				</li>
			</ul>

		</form>	
	</div>
      
      <form  method="post" action="servletPesquisa">
      <ul class="form-style-1">
      <li>
         <table>
         <tr>
         <td>Descrição</td>
         <td><input type="text" id="descricaoconsulta"></td>
         <td><input type="submit" value="Pesquisar"></td>
         </tr>
         </table>
         
         </li>
      
      
      </ul>
      </form>
      
      
	<div class="container">
		<table class="responsive-table">

			<caption>Lista de Usuarios</caption>
			<tr>
				<th>Id</th>
				<th>Foto</th>
				<th>Curriculo</th>
				<th>Nome</th>
				<th>Delete</th>
				<th>Update</th>
				<th>Fones</th>
			</tr>

			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td><c:out value="${user.id}"></c:out></td>

					<c:if test="${user.fotoBase64Miniatura.isEmpty() == false}">
						<td><a
							href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}"><img
								src='<c:out value="${user.fotoBase64Miniatura}"/>' alt="Imagem User"
								title="Imagem User" width="32px" height="32px"></a></td>
					</c:if>
					<c:if test="${user.fotoBase64Miniatura == null}">
						<td><img alt="Imagem User" src="resources/img/user.png"
							width="32px" height="32px" onclick="alert('Não possui Imagem')"></td>
					</c:if>

					<c:if test="${user.curriculoBase64.isEmpty() == false}">
						<td><a
							href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}"><img
								alt="Curriculo" src="resources/img/pdf_baixar.png" width="32px"
								height="32px"></a></td>
					</c:if>
					<c:if test="${user.curriculoBase64.isEmpty() == true}">
						<td><img alt="Curriculo" src="resources/img/pdf.png"
							width="32px" height="32px"
							onclick="alert('Não possui curriculo')"></td>
					</c:if>

					<td><c:out value="${user.nome}"></c:out></td>
<td><a href="salvarUsuario?acao=delete&user=${user.id}" onclick="return confirm('Confirmar a exclusão?');"><img src="resources/img/excluir.png" alt="excluir" title="Excluir" width="20px" height="20px"></a></td>
<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img src="resources/img/editar.png" alt="editar" title="Editar" width="20px" height="20px"></a></td>
<td><a href="salvarTelefones?acao=addFone&user=${user.id}"><img src="resources/img/telefone.png" alt="fone" title="fone" width="20px" height="20px"></a></td>

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

			else if (document.getElementById("senha").value == '') {
				alert('Informe a Senha');
				return false;
			}

			else if (document.getElementById("nome").value == '') {
				alert('Informe o Nome');
				return false;
			}

			else if (document.getElementById("Telefone").value == '') {
				alert('Informe o Telefone');
				return false;
			}

			return true;

		}

		function consultaCep() {
			var cep = $("#cep").val();

			//Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {

							$("#rua").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#cidade").val(dados.localidade);
							$("#estado").val(dados.uf);
							$("#ibge").val(dados.ibge);
						} else {

							$("#cep").val('');
							$("#rua").val('');
							$("#bairro").val('');
							$("#cidade").val('');
							$("#estado").val('');
							$("#ibge").val('');
							//CEP pesquisado não foi encontrado.
							alert("CEP não encontrado.");
						}

					});

		}
	</script>

</body>
</html>