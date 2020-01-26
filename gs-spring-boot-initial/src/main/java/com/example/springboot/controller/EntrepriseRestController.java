package com.example.springboot.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.EntrepriseSubscribeValidator;
import com.example.springboot.exception.EntrepriseValidationException;
import com.example.springboot.model.Entreprise;
import com.example.springboot.repository.EntrepriseRepository;
import com.example.springboot.service.FormatDateToStringService;
import com.example.springboot.service.PopulateEntrepriseArrayListService;

@RestController
@EnableWebSecurity
public class EntrepriseRestController {
	
	@Autowired
	private EntrepriseRepository er;
	//private ArrayList<Entreprise> entreprises = new ArrayList<>();
	
	@GetMapping("/api/entreprises")
	public Iterable<Entreprise> findAll() {
		 
		return er.findAll();   
	}
	
	@GetMapping("/api/entreprise/{id}")
	public Optional<Entreprise> findOneById(@PathVariable(value="id") long id, @ModelAttribute("entreprise") Entreprise entreprise) {
		
		Optional<Entreprise> e = er.findById(id);
		
		
		// add exception
		return e;
	}
	
	@Secured("ADMIN")
	@PostMapping("/api/entreprise")
	public Entreprise createEntreprise(@ModelAttribute("entreprise") Entreprise entreprise) {
		
		er.save(entreprise);
		
		return entreprise;
	}
	
	@PutMapping("/api/entreprise/{id}")
	public Entreprise updateEntreprise(@PathVariable(value="id") long id, @ModelAttribute("entreprise") Entreprise entreprise) {
		
		Entreprise e = new Entreprise();
		Optional<Entreprise> optional = er.findById(id);
		if (optional.isPresent()) {
			e = optional.get();
			e = entreprise;
			er.save(e);
		}
		return e;
	}
	
	@DeleteMapping("/api/entreprise/{id}")
	public Iterable<Entreprise> deleteEntreprise(@PathVariable(value="id") long id, @ModelAttribute("entreprise") Entreprise entreprise) {
		
		er.deleteById(id);
		
		return er.findAll();
	}
	
	@GetMapping("/api/entreprise/filter/{raisonSoc}")
	public Iterable<Entreprise> findByRaisonSocial(@PathVariable(value="raisonSoc") String raisonSoc, @ModelAttribute("entreprise") Entreprise entreprise) {
		
		return er.findByRaisonSocial(raisonSoc);
	}

// méthode arraylist	
//	@GetMapping("/entreprises")
//	public ArrayList<Entreprise> entreprises(Model model) {
//		
//		if (entreprises.size() < 1) {
//			
//			entreprises = PopulateEntrepriseArrayListService.populate();	
//		}		
//		return entreprises;
//	}
	
//	@GetMapping("/entreprise/{id}")
//	public Object entreprise(@PathVariable(value="id") int id, @ModelAttribute("entreprise") Entreprise entreprise, Model model) {
//		
//		entreprises = PopulateEntrepriseArrayListService.populate();
//		
//		model.addAttribute("entreprise", new Entreprise());
//		
//		entreprise = entreprises.get(id);
//		
//		return entreprise;
//	}
	
//	@GetMapping("/entreprises/filter/{nbSal}")
//	public ArrayList<Entreprise> filterEntreprises(@PathVariable(value="nbSal") int nbSal, Model model) {
//		
//		entreprises = PopulateEntrepriseArrayListService.populate();
//		ArrayList<Entreprise> filtered = new ArrayList<>();
//		
//		for (int i = 0; i < entreprises.size(); i++) {
//			
//			if (entreprises.get(i).getNbrSalaries() > nbSal) {
//				
//				filtered.add(entreprises.get(i));
//			}
//		}
//		return filtered;
//	}
	
//	@PostMapping("/entreprise")
//	public Entreprise addEntreprise(@Valid @ModelAttribute("entreprise") Entreprise entreprise, BindingResult result, Model model) {
//		
//		if (entreprises.size() < 1) {
//			
//			entreprises = PopulateEntrepriseArrayListService.populate();
//		}
//		if (result.hasErrors()) {
//			
//			throw new EntrepriseValidationException();
//		}
//		
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("entreprise-jpa");
//		EntityManager em = entityManagerFactory.createEntityManager();
//		
//		model.addAttribute("entreprise", new Entreprise());
//		
//		em.persist(entreprise);
//		//int newId = entreprises.size();
//		//entreprise.setId(newId);
//		//entreprise.setInscriptionToString(FormatDateToStringService.formatDate().format(entreprise.getInscription()));
//		//entreprises.add(entreprise);
//		
//		return entreprise;
//	}
	
//	@PutMapping("/entreprise/{id}")
//	public ArrayList<Entreprise> updateEntreprise(@PathVariable(value="id") int id, @Valid @ModelAttribute("entreprise") Entreprise entreprise, BindingResult result, Model model) {
//		
//		if (entreprises.size() < 1) {
//			
//			entreprises = PopulateEntrepriseArrayListService.populate();
//		}
//		if (result.hasErrors()) {
//			
//			throw new EntrepriseValidationException();
//		}
//		
//		model.addAttribute("entreprise", new Entreprise());
//			
//		entreprises.set(id, entreprise);
//			
//		return entreprises;
//	}
	
//	@DeleteMapping("/entreprise/{id}") 
//	public ArrayList<Entreprise> deleteEntreprise(@PathVariable(value="id") int id, Model model) {
//		
//		if (entreprises.size() < 1) {
//			
//			entreprises = PopulateEntrepriseArrayListService.populate();
//		}
//		
//		Entreprise entreprise = entreprises.get(id);
//		
//		entreprises.remove(entreprise);
//		
//		return entreprises;
//	}
	
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.addValidators(new EntrepriseSubscribeValidator());
//	}
}
