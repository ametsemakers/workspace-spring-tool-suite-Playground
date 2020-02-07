package controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Vinyl;
import repository.VinylRepository;

@RestController
public class VinylController {

	@Autowired
	VinylRepository vinylRepository;
	
	@CrossOrigin
	@GetMapping("/api/vinyls")
	public Iterable<Vinyl> findAll() {
		
		Pageable firstPage = PageRequest.of(0, 10);
		Iterable<Vinyl> vinyls = vinylRepository.findAll(firstPage);
		
		return vinyls;
	}
	
	@GetMapping("/api/vinyl/{id}")
	public Optional<Vinyl> getOneById(@PathVariable(value = "id") int id, @ModelAttribute("vinyl") Vinyl vinyl ) {
		
		Optional<Vinyl> selectedVinyl = vinylRepository.findById(id);
		
		return selectedVinyl;
	}
	
	@PostMapping("/api/vinyl")
	public Vinyl createVinyl(@ModelAttribute("vinyl") Vinyl vinyl) {
		
		vinylRepository.save(vinyl);
		
		return vinyl;
	}
	
	@PutMapping("/api/vinyl/{id}")
	public Vinyl updateVinyl(@PathVariable(value = "id") int id, @ModelAttribute("vinyl") Vinyl vinyl) {
		
		Vinyl vinylToUpdate = new Vinyl();
		Optional<Vinyl> optionalVinylType = vinylRepository.findById(id);
		
		if(optionalVinylType.isPresent()) {
			vinylToUpdate = optionalVinylType.get();
			
			vinylToUpdate = vinyl;
			
			vinylRepository.save(vinylToUpdate);
		}
		return vinylToUpdate;		
	}
	
	@DeleteMapping("/api/vinyl/{id}")
	public String deleteVinyl(@PathVariable(value = "id") int id) {
		
		Vinyl vinylToDelete = new Vinyl();
		Optional<Vinyl> vinyl = vinylRepository.findById(id);
		
		if(vinyl.isPresent()) {
			vinylToDelete = vinyl.get();
			
			vinylRepository.delete(vinylToDelete);
			
			return "Le vinyle est supprimé";
		}
		return "Le vinyle est inconnu";
	}
}
