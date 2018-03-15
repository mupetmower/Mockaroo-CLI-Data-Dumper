package com.interact911.mockaroo;

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

import com.interact911.services.DatabaseConnector;
import com.interact911.services.RestDataRequesterImpl;

@SpringBootApplication
@ComponentScan(basePackages = { "com.interact911.*"})
public class MockarooDataRequesterApplication implements CommandLineRunner  {
	
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
	
	

	public static void main(String[] args) {
		//disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(MockarooDataRequesterApplication.class);
        app.setBannerMode(Mode.OFF);
        app.run(args);
	}
	
	@Override
	public void run(String... args) {
		try {
			
			JSONObject results = grabDataFromMockarooFields();
			System.out.println("Grabbed Data from Mockaroo!");
			
			JSONArray data = results.getJSONArray("results");
	
			insertDataIntoTable(data);
			System.out.println("Inserted Data into DB!");
			
			//System.exit(0);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			//System.exit(1);
		}
	}
	
	
	
	public JSONObject grabDataFromMockarooFields() throws Exception {
		dataRequest.initNewMockarooGenerator();
		dataRequest.setApiAccessKey(mockarooApiKey);
		dataRequest.setUriString(requestUri);
		
		dataRequest.addField("first", "First Name");
		dataRequest.addField("last", "Last Name");
		dataRequest.addField("city", "City");
		dataRequest.addField("country", "Country");
		
		JSONObject results = dataRequest.generateCustomDataFromMockaroo(2);		
		
		return results;
	}
	
	public void grabDataFromMockarooSchema(String schemaName) {
		
	}
	
	public void insertDataIntoTable(JSONArray data) throws SQLException, JSONException {
		databaseConnector.insertData(data);
		
	}
	
}
