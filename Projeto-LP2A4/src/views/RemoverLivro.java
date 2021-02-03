package views;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.LivroController;

@WebServlet("/livro/deletar")
public class RemoverLivro extends HttpServlet {
private static final long serialVersionUID = 1L;
@Override
protected void doPost(HttpServletRequest req,
HttpServletResponse resp) throws ServletException, IOException {
String id = req.getParameter("id");
LivroController Controller = new LivroController();
 Controller.RemoveLivro(Integer.valueOf(id));
 
}
}
