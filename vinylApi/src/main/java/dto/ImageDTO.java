package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO {

	private int id;
	
	private String path;
	
	private VinylDTO vinyl;
	
	private AssetDTO asset;
}
