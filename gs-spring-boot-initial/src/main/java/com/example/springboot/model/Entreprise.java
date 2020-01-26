package com.example.springboot.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

//import com.example.springboot.service.PasswordHashService;

@Entity
@Table(name="entreprise")
public class Entreprise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="nb_salaries")
	private int nbrSalaries;
	
	@Column(name="raison_soc", length = 30, nullable = false)
	@NotEmpty(message= "La raison sociale est obligatoire")
	private String raisonSocial;
	
	@Column(name="email", length = 30, nullable = false)
	@NotEmpty(message= "L'email est obligatoire")
	private String email;
	
	@Column(name="mot_de_passe", length = 100, nullable = false)
	@NotEmpty(message= "Le mot de passe est obligatoire")
	private String mdp;
	
	@Column(name="newsletter")
	private boolean newsletter;
	
	@Column(name="date_inscr", nullable = true)
	private Date inscription;
	
	
	//private String inscriptionToString;
	
	@OneToMany(mappedBy="entreprise")
	private List<Employe> employees;
	
	public Entreprise() {}

//	public Entreprise(int id, String email, String mdp, String raisonSocial, int nbrSalaries, boolean newsletter) throws Exception {
//		
//		this.id 		  = id;
//		this.email		  = email;
//		this.mdp 		  = PasswordHashService.hashPassword(mdp);
//		this.raisonSocial = raisonSocial;
//		this.nbrSalaries  = nbrSalaries;
//		this.inscription  = new Date();
//		this.newsletter   = newsletter;
//	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getRaisonSocial() {
		return raisonSocial;
	}
	
	public void setRaisonSocial(String raisonSocial) {
		this.raisonSocial = raisonSocial;
	}
	
	public int getNbrSalaries() {
		return nbrSalaries;
	}
	
	public void setNbrSalaries(int nbrSalaries) {
		this.nbrSalaries = nbrSalaries;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public boolean isNewsletter() {
		return newsletter;
	}

	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}

	public Date getInscription() {
		return inscription;
	}

	public void setInscription(Date inscription) {
		this.inscription = inscription;
	}

//	public String getInscriptionToString() {
//		return inscriptionToString;
//	}
//
//	public void setInscriptionToString(String inscriptionToString) {
//		this.inscriptionToString = inscriptionToString;
//	}
//	
//	public String toString() {
//		return "Raison social : " + raisonSocial + "\nNombre de salariés : " + nbrSalaries;
//	}
//
//	public ArrayList<Employe> getEmployees() {
//		return employees;
//	}
//
//	public void setEmployees(ArrayList<Employe> employees) {
//		this.employees = employees;
//	}
}
