package com.example.springboot.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;

import com.example.springboot.model.Entreprise;


public class EntrepriseRestJpaController {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("entreprise-jpa");
	EntityManager em = entityManagerFactory.createEntityManager();
	
	public void create() {
		
		Entreprise e = new Entreprise();
		
		e.setRaisonSocial("test");
		
	}
}
