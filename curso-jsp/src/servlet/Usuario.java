
package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.tomcat.util.codec.binary.Base64;
import org.omg.CORBA_2_3.portable.InputStream;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario")
@MultipartConfig
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao != null && acao.equalsIgnoreCase("delete") && user != null) {
				daoUsuario.delete(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("editar")) {
				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", beanCursoJsp);
				view.forward(request, response);
			} else if ( acao != null && acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} else if ( acao != null && acao.equalsIgnoreCase("download")) {
				BeanCursoJsp usuario = daoUsuario.consultar(user);
				if(usuario != null) {
					String contentType = "";
					byte[] fileBytes = null;
					
					String tipo = request.getParameter("tipo");
					
					if(tipo.equalsIgnoreCase("imagem")) {
						contentType = usuario.getContentType();
						fileBytes = new Base64().decodeBase64(usuario.getFotoBase64());
					}else if (tipo.equalsIgnoreCase("curriculo")) {
						contentType = usuario.getContentTypeCurriculo();
						fileBytes = new Base64().decodeBase64(usuario.getCurriculoBase64());
					}
					
					
					
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + usuario.getContentType().split("\\/")[1]);
					
					/* Coloca os bytes em um objeto de entrada para processar*/
					ByteArrayInputStream is = new ByteArrayInputStream(fileBytes);
					
					/*Inicio da resposta para o navegador*/
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
					
					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
					
					os.flush();
					os.close();
					
				}
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String fone = request.getParameter("fone");
			
			String cep = request.getParameter("fone");
			String rua  = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			String ibge = request.getParameter("ibge");
			String sexo = request.getParameter("sexo");
			String perfil = request.getParameter("perfil");
			
			
			
			
			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setFone(fone);
			
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setEstado(estado);
			usuario.setIbge(ibge);
			usuario.setSexo(sexo);
			usuario.setPerfil(perfil);
			
			if(request.getParameter("ativo") != null && request.getParameter("ativo").equalsIgnoreCase("on")) {
				
				usuario.setAtivo(true);
			}else {
				usuario.setAtivo(false);
			}
			

			try {
		
			        /*inicio File upload de imagens e pdf */
				    if(ServletFileUpload.isMultipartContent(request)) {
				    	
				       Part imagemFoto = request.getPart("foto");
				      	
				    	   if(imagemFoto != null && imagemFoto.getInputStream().available() > 0) {
				    		   
				    		   byte[] bytesImagem = converteStremParaByte(imagemFoto.getInputStream());
				    		   
				       String fotoBase64 = new Base64().encodeBase64String(bytesImagem);
				       
				       usuario.setFotoBase64(fotoBase64);
				       usuario.setContentType(imagemFoto.getContentType());
				       
				       /* Inicio miniatura imagem */
				       
				       
				       /* Transforma em um bufferedImage*/
				       byte[] imageByteDecode = new Base64().decodeBase64(fotoBase64);
				       BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));
				       
				       /* Tipo da imagem */
				       int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB: bufferedImage.getType();	
				       
				       /*Cria imagem em miniatura*/
				       BufferedImage resizedImage = new BufferedImage(100, 100, type);
				       Graphics2D g = resizedImage.createGraphics();
				       g.drawImage(bufferedImage, 0, 0, 100, 100, null);
				       g.dispose();
				       
				       /*Escrever imagem novamente*/
				       ByteArrayOutputStream baos = new ByteArrayOutputStream();
				       ImageIO.write(resizedImage, "png", baos);
				      
				       
				       String miniaturaBase64 = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
				       
				       usuario.setFotoBase64Miniatura(miniaturaBase64);
				       /* Fim miniatura imagem */
				       
				       
				       }else {
				    	   usuario.setAtualizarImage(false);
				    	   
				    	   
				       }
				       
				       /* Processa pdf*/
				       
				       Part curriculoPdf = request.getPart("curriculo");
				    	   if(curriculoPdf != null && curriculoPdf.getInputStream().available() > 0) {
				         String curriculoBase64 = 
						 new Base64().encodeBase64String(converteStremParaByte(imagemFoto.getInputStream()));
				         
				         usuario.setCurriculoBase64(curriculoBase64);
				         usuario.setContentTypeCurriculo(curriculoPdf.getContentType());
				         
				    	   }else {
				    		   usuario.setAtualizarPdf(false);
					    	 
				    	   }
				       }
				     
				    	
				    	
				    
				
				
				 /*FIM File upload de imagens e pdf */
				String msg = null;
				boolean podeInserir = true;

				if (login == null || login.isEmpty()) {
					msg = "Login deve ser informado";
					podeInserir = false;

				} else if (senha == null || senha.isEmpty()) {
					msg = "Senha deve ser informada";
					podeInserir = false;

				} else if (nome == null || nome.isEmpty()) {
					msg = "Nome deve ser informado";
					podeInserir = false;

				}

				

				else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {// QUANDO DOR USUÁRIO NOVO
					msg = "Usuário já existe com o mesmo login!";
					podeInserir = false;

				} else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {// QUANDO FOR USUÁRIO NOVO
					msg = "\n A senha já existe para outro usuário!";
					podeInserir = false;
				}

				if (msg != null) {
					request.setAttribute("msg", msg);
				} else if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && podeInserir) {

					daoUsuario.salvar(usuario);

				} else if (id != null && !id.isEmpty() && podeInserir) {
					daoUsuario.atualizar(usuario);
				}

				if (!podeInserir) {
					request.setAttribute("user", usuario);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				request.setAttribute("msg", "Salvo com sucesso!");
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*Converte a entrada de fluxo de dados para array de bytes*/
	private  byte[] converteStremParaByte(java.io.InputStream inputStream) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = inputStream.read();
		while(reads != -1) {
			baos.write(reads);
			reads = inputStream.read();
		}
		
		return baos.toByteArray();			
	}

}