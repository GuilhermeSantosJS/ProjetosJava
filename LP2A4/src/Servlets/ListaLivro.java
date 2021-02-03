package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/livro/lista")
public class ListaLivro extends HttpServlet{
private static final long serialVersionUID = 1L;

@Override
protected void doGet(HttpServletRequest req,
HttpServletResponse resp) throws ServletException, IOException {

	Banco banco = new Banco();
	 List<Livro> lista = banco.getListaLivro();
	 req.setAttribute("livros", lista);
	RequestDispatcher rd =
	req.getRequestDispatcher("/livro-lista.jsp");
	rd.forward(req, resp);
}

}

