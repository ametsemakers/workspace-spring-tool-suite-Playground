package service;

import java.io.ByteArrayInputStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.AssetDTO;
import model.Asset;
import repository.AssetRepository;

@Service
@Transactional
public class AssetService {
	
	@Autowired
	private AssetRepository assetRepository;

	public AssetDTO getAsset(String id) {
		Asset asset = assetRepository.findById(id).orElse(null);
		if (asset == null) {
			return null;
		}
		if (!asset.isPublicAsset()) {
			return null;
		}
		AssetDTO assetDto = new AssetDTO();
		assetDto.setContentType(asset.getMimeType());
		assetDto.setInputStream(new ByteArrayInputStream(asset.getContent()));
		assetDto.setLength(asset.getContent().length);
		assetDto.setContent(asset.getContent());
		
		return assetDto;
	}
	
	public Asset saveAsset(Asset asset) {
		return this.assetRepository.save(asset);
	}
	
	public void deleteAsset(Asset asset) {
		this.assetRepository.delete(asset);;
	}

}
