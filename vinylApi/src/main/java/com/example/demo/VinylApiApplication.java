package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import service.FileFinderService;


@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("model")
@ComponentScan("controller")
public class VinylApiApplication {

	public static void main(String[] args) {
		
		ApplicationContext ctx = SpringApplication.run(VinylApiApplication.class, args);
		
		FileFinderService fileFinderService = new FileFinderService();
		
		fileFinderService.findFile();

		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}

}
