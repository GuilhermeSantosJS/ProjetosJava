package br.com.projetofinanceiro;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.ManagedBean;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.projetofinanceiro.reflexao.Reflexao;
import br.com.projetofinanceiro.reflexao.ReflexaoDespesa;
import br.com.projetofinanceiro.reflexao.ReflexaoEmail;
import br.com.projetofinanceiro.reflexao.ReflexaoPessoa;
import br.com.projetofinanceiro.reflexao.ReflexaoReceitas;
import br.com.projetofinanceiro.reflexao.ReflexaoTelefone;
import br.com.projetofinanceiro.reflexao.ReflexaoTransacao;
import br.com.projetofinanceiro.reflexao.ReflexaoUsuario;
import br.com.projetofinanceiro.reflexao_controller.ReflexaoDespesaController;
import br.com.projetofinanceiro.reflexao_controller.ReflexaoEmailController;
import br.com.projetofinanceiro.reflexao_controller.ReflexaoIndexController;
import br.com.projetofinanceiro.reflexao_controller.ReflexaoPessoaController;
import br.com.projetofinanceiro.reflexao_controller.ReflexaoReceitasController;
import br.com.projetofinanceiro.reflexao_controller.ReflexaoReportUtil;
import br.com.projetofinanceiro.reflexao_controller.ReflexaoTransacaoController;

@SpringBootApplication
@EntityScan(basePackages = "br.com.projetofinanceiro.model")
@ComponentScan(basePackages = { "br.*" })
@EnableJpaRepositories(basePackages = { "br.com.projetofinanceiro.repository" })
@EnableTransactionManagement
@EnableWebMvc
@RestController
public class Application implements WebMvcConfigurer {

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		SpringApplication.run(Application.class, args);

		// REFLEXAO MODELS
		// ------------------------------------------------------
		ReflexaoDespesa reflexaoDespesa = new ReflexaoDespesa();
		reflexaoDespesa.DespesaReflexao();
        // ----------------
		ReflexaoReceitas reflexaoReceitas = new ReflexaoReceitas();
		reflexaoReceitas.ReceitasReflexao();
		// ----------------
		ReflexaoTransacao reflexaoTransacao = new ReflexaoTransacao();
		reflexaoTransacao.TransacaoReflexao();
		// ----------------
		ReflexaoPessoa reflexaoPessoa = new ReflexaoPessoa();
		reflexaoPessoa.PessoaReflexao();
		// ----------------
		ReflexaoEmail reflexaoEmail = new ReflexaoEmail();
		reflexaoEmail.EmailReflexao();
		// ----------------
		ReflexaoTelefone reflexaoTelefone = new ReflexaoTelefone();
		reflexaoTelefone.TelefoneReflexao();
		// ----------------
		ReflexaoUsuario reflexaoUsuario = new ReflexaoUsuario();
		reflexaoUsuario.UsuarioReflexao();
		// ----------------
		// --------------------------------------------------------
		
		// REFLEXAO CONTROLLERS
	    // ------------------------------------------------------
		ReflexaoDespesaController reflexaoDespesaController = new ReflexaoDespesaController();
		reflexaoDespesaController.DespesaControllerReflexao();
		// --------------------
		ReflexaoEmailController reflexaoEmailController = new ReflexaoEmailController();
		reflexaoEmailController.EmailControllerReflexao();
		// --------------------
		ReflexaoReceitasController reflexaoReceitasController = new ReflexaoReceitasController();
		reflexaoReceitasController.ReceitasControllerReflexao();
		// --------------------
		ReflexaoPessoaController reflexaoPessoaController = new ReflexaoPessoaController();
		reflexaoPessoaController.PessoaControllerReflexao();
		// --------------------
		ReflexaoTransacaoController reflexaoTransacaoController = new ReflexaoTransacaoController();
		reflexaoTransacaoController.TransacaoControllerReflexao();
		// --------------------
		ReflexaoIndexController reflexaoIndexController = new ReflexaoIndexController();
		reflexaoIndexController.IndexControllerReflexao();
		// --------------------
		ReflexaoReportUtil reflexaoReportUtil = new ReflexaoReportUtil();
	    reflexaoReportUtil.ReportUtilReflexao();
	    // ----------------
	 	// --------------------------------------------------------
		
		
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("/login");
		registry.setOrder(Ordered.LOWEST_PRECEDENCE);
	}

}
