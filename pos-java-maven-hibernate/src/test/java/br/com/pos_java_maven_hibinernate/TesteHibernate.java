package br.com.pos_java_maven_hibinernate;

import java.util.List;

import org.junit.Test;

import br.com.dao.DaoGeneric;
import br.com.model.TelefoneUser;
import br.com.model.UsuarioPessoa;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() {
		
	DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();     
	
	UsuarioPessoa pessoa = new  UsuarioPessoa();
	
	pessoa.setIdade(18);
	pessoa.setLogin("testando");
	pessoa.setNome("Guilherme");
	pessoa.setSenha("123789");
	pessoa.setSobrenome("dos santos");
	
	
	daoGeneric.salvar(pessoa);
	
	
	}
	
	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();    
		UsuarioPessoa pessoa = new  UsuarioPessoa();
		
		pessoa.setId(1L);
	pessoa = daoGeneric.pesquisar(pessoa);
		
	System.out.println(pessoa);
	}
	
	
	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();    
		UsuarioPessoa pessoa = new  UsuarioPessoa();
		
		pessoa.setIdade(22);
		
		
		pessoa = daoGeneric.updateMerge(pessoa);
		
	System.out.println(pessoa);
	}
	
	

	@Test
	public void testeDelete() throws Exception {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();    
		UsuarioPessoa pessoa  = daoGeneric.pesquisar(3L, UsuarioPessoa.class);
		
		daoGeneric.deletarPorId(pessoa);
		
		System.out.println(pessoa);
		
	}
	
	
	
	@Test
	public void testeConsultar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();    
		
		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);
		
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-------------------------------------------------------------");
		}
		
		
	}
	
	@Test
	public void testeQueryList() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();    
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa where nome = 'Guilherme'").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			
		}
		
	}
	
	

	@Test
	public void testeQueryListMaxResult() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();    
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa order by id")
				.setMaxResults(2)
	            .getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			
		}
		
	}
	
	@Test
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();   
		
		List<UsuarioPessoa> list =  daoGeneric
				.getEntityManager()
				.createQuery("from UsuarioPessoa where nome = :nome")
				.setParameter("nome", "Guilherme")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
		
		
	}
	
	@Test
	public void testeQuerySomaIdade() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();   
		
		Long somaIdade = (Long) daoGeneric.getEntityManager()
				.createQuery("select sum(u.idade) from UsuarioPessoa u").getSingleResult();
		
		System.out.println("Soma de todas as idades é --> " + somaIdade);
	}
	
	@Test
	public void testeNamedQuery1() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();   
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.todos").getResultList();
		
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
		
	}
	
	@Test
	public void testeNamedQuery2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();   
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.buscaPorNome")
				.setParameter("nome", "Guilherme")
				.getResultList();
		
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
		
	}
	
	@Test
	public void testeGravaTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();  
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(5L,  UsuarioPessoa.class);
		
		TelefoneUser telefoneUser = new TelefoneUser();
		
		telefoneUser.setTipo("Celular");
		telefoneUser.setNumero("954296756");
		telefoneUser.setUsuarioPessoa(pessoa);
		
		daoGeneric.salvar(telefoneUser);
		
	}
	
	
	@Test
	public void testeConsultaTelefones() {
		DaoGeneric daoGeneric = new DaoGeneric();  
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(5L,  UsuarioPessoa.class);
		
		
		for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getUsuarioPessoa().getNome());
			System.out.println("-------------------------------------------");
			
		}
		
		
	}
	
	
	
	
	
	
}
