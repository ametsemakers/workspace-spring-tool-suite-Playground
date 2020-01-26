package com.example.springboot.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springboot.model.Saison;

@Controller
public class SimpleController {

	@Value("${spring.application.name}")
	String appName;
	
	@GetMapping("/home")
	public String homepage(Model model) {
		
		model.addAttribute("appName", appName);
		
		return "home";
	}
	
	@GetMapping("/date")
	public String date(Model model) {
		
		ArrayList<Saison> saisons = new ArrayList<>();
		saisons.add(new Saison("Hiver", true));
		saisons.add(new Saison("Printemps", false));
		saisons.add(new Saison("Eté", false));
		saisons.add(new Saison("Automne", false));
		
		model.addAttribute("appName", appName);
		model.addAttribute("localDateTime", LocalDateTime.now());
		model.addAttribute("saisons", saisons);
		
		return "date";
	}
}
