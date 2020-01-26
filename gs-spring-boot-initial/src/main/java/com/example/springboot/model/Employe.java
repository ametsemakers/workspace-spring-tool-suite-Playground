package com.example.springboot.model;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Employe {
	
	@Id
	private long id;
	@NotEmpty(message= "Le nom est obligatoire")
	private String nom;
	private Date dateEntree;
	
	@ManyToOne
	private Entreprise entreprise;
	
	@Embedded
	private Adresse adresse;
		
	public Employe() {}

//	public Employe(String nom, int entreprise) {
//			
//		this.id = ++counter;
//		this.nom = nom;
//		this.dateEntree = new Date();
//		this.entreprise = entreprise;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
}
