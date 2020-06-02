package service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import model.Asset;
import model.Image;
import model.Vinyl;
import repository.VinylRepository;

@Service
public class VinylService {
	
	@Autowired
	private VinylRepository vinylRepository;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private AssetService assetService;
	
	public VinylService(@Autowired VinylRepository vinylRepository) {
		this.vinylRepository = vinylRepository;
	}
	
	public Iterable<Vinyl> getAllVinyls(int page, int size) {
		Pageable requestedPage = PageRequest.of(page, size);
		Iterable<Vinyl> vinyls = vinylRepository.findAll(requestedPage);
		
		return vinyls;
	}
	
	public Iterable<Vinyl> getAllAtOnce() {
		return vinylRepository.findAll();
	}
	
	public Vinyl getOneById(int id) throws VinylNotFoundException {
		Optional<Vinyl> vinyl = vinylRepository.findById(id);
		if (!vinyl.isPresent()) {
			throw new VinylNotFoundException("The vinyl with id " + id + " doesn't exist.");
		}
		return vinyl.get();
	}
	
	@Transactional
	public Vinyl createVinyl(Vinyl vinyl) throws VinylAlreadyExistsException {
		if (vinylRepository.existsById(vinyl.getId())) {
			throw new VinylAlreadyExistsException("The vinyl with the id " + vinyl.getId() + " (" + vinyl.getArtist() + " - " + vinyl.getTitleAlbum() + ") already exists.");
		}
		vinylRepository.save(vinyl);
		// Création de l'image nécessite l'id du vinyl, alors je l'ajout après 
		// Asset asset = Asset.buidImageAsset(file.getBytes(), file.getContentType());
		
		// Image image = imageService.persistImage(vinyl);
		// vinyl.setImage(image);
		return vinyl;
	}
	
	@Transactional
	public void deleteVinyl(int id) throws VinylNotFoundException {
		if (!vinylRepository.existsById(id)) {
			throw new VinylNotFoundException("The vinyl with id " + id + " doesn't exist.");
		}
		
		vinylRepository.deleteById(id);
	}
	
	@Transactional
	public Vinyl updateVinyl(int id, Vinyl vinyl) throws VinylNotFoundException {
		Vinyl vinylFoundById = this.getOneById(id);
		vinylFoundById = vinyl;
		
		return vinylFoundById;
	}

}
