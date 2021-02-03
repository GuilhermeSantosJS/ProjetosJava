package filters;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.ConnectionDataBase;
import connection.ConnectionDataBaseBanco2;

import user.UserLogado;

@WebFilter(urlPatterns= {"/pages/*"})
public class FilterAutenticao  implements Filter{

	
	private static Connection connection;
	private static Connection connectionBanco2;
	
	//faz alguma coisa quando a aplicacao é derrubada
	@Override
	public void destroy() {
		
	}
	
	
	// intercepta todas as requisições
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String urlParaAutenticar = req.getServletPath();
		//retorna null caso não esteja logado
		UserLogado userLogado = (UserLogado) session.getAttribute("usuario");
		
		if(userLogado == null && !urlParaAutenticar.equalsIgnoreCase("/pages/ServletAutenticacao")) { // usuario nao logado
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticar.jsp?url="+urlParaAutenticar);
			dispatcher.forward(request, response);
		 return; //para o processo para redirecionar 	
		}
		
		
		chain.doFilter(request, response); // executa as acoes do request e do response
		
	
	}
	
	// dfaz alguma coisa quando a aplicacao é iniciada
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		connection = ConnectionDataBase.getConnection();
		connectionBanco2 = ConnectionDataBaseBanco2.getConnection();
		
	}

	
}
