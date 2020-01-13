package com.example.springboot.model;

public class Entreprise {
	
	private String raisonSocial;
	private int nbrSalaries;
	
	public Entreprise() {}

	public Entreprise(String raisonSocial, int nbrSalaries) {
		
		this.raisonSocial = raisonSocial;
		this.nbrSalaries  = nbrSalaries;
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
	
	public String toString() {
		return "Raison social : " + raisonSocial + "\nNombre de salariés : " + nbrSalaries;
	}
}