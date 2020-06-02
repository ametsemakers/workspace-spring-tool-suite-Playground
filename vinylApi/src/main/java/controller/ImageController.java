package controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import model.Image;
import repository.ImageRepository;
import service.ImageService;
import service.VinylNotFoundException;

@RestController
public class ImageController {

	@Autowired
	ImageService imageService;
	
	@GetMapping("/api/images")
	public Iterable<Image> findAll() {
		
		int page = 1;
		int size = 20;
		Iterable<Image> images = imageService.getAll(page, size);
		
		return images;
	}
	
	@GetMapping("/api/image/{id}")
	public Image getImageById(@PathVariable(value = "id") int id, @ModelAttribute("image") Image image ) {
		
		return imageService.getById(id);
	}
	
	@CrossOrigin
	@PostMapping("/api/vinyl/{vinylId}/image")
	public Image createImage(@PathVariable(value = "vinylId") int vinylId, @RequestParam("file") MultipartFile file) throws IOException, VinylNotFoundException {
		
		if (file == null || file.isEmpty() || file.getBytes() == null) {
			throw new RuntimeException("Empty file");
		}
		return imageService.createImage(vinylId, file);
	}
	
//	@PutMapping("/api/image/{id}")
//	public Image updateImage(@PathVariable(value = "id") int id, @ModelAttribute("image") Image image) {
//		
//		Image imageToUpdate = new Image();
//		Optional<Image> optionalImageType = imageRepository.findById(id);
//		
//		if(optionalImageType.isPresent()) {
//			imageToUpdate = optionalImageType.get();
//			
//			imageToUpdate = image;
//			
//			imageRepository.save(imageToUpdate);
//		}
//		return imageToUpdate;		
//	}
	
	@DeleteMapping("/api/image/{id}")
	public Image deleteImage(@PathVariable(value = "id") int id) {
		
		return imageService.deleteImage(id);
	}
}
