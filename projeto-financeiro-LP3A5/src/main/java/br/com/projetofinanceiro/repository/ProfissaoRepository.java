package br.com.projetofinanceiro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetofinanceiro.model.Profissao;


@Repository
@Transactional
public interface ProfissaoRepository  extends CrudRepository<Profissao, Long>{


}
