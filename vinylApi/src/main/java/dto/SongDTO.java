package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDTO {
	
	private int id;
	
	private String title_song;
	
	private String artist;
	
	private String alternate_info;
	
	private String feat;
	
	private String title_album;
	
	private String side;
	
	private int position;
	
	private VinylDTO vinyl;
}