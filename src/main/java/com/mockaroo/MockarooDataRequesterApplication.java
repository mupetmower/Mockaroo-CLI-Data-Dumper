package com.mockaroo;



import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mockaroo.entities.Person;
import com.mockaroo.entities.Persons;
import com.mockaroo.repositories.PersonRepository;
import com.mockaroo.services.DatabaseConnector;
import com.mockaroo.services.RestDataRequesterImpl;

import liquibase.Liquibase;
import liquibase.integration.spring.SpringLiquibase;

@SpringBootApplication
@ComponentScan(basePackages = { "com.mockaroo.*" })
public class MockarooDataRequesterApplication implements CommandLineRunner  {
	
	public static void main(String[] args) {
		//disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(MockarooDataRequesterApplication.class);
        app.setBannerMode(Mode.OFF);
        app.run(args);
	}
	
	@Override
	public void run(String... args) {
		try {
			int records = 4;
			
//			JSONObject results = grabDataFromMockarooFields(records);
//			System.out.println("Grabbed Custom Data from Mockaroo!");
//			
//			JSONArray data = results.getJSONArray("results");	
//			
//			insertDataIntoTable(data);
//			System.out.println("Inserted Data into DB!");			
			
			
			System.out.println("Sending schema request. Schema: person");
			JSONObject schemaResults = grabDataFromMockarooSchema("person");
			
			JSONArray schemaData = schemaResults.getJSONArray("results");
			System.out.println("Got schema data");
			
			
			System.out.println(schemaData.toString());
			
			String json = "{\"persons\": " + schemaData.toString() + "}";
			
			ObjectMapper mapper = new ObjectMapper();
			
			//System.out.println(mapper.readValue(json, Persons.class).toString());
			
			
			convertAndInsertAll(schemaData, Person.class, personRepository);
			
			
			
			//System.exit(0);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			System.exit(1);
		}
	}
	
	
	
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
	
	public <T> void convertAndInsertAll(JSONArray fromArray, Class<T> toClassType, JpaRepository<T, Integer> repo) throws JSONException, IOException {
		for (int i = 0; i < fromArray.length(); i++) {
			System.out.println(fromArray.getJSONObject(i).toString());
			T obj = convert(fromArray.getJSONObject(i), toClassType);
			//System.out.println(obj.toString());
			repo.save(obj);
		}
		
	}
	
	public <T> T convert(JSONObject fromObj, Class<T> toClassType) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(fromObj.toString(), toClassType);
	}
	
	public void insertDataIntoTable(JSONArray data) throws SQLException, JSONException {
		databaseConnector.insertData(data);
		
	}
	
}
