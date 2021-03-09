package br.com.projetofinanceiro.repository;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projetofinanceiro.model.Despesas;
import br.com.projetofinanceiro.model.Emails;



@Repository
@Transactional
public interface DespesasRepository extends JpaRepository<Despesas, Long>  {
	
	@Query("select d from  Despesas d where d.pessoa.id = ?1")
	public List<Despesas> getDespesas(Long idpessoa);
	
	
	@Query("select d from Despesas d where d.descricaoDespesa like %?1% ")
	List<Despesas> findDespesasByDescricao(String descricaoDespesa);
	
	default Page<Despesas> findDespesasByDescricaoPage(String descricaoDespesa, Pageable pageable){
		
		Despesas despesas = new Despesas();
		despesas.setDescricaoDespesa(descricaoDespesa);
		
		/*Estamos configurando a pesquisa para consultar por partes do nome no banco de dados, igual a like com SQL*/
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("descricaoDespesa", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		/*une o objeto com o valor a a configuração para consultar*/
		Example<Despesas> example =  Example.of(despesas, exampleMatcher);
		
		Page<Despesas> despesa = findAll(example, pageable);
		
		return despesa;
		
	}

	
	
	
}
