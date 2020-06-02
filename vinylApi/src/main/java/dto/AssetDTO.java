package dto;

import java.io.InputStream;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssetDTO {

	private long id;
	
	private long length;
	
	private String contentType;
	
	private InputStream inputStream;
	
	private byte[] content;
}
