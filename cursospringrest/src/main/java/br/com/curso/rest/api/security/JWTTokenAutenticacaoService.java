package br.com.curso.rest.api.security;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.curso.rest.api.cursospringbootrest.ApplicationContextLoad;
import br.com.curso.rest.api.model.Usuario;
import br.com.curso.rest.api.repository.UsuarioRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
public class JWTTokenAutenticacaoService {

	
	/*Tempo de validade do token 2 dias*/
	private static final long EXPIRATION_TIME = 172800000;
	
	/*Uma senha unica para compor a autenticação e ajudar na segurança*/
	private static final String SECRET = "SenhaSecreta";
	
	/*Prefixo padrao de Token*/
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	/*Gerando token de autenticação e adicionando ao cabeçalho e resposta Http*/
	public void addAuthentication(HttpServletResponse response, String username)  throws IOException{ 
		/*Montagem do Token*/
		String JWT = Jwts.builder() /*Chama o gerador de token*/
				.setSubject(username) /*Adiciona o usuario */
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) /*Tempo de expiração*/
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); /*Compactação e algoritmo de geração de senha*/
		
		/*Junta o token com o prefixo*/
		String token = TOKEN_PREFIX + " " + JWT; /*Bearer exemplo - ljbfs64fsggdgd~lçgf5s4fs4f56s4 */
		
		/*Adiciona no cabeçalho http*/
		response.addHeader(HEADER_STRING, token); /*Authorization: Bearer  ljbfs64fsggdgd~lçgf5s4fs4f56s4 */
		
		/*Liberando resposta para porta diferente para o frontend*/
        response.addHeader("Access-Control-Allow-Origin", "*");
		
		ApplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class).atualizaTokenUser(JWT, username);
		   
		
		/*Liberando resposta para portas diferentes que usam a API ou caso clientes web*/
		liberacaoCors(response);
		
		/*Escreve token como resposta no corpo http*/
		response.getWriter().write("{\"Authorization\": \""+ token+"\"}");
		
	}
	
	/*Retorna o usuário validado com token ou caso não seja valido retorna null*/
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
		
		
		/*Pega o token enviado no cabeçalho http*/
       String token = request.getHeader(HEADER_STRING);	
       
       
       try {
       if(token != null) {
    	   
    	   String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();
    	   
    	   /*Faz a validação do token do usuário na requisição*/
    	   String user = Jwts.parser().setSigningKey(SECRET) /*Bearer ljbfs64fsggdgd~lçgf5s4fs4f56s4*/
    			   .parseClaimsJws(token.replace(TOKEN_PREFIX, "")) /* ljbfs64fsggdgd~lçgf5s4fs4f56s4*/ 
    			   .getBody().getSubject(); /*Exemplo - Joao silva*/
    	   if(user != null) {
    		   Usuario usuario = ApplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class).findUserBylogin(user);
    		   
    		   
    		  if(usuario != null) { 
    			  
    			  if(tokenLimpo.equalsIgnoreCase(usuario.getToken())) {
    			  
    			  return new UsernamePasswordAuthenticationToken(usuario.getLogin(), 
    					  usuario.getSenha(),
    					  usuario.getAuthorities());
    			   
    		   
    	   }
    		  }
       }
	}
       }catch (ExpiredJwtException e) {
	    try {
			response.getOutputStream().println("Seu TOKEN está expirado, faça o login ou informe um novo  TOKEN PARA AUTENTICAÇÃO");
		} catch (IOException e1) {
		}
	}
       /*Fim da condição*/
       liberacaoCors(response);
	return null; /*Não autorizado*/
	
	
	}

	private void liberacaoCors(HttpServletResponse response) {
		if(response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
			
		}
		
		if(response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
			
		}
		
		if(response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
			
		}
		
		if(response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
		
	}
	
	
}
