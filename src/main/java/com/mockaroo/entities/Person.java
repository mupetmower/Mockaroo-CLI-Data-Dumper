package com.mockaroo.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.h2.value.ValueDate;

@Entity
public class Person {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String first_name;
    private String last_name;
    private Date date_of_birth;
    
    
    protected Person() {}
    
    public Person(String first_name, String last_name, Date date_of_birth) {
    	this.first_name = first_name;
    	this.last_name = last_name;
    	this.setDate_of_birth(date_of_birth);
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

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	
    	
}
