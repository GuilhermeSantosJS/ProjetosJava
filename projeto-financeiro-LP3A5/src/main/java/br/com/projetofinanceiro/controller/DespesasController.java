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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetofinanceiro.model.Despesas;
import br.com.projetofinanceiro.model.Emails;
import br.com.projetofinanceiro.model.Pessoa;
import br.com.projetofinanceiro.model.Receitas;
import br.com.projetofinanceiro.repository.DespesasRepository;
import br.com.projetofinanceiro.repository.PessoaRepository;


@Controller
public class DespesasController {

	Logger logger = LoggerFactory.getLogger(DespesasController.class);
	
	@Autowired
	private DespesasRepository despesasRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	private Receitas receitas;
	
	@Cacheable(cacheNames = "inicio", key = "#root.method")
	@GetMapping("/cadastrodespesas/{idpessoa}")
	public ModelAndView inicio(@PathVariable("idpessoa") Long idpessoa, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
	   
		logger.info("Entrando na função de cadastrar despesas");
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodespesas");
		modelAndView.addObject("pessoaobj", pessoa.get());
		modelAndView.addObject("despesa", despesasRepository.getDespesas(idpessoa));
		modelAndView.addObject("despesa", despesasRepository.findAll(PageRequest.of(0, 5, Sort.by("descricaoDespesa"))));	
		return modelAndView;
	}
	
	@Cacheable(cacheNames = "listar despesa", key = "#root.method")
	@RequestMapping(value = {"/listarDespesa"}, method = RequestMethod.GET)
	public ModelAndView listaDespesa(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		logger.info("listando as depesas");
		ModelAndView model = new ModelAndView("cadastro/cadastrodespesas");
		model.addObject("despesa", despesasRepository.findAll(PageRequest.of(0, 5, Sort.by("descricaoDespesa"))));
		model.addObject("despesas", new Despesas());
		return model;
	}
	
	@Cacheable(cacheNames = "carregar as despesas por pagina", key = "#root.method")
	@GetMapping("/despesaspag")
	public ModelAndView carregaDespesasPorPaginacao(@PageableDefault(size = 5) Pageable pageable, ModelAndView model,
			@RequestParam("nomepesquisa") String nomepesquisa, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Page<Despesas> pageDespesas = despesasRepository.findDespesasByDescricaoPage(nomepesquisa, pageable);
		model.addObject("despesa", pageDespesas);
		model.addObject("despesas",  new Despesas());
		model.addObject("nomepesquisa", nomepesquisa);
		model.setViewName("/listaGastos");
		
		return model;
		
	}
	
	
	@Cacheable(cacheNames = "salvar as depesas", key = "#salvar")
	@PostMapping("**/salvarDespesa/{idpessoa}")
	public ModelAndView salvarDespesa(Despesas despesas, @PathVariable("idpessoa") Long idpessoa,
			BindingResult bindingResult, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Thread t = new Thread();
		Pessoa pessoa = pessoaRepository.findById(idpessoa).get();
		
		if(bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodespesas");
			modelAndView.addObject("despesa", despesasRepository.findAll(PageRequest.of(0, 5, Sort.by("descricaoDespesa"))));
			modelAndView.addObject("despesas", despesas);
			
			List<String> msg = new ArrayList<String>();
			
			for(ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage());
			}
			
			modelAndView.addObject("msg", msg);
			return modelAndView;
		}
		
		try {
        despesas.setPessoa(pessoa);	
        despesasRepository.save(despesas);
		logger.info("Despesas salvas com sucesso: " + despesas);
		Thread.sleep(8000);
		t.start();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao salvar despesa");
		}finally {
		ModelAndView andView = new ModelAndView("cadastro/cadastrodespesas");
		andView.addObject("pessoaobj", pessoa);
		andView.addObject("despesa", despesasRepository.getDespesas(idpessoa));
		andView.addObject("despesa", despesasRepository.findAll(PageRequest.of(0, 5, Sort.by("descricaoDespesa"))));
		return andView;
		}
	}
	
	@Cacheable(cacheNames = "editar despesas", key = "#editar")
	@GetMapping("/editarDespesa/{idDespesa}")
	public ModelAndView editarDespesas(@PathVariable("idDespesa") Long idDespesa,
			HttpServletRequest request) throws InterruptedException {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Optional<Despesas> despesas = despesasRepository.findById(idDespesa);
		Thread t = new Thread();
		Thread.sleep(8000);
		t.start();
		logger.info("Editando as despesas");
		Pessoa pessoa =  despesasRepository.findById(idDespesa).get().getPessoa();
		ModelAndView model = new ModelAndView("cadastro/cadastrodespesas");
		model.addObject("despesas", despesas.get());
	    model.addObject("pessoaobj", pessoa);
		model.addObject("despesa", despesasRepository.getDespesas(pessoa.getId()));
	    model.addObject("despesa", despesasRepository.findAll(PageRequest.of(0, 5, Sort.by("descricaoDespesa"))));
	    return model;
	}
	
	@Cacheable(cacheNames = "excluir depesas", key = "#deletar")
	@GetMapping("/excluirDespesa/{idDespesa}")
	public ModelAndView excluirDespesa(@PathVariable("idDespesa") Long idDespesa, HttpServletRequest request ) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Thread t = new Thread();
		
		Pessoa pessoa =  despesasRepository.findById(idDespesa).get().getPessoa();
		try {	
		despesasRepository.deleteById(idDespesa);
		logger.info("Despesa deletada com sucesso");
		Thread.sleep(8000);
		t.start();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao deletar despesas");
		}finally {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodespesas");
		modelAndView.addObject("pessoaobj", pessoa);
		modelAndView.addObject("despesa", despesasRepository.getDespesas(pessoa.getId()));
        modelAndView.addObject("despesa", despesasRepository.findAll(PageRequest.of(0, 5, Sort.by("descricaoDespesa"))));
		return modelAndView;
		}
	}
	
	
	@Cacheable(cacheNames = "listar gastos", key = "#gastos")
	@GetMapping("/listaGastos")
	public ModelAndView totalDespesa(HttpServletRequest request) throws InterruptedException {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Thread t = new Thread();
		Thread.sleep(8000);
		t.start();
		logger.info("Entrando na função de listar o gastos");
		ModelAndView model = new ModelAndView("/listaGastos");
		model.addObject("despesa", despesasRepository.findAll(PageRequest.of(0, 5, Sort.by("descricaoDespesa"))));
		return model;
	}
	
	
	
	
	
}
