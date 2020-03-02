package mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dto.VinylDTO;
import model.Vinyl;

@Mapper
public interface VinylMapper {
	
	VinylMapper INSTANCE = Mappers.getMapper(VinylMapper.class);

	VinylDTO vinylToVinylDto(Vinyl vinyl);
	
	Vinyl vinylDtoToVinyl(VinylDTO vinylDTO);
}
