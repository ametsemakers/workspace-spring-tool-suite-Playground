package controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Image;
import repository.ImageRepository;

@RestController
public class ImageController {

	@Autowired
	ImageRepository imageRepository;
	
	@GetMapping("/api/images")
	public Iterable<Image> findAll() {
		
		Pageable firstPage = PageRequest.of(0, 10);
		Iterable<Image> images = imageRepository.findAll(firstPage);
		
		return images;
	}
	
	@GetMapping("/api/image/{id}")
	public Optional<Image> getImageById(@PathVariable(value = "id") int id, @ModelAttribute("image") Image image ) {
		
		Optional<Image> selectedImage = imageRepository.findById(id);
		
		return selectedImage;
	}
	
	@PostMapping("/api/image")
	public Image createImage(@ModelAttribute("image") Image image) {
		
		imageRepository.save(image);
		
		return image;
	}
	
	@PutMapping("/api/image/{id}")
	public Image updateImage(@PathVariable(value = "id") int id, @ModelAttribute("image") Image image) {
		
		Image imageToUpdate = new Image();
		Optional<Image> optionalImageType = imageRepository.findById(id);
		
		if(optionalImageType.isPresent()) {
			imageToUpdate = optionalImageType.get();
			
			imageToUpdate = image;
			
			imageRepository.save(imageToUpdate);
		}
		return imageToUpdate;		
	}
	
	@DeleteMapping("/api/image/{id}")
	public String deleteImage(@PathVariable(value = "id") int id) {
		
		Image imageToDelete = new Image();
		Optional<Image> image = imageRepository.findById(id);
		
		if(image.isPresent()) {
			imageToDelete = image.get();
			
			imageRepository.delete(imageToDelete);
			
			return "L'image est supprimée";
		}
		return "L'image est inconnue";
	}
}
