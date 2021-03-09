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

import br.com.projetofinanceiro.model.Pessoa;
import br.com.projetofinanceiro.model.Transacoes;
import br.com.projetofinanceiro.repository.PessoaRepository;
import br.com.projetofinanceiro.repository.TransacaoRepository;

@Controller
public class TransacaoController {

	Logger logger = LoggerFactory.getLogger(TransacaoController.class);
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Cacheable(cacheNames = "inicio", key = "#inicio")
	@GetMapping("/transacoes/{idpessoa}")
	public ModelAndView inicio(@PathVariable("idpessoa") Long idpessoa, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		logger.warn("Entrando na pagina de transações");
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		ModelAndView andView = new ModelAndView("/transacoes");
		andView.addObject("pessoaobj", pessoa.get());
		andView.addObject("transacao", transacaoRepository.getTransacoes(idpessoa));
		andView.addObject("transacao", transacaoRepository
				.findAll(PageRequest.of(0, 5, Sort.by("nomeBeneficiario"))));
		return andView;
	}
	
	@Cacheable(cacheNames = "exibir transacoes", key = "#exibetransacao")
	@RequestMapping(value = {"/exibirTransacoes"}, method = RequestMethod.GET)
	public ModelAndView ExibeTransacoes(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		ModelAndView model = new ModelAndView("/transacoes");
		model.addObject("transacao", transacaoRepository.findAll(PageRequest.of(0, 5, Sort.by("nomeBeneficiario"))));
		model.addObject("transacoes", new Transacoes());
		return model;
		
	}
	
	
	@Cacheable(cacheNames = "carrega transacao por pagina", key = "#carregapagina")
	@GetMapping("/transacaopag")
	public ModelAndView carregarTransacoesPorPaginacao(@PageableDefault(size = 5) Pageable pageable, ModelAndView model,
			@RequestParam("nomepesquisa") String nomepesquisa, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Page<Transacoes> pageTransacao = transacaoRepository.findTransacoesByNomeBeneficiarioPage(nomepesquisa, pageable);
		model.addObject("transacao", pageTransacao);
		model.addObject("transacoes",  new Transacoes());
		model.addObject("nomepesquisa", nomepesquisa);
		model.setViewName("/listaTransacoes");
		
		return model;
		
	}
	
	@Cacheable(cacheNames = "salvar transacoes", key = "#salvartransacao")
	@PostMapping("**/salvarTransacao/{idpessoa}")
	public ModelAndView salvarTransacao(Transacoes transacoes, @PathVariable("idpessoa") Long idpessoa,
			BindingResult bindingResult, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Thread t = new Thread();
		Pessoa pessoa = pessoaRepository.findById(idpessoa).get();
		
		if(bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("/transacoes");
			modelAndView.addObject("transacao", transacaoRepository
					.findAll(PageRequest.of(0, 5, Sort.by("nomeBeneficiario"))));
			modelAndView.addObject("transacoes", transacoes);
			
			List<String> msg = new ArrayList<String>();
			
			for(ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage());
			}
			
			modelAndView.addObject("msg", msg);
			return modelAndView;
		}
		try {
		transacoes.setPessoa(pessoa);	
		transacaoRepository.save(transacoes);
		logger.info("Transação salva com sucesso" + transacoes.toString());
		Thread.sleep(8000);
		t.start();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao salvar a transação");
		}finally {
		ModelAndView andView = new ModelAndView("/transacoes");
		andView.addObject("pessoaobj", pessoa);
		andView.addObject("transacao", transacaoRepository.getTransacoes(idpessoa));
		andView.addObject("transacao", transacaoRepository.findAll(PageRequest.of(0, 5, Sort.by("nomeBeneficiario"))));
		return andView;
		}
	}
	
	@Cacheable(cacheNames = "listar transacoes", key = "#listatransacao")
	@GetMapping("/listaTransacoes")
	public ModelAndView transacoes(HttpServletRequest request) throws InterruptedException {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Thread t = new Thread();
		Thread.sleep(8000);
		t.start();
		ModelAndView model = new ModelAndView("/listaTransacoes");
		model.addObject("transacao", transacaoRepository.findAll(PageRequest.of(0, 5, Sort.by("nomeBeneficiario"))));
		return model;
	}
	
	
	
	
	
}
