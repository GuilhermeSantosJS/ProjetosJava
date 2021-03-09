package br.com.curso.rest.api.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.curso.rest.api.model.Usuario;
import br.com.curso.rest.api.model.UsuarioDTO;
import br.com.curso.rest.api.repository.UsuarioRepository;

@CrossOrigin
@RestController /* Arquitetura REST */
@RequestMapping(value = "/usuario")
public class IndexController {

	@Autowired /* se fosse CDI seria @inject */
	private UsuarioRepository usuarioRepository;

	/* Serviço Restful */
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<UsuarioDTO> init(@PathVariable(value = "id") Long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		/* O retorno seria um relatorio */
		return new ResponseEntity<UsuarioDTO>(new UsuarioDTO(usuario.get()), HttpStatus.OK);
	}

	
	/*Vamos supor que o carregamento do de usuário seja um processo lento e queremos controlar ele com cache para agilizar o processo*/
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Usuario>> usuario() throws InterruptedException {

		List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();

		Thread.sleep(6000);/*Segura o codigo por 6 segundos simulando um processo lento*/
		
		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}

	@PostMapping(value = "/cadastrar", produces = "application/json")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) throws Exception {

		
		
		/*Consumingo API publica externa */
		URL url = new URL("https://viacep.com.br/ws/"+ usuario.getCep()+"/json/");
		URLConnection connection = url.openConnection();
		
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		String cep = "";
		StringBuilder jsonCep = new StringBuilder();
		while((cep = br.readLine()) != null) {
			jsonCep.append(cep);
		}
		
		
		Usuario userAux = new Gson().fromJson(jsonCep.toString(), Usuario.class);
		
		usuario.setCep(userAux.getCep());
		usuario.setLogradouro(userAux.getLogradouro());
		usuario.setComplemento(userAux.getComplemento());
		usuario.setBairro(userAux.getBairro());
		usuario.setLocalidade(userAux.getLocalidade());
		usuario.setUf(userAux.getUf());
		
		/*Consumingo API publica externa */
		
		
		
		String senhacriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());	
		usuario.setSenha(senhacriptografada);
		Usuario usuarioSalvo = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);

	}

	@PostMapping(value = "/{iduser}/idvenda/{idvenda}", produces = "application/json")
	public ResponseEntity<Usuario> cadastrarvenda(@PathVariable Long iduser, @PathVariable Long idvenda) {

		/* seria o processo de venda */
		/* Usuario usuarioSalvo = usuarioRepository.save(usuario); */

		return new ResponseEntity("id user :" + iduser + "id venda:" + idvenda, HttpStatus.OK);

	}

	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {

		
		for (int pos=0; pos< usuario.getTelefones().size(); pos++) {
		    usuario.getTelefones().get(pos).setUsuario(usuario);
			}
		
		Usuario userTemporario = usuarioRepository.findUserBylogin(usuario.getLogin());
		
		
		if(!userTemporario.getSenha().equals(usuario.getSenha())) { /*senhas diferentes*/
			
			String senhacriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());	
			usuario.setSenha(senhacriptografada);
		}
		/*outras rotinas antes de atualizar*/
				
		Usuario usuarioSalvo = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);

	}
	
	@PutMapping(value = "/{iduser}/idvenda/{idvenda}", produces = "application/json")
	public ResponseEntity<Usuario> Updatevenda(@PathVariable Long iduser, @PathVariable Long idvenda) {

		/* seria o processo de venda */
		/* Usuario usuarioSalvo = usuarioRepository.save(usuario); */

		return new ResponseEntity("Venda atualizada", HttpStatus.OK);

	}
	
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String delete(@PathVariable("id") Long id) {
		
		usuarioRepository.deleteById(id);
		
		return "ok";		
	}
	
	@DeleteMapping(value = "/{id}/venda", produces = "application/text")
	public String deletevenda(@PathVariable("id") Long id) {
		
		usuarioRepository.deleteById(id);
		
		return "ok";		
	}
	
	
	

}
