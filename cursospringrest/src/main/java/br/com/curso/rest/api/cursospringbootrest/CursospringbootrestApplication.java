package br.com.curso.rest.api.cursospringbootrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@SpringBootApplication
@EntityScan(basePackages= "br.com.curso.rest.api.model")
@ComponentScan(basePackages= {"br.*"})
@EnableJpaRepositories(basePackages = {"br.com.curso.rest.api.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableAutoConfiguration
public class CursospringbootrestApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CursospringbootrestApplication.class, args);
	}

	
	/*Mapeamento global reflete em todo o sistema*/
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/usuario/**").allowedMethods("POST").allowedOrigins("*");
		/* Exemplo - Liberando apenas requisições post para os usuários */
	}
	
}
