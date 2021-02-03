package br.com.spring.data.aula;


import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.dao.InterfaceSpringDataUser;
import br.com.dao.InterfaceTelefone;
import br.com.model.Telefone;
import br.com.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class appSpringDataTest {

	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser;
	
	@Autowired
	private InterfaceTelefone interfaceTelefone;
	
	
	@Test
	public void testeInsert() {
		
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setEmail("santos@gmail.com");
		usuarioSpringData.setIdade(22);
		usuarioSpringData.setLogin("kkkkk");
		usuarioSpringData.setSenha("123789");
		usuarioSpringData.setNome("fulano");
		
		interfaceSpringDataUser.save(usuarioSpringData);
		
	}
	
	
	@Test
	public void testeConsulta() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L);
		
		System.out.println(usuarioSpringData.get().getId());
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getLogin());
		System.out.println(usuarioSpringData.get().getSenha());
		
		for(Telefone telefone : usuarioSpringData.get().getTelefones()) {
		System.out.println(telefone.getNumero());
		System.out.println(telefone.getTipo());
		System.out.println(telefone.getId());
		System.out.println(telefone.getUsuarioSpringData().getNome());
		System.out.println("------------------------------------------------------");
		}
	}
	
	@Test
	public void testeConsultaTodos() {
		
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();
		
		for (UsuarioSpringData usuarioSpringData : lista) {
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getId());
			System.out.println("---------------------------------------------------------");
			
		}
		
	}
	
	
	@Test
	public void testeUpdate() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);
		
		UsuarioSpringData data = usuarioSpringData.get();
		
		data.setNome("Gui galante");
		
		interfaceSpringDataUser.save(data);
	}
	
	
	@Test
	public void testeDelete() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);
		
		interfaceSpringDataUser.delete(usuarioSpringData.get());
		
	}
	
	@Test
	public void testeConsultaNome() {
		List<UsuarioSpringData> list = interfaceSpringDataUser.buscaPorNome("guilherme");
		
	}
	
	@Test
	public void testeConsultaNomeParam() {
		UsuarioSpringData usuarioSpringData  = interfaceSpringDataUser.buscaPorNomeParam("guilherme");
		
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getId());
			System.out.println("---------------------------------------------------------");
	}
	
	
	@Test
	public void testeDeletePorNome() {
		
		interfaceSpringDataUser.deletePorNome("guilherme");
		
	}
	
	@Test
	public void testeUpdateEmailPorNome() {
		interfaceSpringDataUser.updateEmailPorNome("santos@gmail.com", "guilherme");
	}
	
	
	
	@Test 
	public void testeInsertTelefone() {
		Optional<UsuarioSpringData> usuarioSpringData =  interfaceSpringDataUser.findById(3L);
		
		Telefone telefone = new Telefone();
		telefone.setTipo("Casa");
		telefone.setNumero("40715070");
		telefone.setUsuarioSpringData(usuarioSpringData.get());
		
		interfaceTelefone.save(telefone);
		
		
	}
	
}
