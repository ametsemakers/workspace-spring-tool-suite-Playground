package com.example.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.annotation.Secured;

import com.example.springboot.model.Entreprise;

public interface EntrepriseRepository extends CrudRepository<Entreprise, Long> {
	
	List<Entreprise> findByRaisonSocial(String raisonSocial);
}
