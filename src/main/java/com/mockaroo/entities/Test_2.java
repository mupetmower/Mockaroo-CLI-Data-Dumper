package com.mockaroo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Test_2 {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String first_name;
    private String last_name;
    private String city;
    private String country;
    
    
    protected Test_2() {}
    
    public Test_2(String first_name, String last_name, String city, String country) {
    	this.first_name = first_name;
    	this.last_name = last_name;
    	this.city = city;
    	this.country = country;
    }
    
    
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
    	
}
