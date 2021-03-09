package br.com.projetofinanceiro.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.projetofinanceiro.model.Emails;
import br.com.projetofinanceiro.model.Pessoa;

import br.com.projetofinanceiro.repository.ImplEmail;
import br.com.projetofinanceiro.repository.PessoaRepository;


@Controller
public class EmailController {

	Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
	private ImplEmail implEmail;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	@Cacheable(cacheNames = "emails", key = "#email")
	@GetMapping("/emails/{idpessoa}")
	public ModelAndView emails(@PathVariable("idpessoa") Long idpessoa, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		logger.info("editando os emails");
		ModelAndView modelAndView = new ModelAndView("/emails");
		modelAndView.addObject("pessoaobj", pessoa.get());
		modelAndView.addObject("emails", implEmail.getEmails(idpessoa));
		return modelAndView;
		
	}
	
    @Cacheable(cacheNames = "salvar email", key = "#salvaremail")
	@PostMapping("**/salvarEmail/{idpessoa}")
	public ModelAndView enviaEmail(Emails email, @PathVariable("idpessoa") Long idpessoa,
			BindingResult bindingResult, 
			RedirectAttributes redirectAttributes, HttpServletRequest request)  {
    	Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Pessoa pessoa = pessoaRepository.findById(idpessoa).get();
		
		Thread t = new Thread();
		
           if(email != null && email.getEmail().isEmpty() || email.getNome().isEmpty() 
        		   || email.getFeedback().isEmpty()) {
	
			ModelAndView modelAndView = new ModelAndView("/emails");
			modelAndView.addObject("pessoaobj", pessoa);
			modelAndView.addObject("emails", implEmail.getEmails(idpessoa));
			
			List<String> msg = new ArrayList<String>();
			
			if(email.getEmail().isEmpty()) {
			msg.add("Email deve ser informado");
			}
			
			if(email.getNome().isEmpty()) {
				msg.add("Nome deve ser informado");
			}
			
			if(email.getFeedback().isEmpty()) {
				msg.add("feedback deve ser informado");
			}
			
			
		   modelAndView.addObject("msg", msg);
			return modelAndView;
		}
		
		if(bindingResult.hasErrors()) {
			throw new ValidationException("Email não é valido");
			
		}
		
		//para o envio de emails funcionar corretamente com o protocolo SMTP do gmail
		//deve-se ativar o acesso de apps menos seguros na parte de gerenciar conta do google na aba de segurança.
		try {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(465);
		mailSender.setUsername("");// inserir endereço de email aonde vai sair o envio.
		mailSender.setPassword(""); // senha do email
		
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.ssl.trust",  "*");
		properties.setProperty("mail.smtp.socketFactory.port",  "465");
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		mailSender.setJavaMailProperties(properties);
		
		
	 SimpleMailMessage mailMessage = new SimpleMailMessage();
	 mailMessage.setFrom(email.getEmail());
	 mailMessage.setTo("santos.g@aluno.ifsp.edu.br");
	 mailMessage.setSubject("Novo Email de : " + email.getNome());
	 mailMessage.setText(email.getFeedback());
	 
	
	 mailSender.send(mailMessage);
	 email.setPessoa(pessoa);
	 implEmail.save(email);
	 logger.info("Email enviado e salvo com sucesso");
	 redirectAttributes.addFlashAttribute("msg_resultado", "Email enviado com sucesso!");
	 Thread.sleep(5000);
	 t.start();
	}catch (MailSendException e) {
		e.getRootCause();
		logger.error("Erro ao enviar o email", email);
    }finally {
	ModelAndView andView = new ModelAndView("/emails");
	andView.addObject("pessoaobj", pessoa);
	andView.addObject("emails", implEmail.getEmails(idpessoa));
	return  andView;
	}
	
	
}
	
    @Cacheable(cacheNames = "remover emails", key = "#deletaemail")
	@GetMapping("/removerEmail/{idEmail}")
	public ModelAndView removerEmail(@PathVariable("idEmail") Long idEmail, HttpServletRequest request) {
    	Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	         Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
		Pessoa pessoa =  implEmail.findById(idEmail).get().getPessoa();
		Thread t = new Thread();
		
		try {
		implEmail.deleteById(idEmail);
		logger.info("Email removido com sucesso");
		Thread.sleep(5000);
		t.start();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao remover o email");
		}finally {
		ModelAndView modelAndView = new ModelAndView("/emails");
		modelAndView.addObject("pessoaobj", pessoa);
		modelAndView.addObject("emails", implEmail.getEmails(pessoa.getId()));
		return modelAndView;
		}
		
	}
	
	
	
	
}
