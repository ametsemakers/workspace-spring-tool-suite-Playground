package service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import model.Asset;
import model.Image;
import model.Vinyl;
import repository.AssetRepository;
import repository.ImageRepository;

@Service
public class ImageService {
	
	@Autowired
	private AssetService assetService;
	
	@Autowired
	private VinylService vinylService;
	
	@Autowired
	private ImageRepository imageRepository;
	
	public ImageService(@Autowired ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}
	
	public Image getById(int id) {
		return imageRepository.findById(id).get();
	}
	
	public List<Image> getAll(int page, int size) {
		Pageable firstPage = PageRequest.of(page, size);
		return imageRepository.findAll(firstPage);
	}
	
	@Transactional
	public Image createImage(int vinylId, MultipartFile file) throws IOException, VinylNotFoundException {
		
		Asset asset = new Asset();
		if (file.getOriginalFilename() != null) {
			asset = Asset.buildFileAsset(file.getBytes(), file.getContentType());
			
			asset = assetService.saveAsset(asset);
		}
		Image image = new Image();
		image.setAsset(asset);
		
		Vinyl vinyl = vinylService.getOneById(vinylId);
		image.setVinyl(vinyl);
		image.setPath(vinyl.getId() + " - " + vinyl.getTitleAlbum());
		
		return imageRepository.save(image);
	}
	
	public Image deleteImage(int id) {
		Image image = imageRepository.findById(id).orElse(null);
		if (image != null) {
			Asset asset = image.getAsset();
			
			if (asset != null) {
				image.setAsset(null);
				assetService.deleteAsset(asset);
			}
			imageRepository.delete(image);
		}
		return image;
	}
	
	@Transactional
	public Image persistImage(Vinyl vinyl, Asset asset) {
		Image image = new Image();
		image.setVinyl(vinyl);
		image.setPath(vinyl.getId() + "_" + vinyl.getTitleAlbum());
		image.setAsset(asset);
	
		return image;
	}

}
