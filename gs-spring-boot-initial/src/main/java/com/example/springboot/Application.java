package com.example.springboot;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@SpringBootApplication
@EnableJpaRepositories("com.example.springboot.repository")
@EntityScan("com.example.springboot.model")
@ComponentScan("com.example.springboot.controller")
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}
	
	private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
		
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.addDialect(new Java8TimeDialect());
		engine.setTemplateResolver(templateResolver);
		
		return engine;
	}
	
}
