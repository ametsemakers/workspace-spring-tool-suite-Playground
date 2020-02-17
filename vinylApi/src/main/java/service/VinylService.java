package service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import model.Vinyl;
import repository.VinylRepository;

@Service
public class VinylService {
	
	private VinylRepository vinylRepository;
	
	public VinylService(@Autowired VinylRepository vinylRepository) {
		this.vinylRepository = vinylRepository;
	}
	
	public Iterable<Vinyl> getAllVinyls(int page, int size) {
		Pageable firstPage = PageRequest.of(page, size);
		Iterable<Vinyl> vinyls = vinylRepository.findAll(firstPage);
		
		return vinyls;
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
		return vinylRepository.save(vinyl);
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
