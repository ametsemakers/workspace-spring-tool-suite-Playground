package repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import model.Vinyl;

public interface VinylRepository extends CrudRepository<Vinyl, Integer> {
	
	@Query(value = "SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'vinyl' AND table_schema = 'vinyl_api'", nativeQuery = true)
	int getNextId();
	
	List<Vinyl> findAll(Pageable pageable);

	List<Vinyl> findByArtist(String artist, Pageable pageable);
	
	List<Vinyl> findByTitleAlbum(String titleAlbum);

	Vinyl findTopByOrderByIdDesc();
	
	
}
