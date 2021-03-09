package br.com.projetofinanceiro.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;




import br.com.projetofinanceiro.model.Pessoa;
import br.com.projetofinanceiro.model.Receitas;
import br.com.projetofinanceiro.repository.PessoaRepository;
import br.com.projetofinanceiro.repository.ReceitasRepository;


@Controller
public class ReceitasController {

	
	Logger logger = LoggerFactory.getLogger(ReceitasController.class);
	
	@Autowired
	private ReceitasRepository receitasRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Cacheable(cacheNames = "inicio", key = "#inicio")
	@GetMapping("/cadastroreceitas/{idpessoa}")
	public ModelAndView inicio(@PathVariable("idpessoa") Long idpessoa, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		logger.info("Entrando na função de cadastrar receitas");
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroreceitas");
		modelAndView.addObject("pessoaobj", pessoa.get());
		modelAndView.addObject("receita", receitasRepository.getReceitas(idpessoa));
		modelAndView.addObject("receita", receitasRepository.findAll(PageRequest.of(0, 5, Sort.by("salarioMensal"))));	
		return modelAndView;
	}
	
	@Cacheable(cacheNames = "receitas por pagina", key = "#receitapag")
	@GetMapping("/receitaspag")
	public ModelAndView carregarReceitasPorPaginacao(@PageableDefault(size = 5) Pageable pageable, ModelAndView model,
			@RequestParam("nomepesquisa") String nomepesquisa, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Page<Receitas> pageReceitas = receitasRepository.findReceitasByDescricaoReceita(nomepesquisa, pageable);
		model.addObject("receita", pageReceitas);	
		model.addObject("receitas",  new Receitas());
		model.addObject("nomepesquisa", nomepesquisa);
		model.setViewName("cadastro/cadastroreceitas");
		
		return model;
		
	}
	
	@Cacheable(cacheNames = "listar receitas", key = "#listareceita")
	@RequestMapping(value = {"/listarReceitas"}, method = RequestMethod.GET)
	public ModelAndView listaReceitas(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		ModelAndView model = new ModelAndView("cadastro/cadastroreceitas");
		model.addObject("receita", receitasRepository.findAll(PageRequest.of(0, 5, Sort.by("salarioMensal"))));
		model.addObject("receitas", new Receitas());
		return model;
		
	}
	

	@Cacheable(cacheNames = "salvar receita", key = "#salvarreceita")
	@PostMapping("**/salvarReceita/{idpessoa}")
	public ModelAndView salvarReceita(Receitas receitas, @PathVariable("idpessoa") Long idpessoa,
			BindingResult bindingResult, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Thread t = new Thread();
		Pessoa pessoa = pessoaRepository.findById(idpessoa).get();
		if(bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastroreceitas");
			modelAndView.addObject("receita", receitasRepository.findAll(PageRequest.of(0, 5, Sort.by("salarioMensal"))));
			modelAndView.addObject("receitas", receitas);
			
			List<String> msg = new ArrayList<String>();
			
			for(ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage());
			}
			
			modelAndView.addObject("msg", msg);
			return modelAndView;
		}
		
		try {
		receitas.setPessoa(pessoa);	
		receitasRepository.save(receitas);
		logger.info("Receita salva com sucesso");
		Thread.sleep(8000);
		t.start();
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro a salvar a receita");
		}finally {
		ModelAndView andView = new ModelAndView("cadastro/cadastroreceitas");
		andView.addObject("pessoaobj", pessoa);
		andView.addObject("receita", receitasRepository.getReceitas(idpessoa));
		andView.addObject("receita", receitasRepository.findAll(PageRequest.of(0, 5, Sort.by("salarioMensal"))));
		return andView;
		}
		
	}
	
	
	@Cacheable(cacheNames = "editar receitas", key = "#editarreceita")
	@GetMapping("/editarReceitas/{idReceitas}")
	public ModelAndView editarReceitas(@PathVariable("idReceitas") Long idReceitas,
			HttpServletRequest request) throws InterruptedException {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Optional<Receitas> receitas = receitasRepository.findById(idReceitas);
		Thread t = new Thread();
		Thread.sleep(5000);
		t.start();
		logger.info("editando a receita");
		Pessoa pessoa =  receitasRepository.findById(idReceitas).get().getPessoa();
		ModelAndView model = new ModelAndView("cadastro/cadastroreceitas");
	    model.addObject("receitas", receitas.get());
	    model.addObject("pessoaobj", pessoa);
		model.addObject("receita", receitasRepository.getReceitas(pessoa.getId()));
		model.addObject("receita", receitasRepository.findAll(PageRequest.of(0, 5, Sort.by("salarioMensal"))));
	    return model;		
	}
	
	
	@Cacheable(cacheNames = "excluir receitas", key = "#deletareceita")
	@GetMapping("/excluirReceitas/{idReceitas}")
	public ModelAndView excluirReceitas(@PathVariable("idReceitas") Long idReceitas,
			HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Thread t = new Thread();
		Pessoa pessoa =  receitasRepository.findById(idReceitas).get().getPessoa();
		try {
		receitasRepository.deleteById(idReceitas);
		logger.info("receita deletada com sucesso");
		Thread.sleep(5000);
		t.start();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("erro ao deletar a receita");
		}finally {
		ModelAndView model = new ModelAndView("cadastro/cadastroreceitas");
		model.addObject("pessoaobj", pessoa);
		model.addObject("receita", receitasRepository.getReceitas(pessoa.getId()));
		model.addObject("receita", receitasRepository.findAll(PageRequest.of(0, 5, Sort.by("salarioMensal"))));
		return model;
		}
	}
	
	@Cacheable( cacheNames = "listar ganhos", key = "#ganhos" )
	@GetMapping("/listaGanhos")
	public ModelAndView totalReceita(Receitas receitas, HttpServletRequest request) throws InterruptedException {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Thread t = new Thread();
		Thread.sleep(5000);
		t.start();
		ModelAndView model = new ModelAndView("/listaGanhos");
		model.addObject("receita", receitasRepository.findAll(PageRequest.of(0, 5, Sort.by("descricaoReceita"))));
		return model;
	}
	
	
	
	
	
}
