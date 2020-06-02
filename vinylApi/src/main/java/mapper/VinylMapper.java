package mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dto.VinylDTO;
import model.Vinyl;

@Mapper
public interface VinylMapper {
	
	VinylMapper INSTANCE = Mappers.getMapper(VinylMapper.class);

	VinylDTO vinylToVinylDto(Vinyl vinyl);
	
	Vinyl vinylDtoToVinyl(VinylDTO vinylDTO);
	
	List<VinylDTO> vinylsToVinylDtos(List<Vinyl> vinyls);
	
	List<Vinyl> vinylDtosToVinyls(List<VinylDTO> vinylDtos);
}
