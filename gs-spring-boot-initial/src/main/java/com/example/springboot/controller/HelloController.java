package com.example.springboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.Employe;
import com.example.springboot.model.Entreprise;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	@RequestMapping("/")
	public Object index() throws Exception {
		
		Entreprise e = new Entreprise();
		
		return e;
	}
	
	@RequestMapping("/employe")
	public Object employé() {
		
		Employe u1 = null;
		
		try {
			u1 = new Employe();
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		return u1;
	}

}
