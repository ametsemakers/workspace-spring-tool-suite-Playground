package service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Image;
import model.Vinyl;
import repository.ImageRepository;

@Service
public class ImageService {
	
	// still unsure of implementation
	// private static final String IMAGE_WEB_PATH = "";
	private ImageRepository imageRepository;
	
	public ImageService(@Autowired ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}
	
	@Transactional
	public Image createImage(Image image) {
		return imageRepository.save(image);
	}
	
	@Transactional
	public Image persistImage(Vinyl vinyl) {
		Image image = new Image();
		image.setVinyl(vinyl);
		image.setPath(vinyl.getId() + "_" + vinyl.getTitleAlbum());
	
		return image;
	}

}
