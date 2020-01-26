package repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import model.Image;

public interface ImageRepository extends CrudRepository<Image, Integer> {

	List<Image> findAll(Pageable pageable);
	
	List<Image> findByPath(String path);
}
