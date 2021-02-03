package views;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.LivroController;
import models.Livro;


@WebServlet("/livro/editar")
public class EditaLivro extends HttpServlet {
private static final long serialVersionUID = 1L;
protected void doGet(HttpServletRequest request,
HttpServletResponse response) throws ServletException, IOException {
String id = request.getParameter("id");
LivroController Controller = new LivroController();
 Livro livro =  Controller.getLivrobyId(Integer.valueOf(id));
request.setAttribute("id", livro.getId());
request.setAttribute("titulo", livro.getTitulo());
request.setAttribute("autor", livro.getAutor());
request.setAttribute("isbn", livro.getIsbn());
request.setAttribute("preco", livro.getPreco());
RequestDispatcher rd =
request.getRequestDispatcher("/livro-novo.jsp");
rd.forward(request, response);
}
}