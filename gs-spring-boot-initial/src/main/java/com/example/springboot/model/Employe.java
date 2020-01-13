package com.example.springboot.model;

import java.security.MessageDigest;
import java.util.Date;

public class Employe {
	
	private String email, mdp;
	private boolean newsletter;
	private Date inscription;
		
	public Employe() {}

	public Employe(String email, String mdp, boolean newsletter) throws Exception {
			
		this.email       = email;
		this.mdp         = this.hashPassword(mdp);
		this.newsletter  = newsletter;
		this.inscription = new Date();
	}
		
	public String hashPassword(String mdp) throws Exception {
			
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(mdp.getBytes());
			
		byte byteData[] = md.digest();
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
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
	
	public String toString() {
		return "Email : " + email + "\nMot de passe : " + mdp + "\nA newsletter : " + newsletter + "\nDate d'inscription : " + inscription;
	}
}
