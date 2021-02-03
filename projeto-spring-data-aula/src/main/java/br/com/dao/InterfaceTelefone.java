package br.com.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.model.Telefone;

@Repository
public interface InterfaceTelefone extends CrudRepository<Telefone, Long> {

	
	
}
