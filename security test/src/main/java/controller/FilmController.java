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

import model.Film;
import repository.FilmRepository;

@RestController
public class FilmController {

	@Autowired
	private FilmRepository er;

	@GetMapping("/")
	public String index() {
		return "bijour";
	}

	@GetMapping("/api/users")
	public Iterable<Film> findAll() {

		return er.findAll();
	}

	@GetMapping("/api/user/{id}")
	public Optional<Film> findOneById(@PathVariable(value = "id") long id, @ModelAttribute("film") Film user) {

		Optional<Film> foundUser = er.findById((int) id);

		return foundUser;
	}

	@PostMapping("/api/user")
	public Iterable<Film> createUser(@ModelAttribute("film") Film user) {

		er.save(user);

		return er.findAll();
	}

	@PutMapping("/api/user/{id}")
	public Film updateUser(@PathVariable(value = "id") long id, @ModelAttribute("film") Film user) {

		Film u = new Film();
		Optional<Film> optional = er.findById((int) id);
		if (optional.isPresent()) {
			u = optional.get();
			u = user;
			er.save(u);
		}
		return u;
	}

	@DeleteMapping("api/user/{id}")
	public Iterable<Film> deleteUser(@PathVariable(value = "id") long id, @ModelAttribute("film") Film user) {

		er.deleteById((int) id);

		return er.findAll();
	}
}
