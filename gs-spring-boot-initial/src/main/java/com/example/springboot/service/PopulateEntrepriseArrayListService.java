package com.example.springboot.service;

import java.util.ArrayList;

import com.example.springboot.model.Entreprise;

public class PopulateEntrepriseArrayListService {
	
	private static ArrayList<Entreprise> entreprises = new ArrayList<>();

	public static ArrayList<Entreprise> populate() {
		int nbSal = 50;
		
		for (int i = 0; i < 4; i++) {
			try {
				//entreprises.add(new Entreprise(i, "aa@bb.cc" + i, "abc" + i, "entreprise" + i, (i * nbSal) + 5, true));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			Entreprise e = entreprises.get(i);
			//e.setInscriptionToString(FormatDateToStringService.formatDate().format(e.getInscription()));
		}
		return entreprises;
	}
	
}
