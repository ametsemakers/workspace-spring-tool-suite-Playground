package com.example.springboot.model;

import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class Adresse {

	@Id
	private long id;
	
	private String street;
	
	private int postalCode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
}
