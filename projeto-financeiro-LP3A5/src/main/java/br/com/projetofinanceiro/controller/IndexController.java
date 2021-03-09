package br.com.projetofinanceiro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Cacheable(cacheManager = "index")
	@RequestMapping("/")
	public String index() {
	logger.warn("Acesso ao homepage");	
	return "index";
	}
	
}
