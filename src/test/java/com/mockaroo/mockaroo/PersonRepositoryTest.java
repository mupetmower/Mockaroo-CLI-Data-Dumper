package com.mockaroo.mockaroo;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mockaroo.MockarooDataRequesterApplication;
import com.mockaroo.entities.Person;
import com.mockaroo.repositories.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockarooDataRequesterApplication.class)
public class PersonRepositoryTest {

	private static Logger log = LoggerFactory.getLogger(PersonRepositoryTest.class);
	
	@Autowired
	private PersonRepository personRepository;

	@Test
	public void test() {

		log.info("Starting test");
		long beforeSave = personRepository.count();
		log.info("Before save size: {}", beforeSave);
		Person person = new Person("first", "last", null);
		personRepository.save(person);
		personRepository.flush();

		long afterSave = personRepository.count();
		log.info("After save size: {}", afterSave);
		assertThat(afterSave).isGreaterThan(beforeSave);
		assertThat(afterSave).isEqualTo(beforeSave + 1);

		log.info("Finished test");
	}

}
