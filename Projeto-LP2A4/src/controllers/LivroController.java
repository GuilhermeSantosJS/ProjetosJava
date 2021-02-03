package controllers;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import models.Livro;

public class LivroController{

	public void SalvaLivro(Livro livro) {
	     EntityManagerFactory emf = Persistence.createEntityManagerFactory("livro");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			if(livro.getId() == null) {
				em.persist(livro);
			}
			else {
				em.merge(livro);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e);
			em.getTransaction().rollback();
		}finally {
			em.close();
			emf.close();
		}
	}


	public List<Livro> getListaLivro(){
		EntityManagerFactory emf =
	    Persistence.createEntityManagerFactory("livro");
		EntityManager em = emf.createEntityManager();
		List<Livro> listaLivro = null;
		try {
			listaLivro = em.createQuery("SELECT livro FROM Livro livro").getResultList();
			return listaLivro;
		} catch (Exception e) {
			System.err.println(e);
		}finally {
			em.close();
			emf.close();
		}
		return null;
	}
	public Livro getLivrobyId(Integer id) {
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("livro");
		EntityManager em = emf.createEntityManager();
		Livro localiza = null;
		try {
			localiza = em.find(Livro.class, id);
		}catch (Exception e) {
			System.err.println(e);
		}finally {
			em.close();
			emf.close();
		}
		return localiza;
	}
	public void RemoveLivro(Integer id) {
		EntityManagerFactory emf =
		Persistence.createEntityManagerFactory("livro");
		EntityManager em = emf.createEntityManager();
		Livro livro = null;
            try {
			livro = em.find(Livro.class, id);
			em.getTransaction().begin();
			em.remove(livro);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e);
			em.getTransaction().rollback();
		}finally {
			em.close();
			emf.close();
		}
	}
}


	

	


