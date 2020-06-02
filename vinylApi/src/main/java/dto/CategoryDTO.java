package dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
	
	private int id;
	
	private String name;
	
	private List<VinylDTO> vinyls;
}