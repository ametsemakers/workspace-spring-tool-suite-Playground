package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import model.Acteur;

public interface ActeurRepository extends CrudRepository<Acteur, Integer> {

	List<Acteur> findByPrenom(String prenom);
}
