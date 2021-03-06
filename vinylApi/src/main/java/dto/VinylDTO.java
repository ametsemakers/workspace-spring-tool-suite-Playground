package dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VinylDTO {
	
	private int id;
	
	private String artist;
	
	private String titleAlbum;
	
	private String label;
	
	private String country;
	
	private String catNb;
	
	private String yearOriginal;
	
	private String yearEdition;
	
	private ImageDTO image;
	
	private List<SongDTO> songs;
	
	private List<CategoryDTO> categories;
}
