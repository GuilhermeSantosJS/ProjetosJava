package br.com.projetofinanceiro.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetofinanceiro.model.Pessoa;
import br.com.projetofinanceiro.model.Telefone;
import br.com.projetofinanceiro.repository.ImplEmail;
import br.com.projetofinanceiro.repository.PessoaRepository;
import br.com.projetofinanceiro.repository.ProfissaoRepository;
import br.com.projetofinanceiro.repository.TelefoneRepository;

@Controller
public class PessoaController {
	
	Logger logger = LoggerFactory.getLogger(PessoaController.class);
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@Autowired
	private ReportUtil reportUtil;
	
	@Autowired
	private ProfissaoRepository profissaoRepository;
	
	@Autowired
	private ImplEmail implEmail;
	
    @Cacheable(cacheNames = "inicio", key = "#inicio")
	@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
	public ModelAndView inicio(HttpServletRequest request) {
    	Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		logger.info("Acessando a pagina cadastro de despesas");
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoaobj", new Pessoa());
		modelAndView.addObject("pessoas", pessoaRepository.findAll(PageRequest.of(0, 5, Sort.by("nome"))));	
		modelAndView.addObject("profissoes", profissaoRepository.findAll());
		return modelAndView;
	}
	
    @Cacheable(cacheNames = "carrega pessoas pagina", key = "#carregapag")
	@GetMapping("/pessoaspag")
	public ModelAndView carregaPessoasPorPaginacao(@PageableDefault(size = 5) Pageable pageable, ModelAndView model,
			@RequestParam("nomepesquisa") String nomepesquisa, HttpServletRequest request) {
		
    	Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Page<Pessoa> pagePessoa = pessoaRepository.findPessoaByNamePage(nomepesquisa, pageable);
		model.addObject("pessoas", pagePessoa);
		
		model.addObject("pessoaobj",  new Pessoa());
		model.addObject("nomepesquisa", nomepesquisa);
		model.setViewName("cadastro/cadastropessoa");
		
		return model;
		
	}
	
	
	@Cacheable(cacheNames = "salvar", key = "#salvar")
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarpessoa", consumes = {"multipart/form-data"})
	public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult bindingResult,
			final MultipartFile file, HttpServletRequest request) throws IOException {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		pessoa.setTelefones(telefoneRepository.getTelefones(pessoa.getId()));
		pessoa.setEmails(implEmail.getEmails(pessoa.getId()));
		Thread t = new Thread();
		
		
		if(bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
			modelAndView.addObject("pessoas", pessoaRepository.findAll(PageRequest.of(0, 5, Sort.by("nome"))));
			modelAndView.addObject("pessoaobj", pessoa);
			
			List<String> msg = new ArrayList<String>();
			
			for(ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage());
			}
			
			modelAndView.addObject("msg", msg);
			modelAndView.addObject("profissoes", profissaoRepository.findAll());
			return modelAndView;
		}
		
		if(file.getSize() > 0) { // cadastrando um curriculo
			pessoa.setCurriculo(file.getBytes());
			pessoa.setTipoFileCurriculo(file.getContentType());
			pessoa.setNomeFileCurriculo(file.getOriginalFilename());
		}else {
			if(pessoa.getId() != null && pessoa.getId() > 0) { // editando
				Pessoa curriculoTemp = pessoaRepository.findById(pessoa.getId()).get();		
				pessoa.setCurriculo(curriculoTemp.getCurriculo());
				pessoa.setTipoFileCurriculo(curriculoTemp.getTipoFileCurriculo());
				pessoa.setNomeFileCurriculo(curriculoTemp.getNomeFileCurriculo());
				
			}
		}
		
		try {
		pessoaRepository.save(pessoa);
		logger.info("Sucesso ao salvar a pessoa");
		Thread.sleep(8000);
		t.start();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao salvar a pessoa");
		}
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		andView.addObject("pessoas", pessoaRepository.findAll(PageRequest.of(0, 5, Sort.by("nome"))));
		andView.addObject("pessoaobj", new Pessoa());
		return andView;
		
		
		
	}
	
	@Cacheable(cacheNames = "lista pessoas", key = "#listar")
	@RequestMapping(method = RequestMethod.GET, value = "/listapessoas")
	public ModelAndView  pessoas(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		logger.info("listando as pessoas");
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		andView.addObject("pessoas", pessoaRepository.findAll(PageRequest.of(0, 5, Sort.by("nome"))));
		andView.addObject("pessoaobj", new Pessoa());
		return andView;
	}
	
	
	@Cacheable(cacheNames = "editar pessoas", key = "#editarpessoa")
	@GetMapping("/editarpessoa/{idpessoa}")
	public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		logger.info("Editando as pessoas");
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoaobj", pessoa.get());
		modelAndView.addObject("profissoes", pessoaRepository.findAll(PageRequest.of(0, 5, Sort.by("nome"))));
		return modelAndView;
		
	}
	
	@Cacheable(cacheNames = "excluir pessoa", key = "#deletarpessoa")
	@GetMapping("/excluirpessoa/{idpessoa}")
	public ModelAndView excluir(@PathVariable("idpessoa") Long idpessoa, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Thread t = new Thread();
		
		try {
		pessoaRepository.deleteById(idpessoa);	
		logger.info("Pessoa deletada com sucesso");
		Thread.sleep(8000);
		t.start();
		}catch (Exception e) {
		 e.printStackTrace();
		 logger.error("Erro ao deletar a pessoa");
		}finally {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoas", pessoaRepository.findAll(PageRequest.of(0, 5, Sort.by("nome"))));
		modelAndView.addObject("pessoaobj", new Pessoa());
		return modelAndView;
		}
	}
	
	
	@Cacheable(cacheNames = "baixar curriculo", key = "#baixarcurriculo")
	@GetMapping("**/baixarcurriculo/{idpessoa}")
	public void baixarcurriculo(@PathVariable("idpessoa") Long idpessoa,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		/*Consultar  o objeto pesso no banco de dados*/
		Pessoa pessoa = pessoaRepository.findById(idpessoa).get();
		if(pessoa.getCurriculo() != null) {
	     
			/*Setar tamanho da resposta */
			response.setContentLength(pessoa.getCurriculo().length);
			
			/*Tipo do arquivo para download ou pode ser generica*/
			response.setContentType(pessoa.getTipoFileCurriculo());
			
			/*Define o cabeçalho da resposta*/
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", pessoa.getNomeFileCurriculo());
			
			response.setHeader(headerKey, headerValue);
			
			/*Finaliza a resposta passando o arquivo*/
			response.getOutputStream().write(pessoa.getCurriculo());
		}
	}
	
	
	@Cacheable(cacheNames = "pesquisar pessoas", key = "#pesquisapessoa")
	@PostMapping("**/pesquisarpessoa")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa,  @RequestParam("pesqsexo") String pesqsexo,
			@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Page<Pessoa> pessoas  = null;
		
		if(pesqsexo != null && pesqsexo.isEmpty()) {
			pessoas = pessoaRepository.findPessoaBySexoPage(nomepesquisa, pesqsexo, pageable);
			
		}else {
			pessoas = pessoaRepository.findPessoaByNamePage(nomepesquisa, pageable);
		}
		
		logger.info("Pesquisando as pessoas");
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoas", pessoas);
		modelAndView.addObject("pessoaobj", new Pessoa());
		modelAndView.addObject("nomepesquisa", nomepesquisa);
		
		return  modelAndView;
		
	}
	
	
	@Cacheable(cacheNames = "telefones", key = "#telefone")
	@GetMapping("/telefones/{idpessoa}")
	public ModelAndView telefones(@PathVariable("idpessoa") Long idpessoa, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		logger.info("editando os telefones");
		ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
		modelAndView.addObject("pessoaobj", pessoa.get());
		modelAndView.addObject("telefones", telefoneRepository.getTelefones(idpessoa));
		return modelAndView;
		
	}
	
	@Cacheable(cacheNames = "salvar telefone", key = "#salvartelefone")
	@PostMapping("**/addfonePessoa/{idpessoa}")
	public ModelAndView addFonePessoa(Telefone telefone,
			@PathVariable("idpessoa") Long idpessoa, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Pessoa pessoa = pessoaRepository.findById(idpessoa).get();
		Thread t = new Thread();
		
		if(telefone != null && telefone.getNumero().isEmpty() || telefone.getTipo().isEmpty()) {
			
			
			
			ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
			modelAndView.addObject("pessoaobj", pessoa);
			modelAndView.addObject("telefones", telefoneRepository.getTelefones(idpessoa));
			
			List<String> msg = new ArrayList<String>();
			
			if(telefone.getNumero().isEmpty()) {
			msg.add("Numero deve ser informado");
			}
			
			if(telefone.getTipo().isEmpty()) {
				msg.add("Tipo deve ser informado");
			}
			
		   modelAndView.addObject("msg", msg);
			return modelAndView;
		}
		
		
		try {
		telefone.setPessoa(pessoa);
		telefoneRepository.save(telefone);
		logger.info("Telefone salvo com sucesso");
		Thread.sleep(8000);
		t.start();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao salvar o telefone");
		}finally {
		ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
		modelAndView.addObject("pessoaobj", pessoa);
		modelAndView.addObject("telefones", telefoneRepository.getTelefones(idpessoa));
		return modelAndView;
		}
		
	}
	
	@Cacheable(cacheNames = "remover telefone", key = "#deletatelefone")
	@GetMapping("/removertelefone/{idtelefone}")
	public ModelAndView removertelefone(@PathVariable("idtelefone") Long idtelefone,HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Pessoa pessoa =  telefoneRepository.findById(idtelefone).get().getPessoa();
		Thread t = new Thread();
		
		try {
		telefoneRepository.deleteById(idtelefone);
		logger.info("Telefone removido com sucesso");
		Thread.sleep(8000);
		t.start();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao remover o telefone");
		}finally {
		ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
		modelAndView.addObject("pessoaobj", pessoa);
		modelAndView.addObject("telefones", telefoneRepository.getTelefones(pessoa.getId()));
		return modelAndView;
		}
		
	}
	
	@Cacheable(cacheNames = "pesquisar pessoas", key = "#pesquisapessoa")
	@GetMapping("**/pesquisarpessoa")
	public void imprimePdf(@RequestParam("nomepesquisa") String nomepesquisa, 
			@RequestParam("pesqsexo") String pesqsexo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		if(pesqsexo != null && !pesqsexo.isEmpty()
				&& nomepesquisa != null && nomepesquisa.isEmpty()) {
			pessoas  = pessoaRepository.findPessoaByNameSexo(nomepesquisa, pesqsexo); //  busca por nome e sexo
			
			
			
		}else if(nomepesquisa != null && nomepesquisa.isEmpty()) {
			pessoas = pessoaRepository.findPessoaByName(nomepesquisa); // busca somente por nome
			
		}
		else if(pesqsexo != null && pesqsexo.isEmpty()) {
			pessoas = pessoaRepository.findPessoaBySexo(pesqsexo); // busca somente por sexo
			
		}
		else { // busca todos
			Iterable<Pessoa> iterator =  pessoaRepository.findAll();
			for (Pessoa pessoa : iterator) {
				pessoas.add(pessoa);
			}
		}
		
		/* Chama o serviço que gera o relatorio */
		byte[] pdf = reportUtil.gerarRelatorio(pessoas, "pessoa", request.getServletContext());
	
		
	
		/* Tamanho da resposta */
		
		response.setContentLength(pdf.length);
		
		/*Definir na resposta o tipo de arquivo*/
		
		response.setContentType("application/octet-stream");
		
		/*Definir cabeçalho da resposta*/
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", "relatorio.pdf");
		
		response.setHeader(headerKey, headerValue);
		
		
		/*Finaliza a resposta pro navegador*/
		
		response.getOutputStream().write(pdf);
		
	}
	
	
	
}
