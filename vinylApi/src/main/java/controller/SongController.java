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

import model.Song;
import model.Vinyl;
import repository.SongRepository;
import repository.VinylRepository;

@RestController
public class SongController {

	@Autowired
	SongRepository songRepository;
	
	@Autowired
	VinylRepository vinylRepository;
	
	@GetMapping("/api/songs")
	public Iterable<Song> findAll() {
		
		Pageable firstPage = PageRequest.of(0, 10);
		Iterable<Song> songs = songRepository.findAll(firstPage);
		
		return songs;
	}
	
	@GetMapping("/api/song/{id}")
	public Optional<Song> getSongById(@PathVariable(value = "id") int id, @ModelAttribute("song") Song song ) {
		
		Optional<Song> selectedSong = songRepository.findById(id);
		
		return selectedSong;
	}
	
	@GetMapping("/api/vinyl/{vinyl_id}/songs")
	public Iterable<Song> findSongsFromVinyl(@PathVariable(value = "vinyl_id") int id) {
		
		
		Optional<Vinyl> vinyl = vinylRepository.findById(id);
		Vinyl vinylToSearch = new Vinyl();
		
		if(vinyl.isPresent()) {

			vinylToSearch = vinyl.get();
		}
	
		Iterable<Song> songs = songRepository.findByVinyl(vinylToSearch);
		
		return songs;
	}
	
	@PostMapping("/api/song")
	public Song createSong(@ModelAttribute("song") Song song) {
		
		songRepository.save(song);
		
		return song;
	}
	
	@PutMapping("/api/song/{id}")
	public Song updateSong(@PathVariable(value = "id") int id, @ModelAttribute("song") Song song) {
		
		Song songToUpdate = new Song();
		Optional<Song> optionalSongType = songRepository.findById(id);
		
		if(optionalSongType.isPresent()) {
			songToUpdate = optionalSongType.get();
			
			songToUpdate = song;
			
			songRepository.save(songToUpdate);
		}
		return songToUpdate;		
	}
	
	@DeleteMapping("/api/song/{id}")
	public String deleteSong(@PathVariable(value = "id") int id) {
		
		Song songToDelete = new Song();
		Optional<Song> song = songRepository.findById(id);
		
		if(song.isPresent()) {
			songToDelete = song.get();
			
			songRepository.delete(songToDelete);
			
			return "La chanson est supprimée";
		}
		return "La chanson est inconnue";
	}
}
