package com.mockaroo.bootstrap;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mockaroo.entities.Test_2;
import com.mockaroo.repositories.Test2Repository;

@Component
public class Test2DataLoader implements CommandLineRunner{

	@Autowired
	private Test2Repository test2Repository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		test2Repository.save(new Test_2("TestFirst", "TestLast", "TestCity", "TestCountry"));
	}
}
