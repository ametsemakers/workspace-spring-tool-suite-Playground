package controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import model.Song;
import repository.SongRepository;
import repository.VinylRepository;
import service.NoSongsOnVinylException;
import service.SongAlreadyExistsException;
import service.SongNotFoundException;
import service.SongService;
import service.VinylNotFoundException;

@RestController
public class SongController {
	
	@Autowired
	SongService songService;

	@Autowired
	SongRepository songRepository;
	
	@Autowired
	VinylRepository vinylRepository;
	
	@ExceptionHandler(SongNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String handleException(SongNotFoundException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(SongAlreadyExistsException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public String handleException(SongAlreadyExistsException e) {
		return e.getMessage();
	}
	
	@GetMapping("/api/songs/page={page}/size={size}")
	public Iterable<Song> findAll(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		
		return songService.getAllSongs(page, size);
	}
	
	@GetMapping("/api/song/{id}")
	public Song getSongById(@PathVariable(value = "id") int id) throws SongNotFoundException {
		
		return songService.getSongById(id);
	}
	
	@GetMapping("/api/vinyl/{idVinyl}/songs")
	public Iterable<Song> findSongsFromVinyl(@PathVariable(value = "idVinyl") int idVinyl) throws VinylNotFoundException, NoSongsOnVinylException {
		
		return songService.getSongsFromVinyl(idVinyl);
	}
	
	@PostMapping("/api/song")
	public Song createSong(@ModelAttribute("song") Song song) throws SongAlreadyExistsException {
		
		return songService.createSong(song);
	}
	
	@PutMapping("/api/song/{id}")
	public Song updateSong(@PathVariable(value = "id") int id, @ModelAttribute("song") Song song) throws SongAlreadyExistsException {
		
		try {
			return songService.updateSong(id, song);
		} catch (SongNotFoundException e) {
			return songService.createSong(song);
		}
	}
	
	@DeleteMapping("/api/song/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteSong(@PathVariable(value = "id") int id) throws SongNotFoundException {
		
		songService.deleteSong(id);
	}
}
