package com.example.springboot.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
		
		model.addAttribute("appName", appName);
		model.addAttribute("localDateTime", LocalDateTime.now());
		
		return "date";
	}
}
