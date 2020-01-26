package repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import model.Vinyl;

public interface VinylRepository extends CrudRepository<Vinyl, Integer> {
	
	List<Vinyl> findAll(Pageable pageable);

	List<Vinyl> findByArtist(String artist, Pageable pageable);
	
	List<Vinyl> findByTitleAlbum(String titleAlbum);

}
