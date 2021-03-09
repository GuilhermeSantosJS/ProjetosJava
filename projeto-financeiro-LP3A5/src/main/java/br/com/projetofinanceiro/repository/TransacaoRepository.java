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

import br.com.projetofinanceiro.model.Despesas;
import br.com.projetofinanceiro.model.Transacoes;


@Repository
@Transactional
public interface TransacaoRepository extends JpaRepository<Transacoes, Long> {

	@Query("select t from  Transacoes t where t.pessoa.id = ?1")
	public List<Transacoes> getTransacoes(Long idpessoa);
	
	@Query("select t from Transacoes t where t.nomeBeneficiario like %?1% ")
	List<Transacoes> findTransacoesByNomeBeneficiarioPage(String nomeBeneficiario);
	
	default Page<Transacoes> findTransacoesByNomeBeneficiarioPage(String nomeBeneficiario,
			Pageable pageable){
		
		Transacoes transacoes = new Transacoes();
		transacoes.setNomeBeneficiario(nomeBeneficiario);
		
		/*Estamos configurando a pesquisa para consultar por partes do nome no banco de dados, igual a like com SQL*/
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("nomeBeneficiario", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		/*une o objeto com o valor a a configuração para consultar*/
		Example<Transacoes> example =  Example.of(transacoes, exampleMatcher);
		
		Page<Transacoes> transacao = findAll(example, pageable);
		
		return transacao;
		
	}

	
	
}
