package com.interact911.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RestDataRequesterImpl implements RestDataRequester {
	
	private String defaultKey = "c61a4ed0";	
	private String defaultUri = "https://api.mockaroo.com/api/generate.json";
	private MockarooReturnType defaultType = MockarooReturnType.JSON;
	
	MockarooConnector mockarooConnector = new MockarooConnector();
	
	
	public String requestDataFromMockarooSchema(String schemaName) {
		String response = "";
		
		return response;
	}
	
	public JSONObject generateCustomDataFromMockaroo(int recordCount) throws Exception {				
		mockarooConnector.setRecordCount(recordCount);
		
		JSONObject results = mockarooConnector.requestMockarooData();
		
		return results;
	}
	
	public void initNewMockarooGenerator() {
		mockarooConnector = new MockarooConnector();	
		mockarooConnector.setMockarooApiUriString(defaultUri);
		mockarooConnector.setMockarooApiKey(defaultKey);
		mockarooConnector.setReturnType(defaultType);
	}	
	
	public void setApiAccessKey(String key) {
		mockarooConnector.setMockarooApiKey(key);
	}	
	
	public void setUriString(String uri) {
		mockarooConnector.setMockarooApiUriString(uri);
	}
	
	public void setReturnType(MockarooReturnType type) {
		mockarooConnector.setReturnType(type);
	}
	
	public void addParamValue(String key, Object value) {
		mockarooConnector.addParamToParameterMap(key, value);
	}
	
	public void addField(String name, String type) throws JSONException {
		mockarooConnector.addField(name, type);
	}
	
	
}
