package com.example.springboot.model;

public class Saison {

	private String name;
	private boolean current;
	
	public Saison() {}
	
	public Saison(String name, boolean current) {
		
		this.setName(name);
		this.setCurrent(current);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}
}
