package repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import model.Song;
import model.Vinyl;

public interface SongRepository extends CrudRepository<Song, Integer> {

	List<Song> findAll(Pageable pageable);
	
	List<Song> findByVinyl(Vinyl vinyl);
	
	List<Song> findByTitleSong(String titleSong);
	
	List<Song> findByArtist(String artist);
}
