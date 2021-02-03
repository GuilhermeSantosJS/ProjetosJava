package br.com.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JPAUtil;

@Named
public class DaoGeneric<E> {

	
	@Inject
	private EntityManager entityManager;
		
	@Inject
	private JPAUtil jpaUtil;
	
	public DaoGeneric() {
		
	}
	
	
	public void salvar(E entidade) {
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.persist(entidade);
		
		entityTransaction.commit();
		entityManager.close();
		
	}
	
	
	
	public E merge(E entidade) {
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		E retorno = entityManager.merge(entidade);
		
		entityTransaction.commit();
		
		
		return retorno;
	}
	
	
	public void deletePorId(E entidade) {
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Object id = jpaUtil.getPrimaryKey(entidade);
		entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id =" + id).executeUpdate();
		//entityManager.remove(entidade);
		
		entityTransaction.commit();
		
		
	}
	
	public List<E> getListEntity(Class<E> entidade){
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		List<E> retorno = entityManager.createQuery("from " +  entidade.getName()).getResultList();
		
		
		entityTransaction.commit();
		
		
		
		return retorno;
	}
	
	
	public E consultar(Class<E> entidade, String codigo) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		E objeto = (E) entityManager.find(entidade, Long.parseLong(codigo));
		entityTransaction.commit();
		return objeto;
		
	}
	
	
	
}
