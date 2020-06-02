package controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import dto.VinylDTO;
import model.Vinyl;
import service.VinylAlreadyExistsException;
import service.VinylNotFoundException;
import service.VinylService;

@CrossOrigin
@RestController
public class VinylController {
	
	@Autowired
	VinylService vinylService;
	
	@ExceptionHandler(VinylNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String handleException(VinylNotFoundException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(VinylAlreadyExistsException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public String handleException(VinylAlreadyExistsException e) {
		return e.getMessage();
	}
	
	@CrossOrigin
	@GetMapping("/api/vinyls/page={page}/size={size}")
	public Iterable<Vinyl> getAll(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		Iterable<Vinyl> vinyls = vinylService.getAllVinyls(page, size);
		
		return vinyls;
	}
	
	@GetMapping("/api/vinyls")
	public Iterable<Vinyl> getAllAtOnce() {
		return vinylService.getAllAtOnce();
	}
	
	@CrossOrigin
	@GetMapping("/api/vinyl/{id}")
	public Vinyl getOneById(@PathVariable(value = "id") int id) throws VinylNotFoundException {
		
		Vinyl vinyl = vinylService.getOneById(id);
		
		return vinyl;
	}
	
	@CrossOrigin
	@PostMapping("/api/file")
	public void sendFile(@RequestParam("file") MultipartFile file) {
		System.out.println(file.getOriginalFilename());
	}
	
	@CrossOrigin
	@PostMapping("/api/vinyl")
	public Vinyl createVinyl(@ModelAttribute("vinyl") Vinyl vinyl) throws VinylAlreadyExistsException {
		
		return vinylService.createVinyl(vinyl);
	}
	
	@CrossOrigin
	@PutMapping("/api/vinyl/{id}")
	public Vinyl updateOrCreateVinyl(@PathVariable(value = "id") int id, @ModelAttribute("vinyl") Vinyl vinyl) throws VinylAlreadyExistsException {
		
		try {
			return vinylService.updateVinyl(id, vinyl);
		} catch (VinylNotFoundException e) {
			return vinylService.createVinyl(vinyl);
		}
//		Vinyl vinylToUpdate = new Vinyl();
//		Optional<Vinyl> optionalVinylType = vinylRepository.findById(id);
//		
//		if(optionalVinylType.isPresent()) {
//			vinylToUpdate = optionalVinylType.get();
//			
//			vinylToUpdate = vinyl;
//			
//			vinylRepository.save(vinylToUpdate);
//		}
//		return vinylToUpdate;	
		
	}
	
	@CrossOrigin
	@DeleteMapping("/api/vinyl/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteVinyl(@PathVariable(value = "id") int id) throws VinylNotFoundException {
		
		vinylService.deleteVinyl(id);
	}
}
