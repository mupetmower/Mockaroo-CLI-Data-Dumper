package com.mockaroo.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.h2.value.ValueDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    
	public String first_name;
	
	public String last_name;
    
    @Column(nullable = true)
    @JsonInclude(content=Include.NON_NULL)
    public Date date_of_birth;
    
    
    public Person() {}
    
    public Person(String first_name, String last_name, Date date_of_birth) {
    	this.first_name = first_name;
    	this.last_name = last_name;
    	this.setDate_of_birth(date_of_birth);
    }
    
    @Override
    public String toString() {    	
    	return String.format("Person Id: %d Name: %s %s DOB: %s",
    			id, first_name, last_name, date_of_birth.toString());
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
	
//	public void setDate_of_birth(String date_of_birth) {
//		this.date_of_birth = Date.valueOf(date_of_birth);
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
    	
}
