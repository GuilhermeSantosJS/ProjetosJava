package br.com.projetofinanceiro.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetofinanceiro.model.Emails;
import br.com.projetofinanceiro.model.Telefone;


@Repository
@Transactional
public interface ImplEmail extends JpaRepository<Emails, Long> {

	@Query("select e from  Emails e where e.pessoa.id = ?1")
	public List<Emails> getEmails(Long idpessoa);
	
}



