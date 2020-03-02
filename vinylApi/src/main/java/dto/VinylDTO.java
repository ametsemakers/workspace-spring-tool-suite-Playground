package dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import model.Image;
import model.Song;

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
	
	private Image image;
	
	private List<Song> songs;
}
