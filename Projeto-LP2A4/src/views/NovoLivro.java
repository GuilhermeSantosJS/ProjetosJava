package views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.LivroController;
import models.Livro;


@WebServlet("/livro/novo")
public class NovoLivro extends HttpServlet{
private static final long serialVersionUID = 1L;


@Override
protected void doGet(HttpServletRequest req,
HttpServletResponse resp) throws ServletException, IOException {
RequestDispatcher rd =
req.getRequestDispatcher("/livro-novo.jsp");
rd.forward(req, resp);
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse
resp) throws ServletException, IOException {


String id = req.getParameter("id");
if (id.isEmpty()) { 
id ="0";
}
  String titulo = req.getParameter("titulo");
 String autor = req.getParameter("autor");
 String isbn = req.getParameter("isbn");
 String preco = req.getParameter("preco");

 
 List<String> msg = new ArrayList<String>();


 if(titulo.isEmpty()) {
 msg.add("Campo titulo é obrigatório");
 }
 if(autor==null || autor.isEmpty()) {
 msg.add("Campo autor é obrigatório");
 }
 if(isbn.isEmpty()) {
 msg.add("Campo isbn é obrigatório");
 }
 if(preco.isEmpty()) {
 msg.add("Campos preco é obrigatório");
 }
 
 if (msg.size() > 0) {
	 req.setAttribute("msg", msg);
	 req.setAttribute("id", id);
	 req.setAttribute("titulo", titulo);
	 req.setAttribute("autor", autor);
	 req.setAttribute("isbn", isbn);
	 req.setAttribute("preco", preco);
	 }
	 else {
	 Livro livro = new Livro();
	 livro.setId(Integer.valueOf(id));
	 livro.setTitulo(titulo);
	 livro.setAutor(autor);
	 livro.setIsbn(isbn);
	 livro.setPreco(preco);
	 
	 LivroController Controller = new LivroController();

	 Controller.SalvaLivro(livro);
	 System.out.println("Livro Adicionado com sucesso!");


	 req.setAttribute("sucesso", "Livro Adicionado com sucesso");
	 }

	 doGet(req, resp);
	}

}






