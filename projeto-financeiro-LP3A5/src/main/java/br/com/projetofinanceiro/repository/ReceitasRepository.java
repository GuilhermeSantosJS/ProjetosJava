package br.com.projetofinanceiro.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetofinanceiro.model.Emails;
import br.com.projetofinanceiro.model.Receitas;


@Repository
@Transactional
public interface ReceitasRepository extends JpaRepository<Receitas, Long> {
	
	
	@Query("select r from  Receitas r where r.pessoa.id = ?1")
	public List<Receitas> getReceitas(Long idpessoa);
	
	@Query("select r from Receitas r where r.salarioMensal like %?1% ")
	List<Receitas> findReceitasByDescricaoReceita(String descricaoReceita);
	
	default Page<Receitas> findReceitasByDescricaoReceita(String descricaoReceita, Pageable pageable){
		
		Receitas receitas = new Receitas();
		receitas.setDescricaoReceita(descricaoReceita);
		
		/*Estamos configurando a pesquisa para consultar por partes do nome no banco de dados, igual a like com SQL*/
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("descricaoReceita", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		/*une o objeto com o valor a a configuração para consultar*/
		Example<Receitas> example =  Example.of(receitas, exampleMatcher);
		
		Page<Receitas> receita = findAll(example, pageable);
		
		return receita;
		
	}
	
	
}
