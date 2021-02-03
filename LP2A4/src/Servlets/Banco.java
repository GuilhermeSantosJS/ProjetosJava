package Servlets;

import java.util.ArrayList;
import java.util.List;

public class Banco {

	private static List<Livro> listaLivro = new ArrayList<>();
	private static Integer codigo = 1;
	 public void adicionaLivro(Livro obj) {
	 listaLivro.add(obj);
	 }
	 
	 public void salvaLivro(Livro obj) {
		 if (obj.getId() > 0)
		 atualizaLivro(obj);
		 else {
		 obj.setId(getProximoCodigo());
		 listaLivro.add(obj);
		 }
		 
		 }
		 private void atualizaLivro(Livro obj) {

		 for (int i = 0; i < Banco.listaLivro.size(); i++) {
		 if (Banco.listaLivro.get(i).getId() == obj.getId()) {
		 Banco.listaLivro.set(i, obj);
		 }
		 }
		}
		 public Integer getProximoCodigo() {
			 return codigo++;
			 }

	 
	 public List<Livro> getListaLivro(){
	 return Banco.listaLivro;
	 }
	 public Integer getTamanhoListaLivro() {
	 return Banco.listaLivro.size();
	 }
	
	 public Livro getLivrobyId(Integer id) {
		 Livro localizar = new Livro();
		 for (Livro cliente : Banco.listaLivro) {
		 if (cliente.getId() == id)
		 localizar = cliente;
		 }
		 return localizar;
		 }
	 
	 
	 public void deletaCliente(Integer id) {
		 for (int i = 0; i < Banco.listaLivro.size(); i++) {
		  if (Banco.listaLivro.get(i).getId() == id) {
		  Banco.listaLivro.remove(i);
		  }
		  }
		 }

	 
		}


	
	

