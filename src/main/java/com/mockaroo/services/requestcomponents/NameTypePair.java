package com.mockaroo.services.requestcomponents;

public class NameTypePair {
	
	private String name;
	private String type;
	
	public NameTypePair(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public NameTypePair() {
		
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

}
