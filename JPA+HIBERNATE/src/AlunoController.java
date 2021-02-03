import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class AlunoController {
	
  EntityManagerFactory emf;
  EntityManager em;
  
public AlunoController() {
	emf = Persistence.createEntityManagerFactory("aluno");
	em = emf.createEntityManager();
}

public void salvar(Aluno _aluno) {
	em.getTransaction().begin();
	em.merge(_aluno);
	em.getTransaction().commit();
	emf.close();
}

public void remover(Aluno _aluno) {
	em.getTransaction().begin();
	Query q = em.createNativeQuery("DELETE aluno FROM aluno WHERE  matricula  =" + _aluno.getMatricula());
	q.executeUpdate();
	em.getTransaction().commit();
	emf.close();
}

public List<Aluno> listar(){
	em.getTransaction().begin();
	Query consulta = em.createQuery("SELECT aluno FROM Aluno aluno");
	List<Aluno> lista = consulta.getResultList();
	em.getTransaction().commit();
	emf.close();
	
	return lista;
	
}

	
}

