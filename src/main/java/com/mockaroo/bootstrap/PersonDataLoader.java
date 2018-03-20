package com.mockaroo.bootstrap;



import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;

import org.h2.value.ValueDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mockaroo.entities.Person;
import com.mockaroo.repositories.PersonRepository;

@Component
public class PersonDataLoader implements CommandLineRunner {
	
	@Autowired
	private PersonRepository personRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		personRepository.save(new Person("Jack", "Frost", Date.valueOf("1960-12-24")));
		personRepository.save(new Person("Jack", "The Ripper", Date.valueOf("1985-4-5")));
		personRepository.save(new Person("Test", "Test'Last", Date.valueOf(LocalDate.of(1985, 4, 5))));
		personRepository.save(new Person("Test2", "Test'Last2", new Date(12, 2, 1991)));
	}

}
