package br.com.projetofinanceiro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebConfigSecurity  extends  WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;
	
	@Override // Configura as solicitações de acesso por Http
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
		.disable() // Desativa as configurações padrão de memória.
		.authorizeRequests() // Permitir restringir acessos.
		.antMatchers(HttpMethod.GET, "/").permitAll() 
		.antMatchers(HttpMethod.GET, "/cadastropessoa").hasAnyRole("ADMIN")  // Qualquer usuário acessa a pagina inicial.
		.anyRequest().authenticated()
		.and().formLogin().permitAll() // Permite qualquer usuário
		.loginPage("/login")
		.defaultSuccessUrl("/cadastropessoa")
		.failureUrl("/login?error=true")
		.and().logout().logoutSuccessUrl("/login") // Mapeia URL  de logout e invalida usuário autenticado
		.logoutRequestMatcher( new AntPathRequestMatcher("/logout"));
 	}
	
	
	@Override // Cria autenticação do usuário com banco de dados ou em memória
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(implementacaoUserDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
		
		
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
		.withUser("Guilherme")
		.password("$2a$10$J9UaNmePf1Gmtpfpw3K1YeYydbboHSqbQQndpDbJ4Ukp1Bt4cpDwu")
		.roles("ADMIN");
		
	}
	
	
	@Override // Ignora URL especificas.
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/materialize/**");
		
		
		
	}
	
	
}
