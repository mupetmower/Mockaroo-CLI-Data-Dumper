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
import org.springframework.test.context.junit4.SpringRunner;

import com.mockaroo.entities.Test_2;
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
	
	//@Value("${mockaroo_request_type:unknown}")
	//private String requestType;
	
	
	@Autowired
    private RestDataRequesterImpl dataRequest;

	@Autowired
    private DatabaseConnector databaseConnector;	
	
	
	@Test
	public void contextLoads() {
		try {
			int records = 4;
			
			JSONObject results = grabDataFromMockarooFields(records);
			System.out.println("Grabbed Data from Mockaroo!");
			
			JSONArray data = results.getJSONArray("results");
			
			assertThat(data.length()).isEqualTo(records);
			
			insertDataIntoTable(data);
			System.out.println("Inserted Data into DB!");
			
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
	
	public void grabDataFromMockarooSchema(String schemaName) {
		
		
	}
	
	public void insertDataIntoTable(JSONArray data) throws SQLException, JSONException {
		databaseConnector.insertData(data);
		
	}

	

}
