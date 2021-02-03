package views;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.LivroController;
import models.Livro;


@WebServlet("/livro/lista")
public class ListaLivro extends HttpServlet{
private static final long serialVersionUID = 1L;

@Override
protected void doGet(HttpServletRequest req,
HttpServletResponse resp) throws ServletException, IOException {

	LivroController Controller = new LivroController();
	 List<Livro> lista = Controller.getListaLivro();
	 req.setAttribute("livros", lista);
	RequestDispatcher rd =
	req.getRequestDispatcher("/livro-lista.jsp");
	rd.forward(req, resp);
}

}

