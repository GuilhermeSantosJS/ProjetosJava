package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import dao.DaoUsuario;
import entidades.Usuario;


@WebServlet("/pages/CarregarDadosDataTable")
public class CarregarDadosDataTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	
	private DaoUsuario daoUsuario = new DaoUsuario();
	
    public CarregarDadosDataTable() {
        super();
    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
		List<Usuario> usuarios = daoUsuario.getUsuarios();
		
		  if(!usuarios.isEmpty()) {
			  int totalUsuarios = usuarios.size();
			  int index = 1;
			  
			  String data = "";
			  
			  for (Usuario usuario : usuarios) {
				  
				 data +=  "[" +
					      "\""+usuario.getId()+"\","+
					      "\""+usuario.getLogin()+"\""+
					    "]";
				  if(index < totalUsuarios) {
					  data += ",";
				  }
				  
				  index++;
				  
			}
		 
		String json = "{"+
			  "\"draw\": 1,"+
			  "\"recordsTotal\": "+ usuarios.size()+","+
			  "\"recordsFiltered\": "+usuarios.size()+","+
			  "\"data\": ["+ data + 
			   
			   
			   
			       // FIM - processa lista de dados
			    
			  "]"+ // FECHAMENTO DO DATA
			"}"; // FECHAMENTO DO JSON
		
		


		
		response.setStatus(200);
		response.getWriter().write(json);
		  }
		  
		}catch(Exception e) {
			e.printStackTrace();
			response.setStatus(500);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
