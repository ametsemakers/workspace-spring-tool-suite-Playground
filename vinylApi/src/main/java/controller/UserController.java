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

import model.User;
import repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/api/users")
	public Iterable<User> findAll() {
		
		Iterable<User> users = userRepository.findAll();
		
		return users;
	}
	
	@GetMapping("/api/user/{id}")
	public Optional<User> getUserById(@PathVariable(value = "id") int id, @ModelAttribute("user") User user ) {
		
		Optional<User> selectedUser = userRepository.findById(id);
		
		return selectedUser;
	}
	
	@PostMapping("/api/user")
	public User createUser(@ModelAttribute("user") User user) {
		
		userRepository.save(user);
		
		return user;
	}
	
	@PutMapping("/api/user/{id}")
	public User updateUser(@PathVariable(value = "id") int id, @ModelAttribute("user") User user) {
		
		User userToUpdate = new User();
		Optional<User> optionalUserType = userRepository.findById(id);
		
		if(optionalUserType.isPresent()) {
			userToUpdate = optionalUserType.get();
			
			userToUpdate = user;
			
			userRepository.save(userToUpdate);
		}
		return userToUpdate;		
	}
	
	@DeleteMapping("/api/user/{id}")
	public String deleteUser(@PathVariable(value = "id") int id) {
		
		User userToDelete = new User();
		Optional<User> user = userRepository.findById(id);
		
		if(user.isPresent()) {
			userToDelete = user.get();
			
			userRepository.delete(userToDelete);
			
			return "L'utilisateur est supprimé";
		}
		return "L'utilisateur est inconnu";
	}
}
