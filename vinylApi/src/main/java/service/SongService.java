package service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import model.Song;
import model.Vinyl;
import repository.SongRepository;

@Service
public class SongService {

	private SongRepository songRepository;
	
	private VinylService vinylService;
	
	public SongService(@Autowired SongRepository songRepository) {
		this.songRepository = songRepository;
	}
	
	public Iterable<Song> getAllSongs(int page, int size) {
		
		Pageable requestedPage = PageRequest.of(page, size);
		return songRepository.findAll(requestedPage);
	}
	
	public Song getSongById(int id) throws SongNotFoundException {
		Optional<Song> song = songRepository.findById(id);
		if (!song.isPresent()) {
			throw new SongNotFoundException("The song with id " + id + " doesn't exist.");
		}
		return song.get();
	}
	
	public Iterable<Song> getSongsFromVinyl(int idVinyl) throws VinylNotFoundException, NoSongsOnVinylException {
		Vinyl vinyl = vinylService.getOneById(idVinyl);
		
		if (songRepository.findByVinyl(vinyl).isEmpty()) {
			throw new NoSongsOnVinylException("There are no songs registered on vinyl with id " + vinyl.getId());
		}
		return songRepository.findByVinyl(vinyl);
	}
	
	@Transactional
	public Song createSong(Song song) throws SongAlreadyExistsException {
		if (songRepository.existsById(song.getId())) {
			throw new SongAlreadyExistsException("The song with the id " + song.getId() + " (" + song.getTitleSong() + " on album: " + song.getArtist() + " - " + song.getTitleAlbum() + ") already exists.");
		}
		return songRepository.save(song);
	}
	
	@Transactional
	public void deleteSong(int id) throws SongNotFoundException {
		if (songRepository.existsById(id)) {
			throw new SongNotFoundException("The song with id " + id + " doesn't exist.");
		}
		songRepository.deleteById(id);
	}
	
	@Transactional
	public Song updateSong(int id, Song song) throws SongNotFoundException {
		Song songFoundById = this.getSongById(id);
		songFoundById = song;
		
		return songFoundById;
	}
}
