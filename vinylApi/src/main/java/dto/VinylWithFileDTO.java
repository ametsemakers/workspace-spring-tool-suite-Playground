package dto;

import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.Nullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VinylWithFileDTO {

private int id;
	
	private String artist;
	
	private String titleAlbum;
	
	private String label;
	
	private String country;
	
	private String catNb;
	
	private String yearOriginal;
	
	private String yearEdition;
	
	@Nullable
	private MultipartFile file;
}
