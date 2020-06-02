package mapper;

import java.util.List;

import org.mapstruct.Mapper;

import dto.SongDTO;
import model.Song;

@Mapper
public interface SongMapper {

	SongDTO songToSongDto(Song song);
	
	Song songDtoToSong(SongDTO songDto);
	
	List<SongDTO> songsToSongDtos(List<Song> songs);
	
	List<Song> songDtosToSongs(List<SongDTO> songDtos);
}
