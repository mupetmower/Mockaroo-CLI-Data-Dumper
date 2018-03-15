package com.interact911.mockaroo;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.interact911.services.DatabaseConnector;
import com.interact911.services.RestDataRequesterImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockarooDataRequesterApplicationTests {
	
//	@Value("${mockaroo_apikey:unknown}")
//	private String mockarooApiKey;
//	
//	@Value("${mockaroo_request_uri:unknown}")
//	private String requestUri;
//	
//	//@Value("${mockaroo_request_type:unknown}")
//	//private String requestType;
//	
//	
//	@Autowired
//    private RestDataRequesterImpl dataRequest;
//
//	@Autowired
//    private DatabaseConnector databaseConnector;

	@Test
	public void contextLoads() {
//		try {
//			
//			//JSONObject results = grabDataFromMockarooFields();
//	
//			//JSONArray data = results.getJSONArray("results");
//	
//			//insertDataIntoTable(data);
//			
//			
//			//System.exit(0);
//		} catch (Exception ex) {
//			System.out.println(ex.getMessage());
//			ex.printStackTrace();
//			//System.exit(1);
//		}
		
	}

	
	
	
//	public JSONObject grabDataFromMockarooFields() throws Exception {
//		dataRequest.initNewMockarooGenerator();
//		dataRequest.setApiAccessKey(mockarooApiKey);
//		dataRequest.setUriString(requestUri);
//		
//		dataRequest.addField("first", "First Name");
//		dataRequest.addField("last", "Last Name");
//		dataRequest.addField("city", "City");
//		dataRequest.addField("country", "Country");
//		
//		JSONObject results = dataRequest.generateCustomDataFromMockaroo(2);		
//		
//		return results;
//	}
//	
//	public void grabDataFromMockarooSchema(String schemaName) {
//		
//	}
//	
//	public void insertDataIntoTable(JSONArray data) throws SQLException, JSONException {
//		databaseConnector.insertData(data);
//	}
}
