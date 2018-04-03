package com.mockaroo.mockaroo;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mockaroo.entities.Person;
import com.mockaroo.entities.Test_2;
import com.mockaroo.repositories.PersonRepository;
import com.mockaroo.services.DatabaseConnector;
import com.mockaroo.services.RestDataRequesterImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class MockarooDataRequesterApplicationTests {
	
	@Value("${mockaroo_apikey:unknown}")
	private String mockarooApiKey;
	
	@Value("${mockaroo_request_uri:unknown}")
	private String requestUri;
	
	@Value("${mockaroo_person_schema_request_url:unknown}")
	private String personSchemaRequestUri;
	
	@Value("${mockaroo_schema_request_url:unknown}")
	private String schemaRequestUri;
	
	//@Value("${mockaroo_request_type:unknown}")
	//private String requestType;
	
	
	@Autowired
    private RestDataRequesterImpl dataRequest;

	@Autowired
    private DatabaseConnector databaseConnector;
	
	@Autowired
	private PersonRepository personRepository;
	
	
	
	@Test
	public void mockarooDataRequesting() {
		try {
			int records = 4;
			
			JSONObject results = grabDataFromMockarooFields(records);
			System.out.println("Grabbed Custom Data from Mockaroo!");
			
			JSONArray data = results.getJSONArray("results");
			
			assertThat(data.length()).isEqualTo(records);
			
			insertDataIntoTable(data);
			System.out.println("Inserted Data into DB!");
			
			//AssertThat table has correct data..
			
			
			
			System.out.println("Sending schema request. Schema: person");
			JSONObject schemaResults = grabDataFromMockarooSchema("person");
			
			JSONArray schemaData = schemaResults.getJSONArray("results");
			System.out.println("Got schema data");
			
			assertThat(schemaData.length()).isEqualTo(1000);
			
			//System.out.println(schemaData.toString());
			
			convertAndInsertAll(schemaData, Person.class, personRepository);
			
			assertThat(personRepository.count()).isGreaterThanOrEqualTo(schemaData.length());
			
			
			//System.exit(0);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			//System.exit(1);
		}
				
	}
	
	
	public JSONObject grabDataFromMockarooFields(int records) throws Exception {
		dataRequest.initNewMockarooGenerator();
		dataRequest.setApiAccessKey(mockarooApiKey);
		dataRequest.setUriString(requestUri);
		
		dataRequest.addField("first", "First Name");
		dataRequest.addField("last", "Last Name");
		dataRequest.addField("city", "City");
		dataRequest.addField("country", "Country");
		
		JSONObject results = dataRequest.generateCustomDataFromMockaroo(records);		
		
		return results;
	}
	
	public JSONObject grabDataFromMockarooSchema(String schemaName) throws Exception {
		dataRequest.initNewMockarooGenerator();
		dataRequest.setSchemaUri(schemaRequestUri);
		dataRequest.setApiAccessKey(mockarooApiKey);
				
		JSONObject results = dataRequest.requestDataFromMockarooSchema(schemaName);		
		
		return results;		
	}
	
	public <T> void convertAndInsertAll(JSONArray fromArray, Class<T> toClassType, JpaRepository<T, Integer> repo) throws JSONException {
		for (int i = 0; i < fromArray.length(); i++) {
			T obj = convert(fromArray.getJSONObject(i), toClassType);
			repo.save(obj);
		}
		
	}
	
	public <T> T convert(JSONObject fromObj, Class<T> toClassType) {
		ObjectMapper om = new ObjectMapper();
		om.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		return om.convertValue(fromObj, toClassType);
	}
	
	public void insertDataIntoTable(JSONArray data) throws SQLException, JSONException {
		databaseConnector.insertData(data);
		
	}
	
	

	

}
