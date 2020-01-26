package controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Acteur;
import repository.ActeurRepository;

@RestController
public class ActeurController {

	@Autowired
	ActeurRepository ar;

	@GetMapping("/api/acteurs")
	public Iterable<Acteur> findAll() {

		return ar.findAll();
	}

	@GetMapping("/api/acteur/{id}")
	public Optional<Acteur> findOneById(@PathVariable(value = "id") long id, @ModelAttribute("acteur") Acteur acteur) {

		Optional<Acteur> foundUser = ar.findById((int) id);

		return foundUser;
	}

	@PostMapping("/api/acteur")
	public Iterable<Acteur> createUser(@ModelAttribute("acteur") Acteur acteur) {

		ar.save(acteur);

		return ar.findAll();
	}

	@PutMapping("/api/acteur/{id}")
	public Acteur updateUser(@PathVariable(value = "id") long id, @ModelAttribute("acteur") Acteur acteur) {

		Acteur u = new Acteur();
		Optional<Acteur> optional = ar.findById((int) id);
		if (optional.isPresent()) {
			u = optional.get();
			u = acteur;
			ar.save(u);
		}
		return u;
	}

	@DeleteMapping("api/acteur/{id}")
	public Iterable<Acteur> deleteUser(@PathVariable(value = "id") long id, @ModelAttribute("acteur") Acteur acteur) {

		ar.deleteById((int) id);

		return ar.findAll();
	}
}
