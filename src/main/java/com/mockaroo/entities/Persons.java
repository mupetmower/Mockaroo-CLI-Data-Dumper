package com.mockaroo.entities;

public class Persons {
	public Person[] persons;
	
	@Override
	public String toString() {
		String s = "";
		for (Person p : persons)
			s += p.toString() + "\n";
		
		return s;
	}
}
