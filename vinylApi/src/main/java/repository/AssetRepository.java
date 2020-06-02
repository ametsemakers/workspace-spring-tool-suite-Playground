package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, String> {

	Asset findByContentAndMimeType(byte[] content, String mimeType);
}
