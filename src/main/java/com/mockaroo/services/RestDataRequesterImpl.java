package com.mockaroo.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RestDataRequesterImpl implements RestDataRequester {
	
	private String defaultKey = "c61a4ed0";	
	private String defaultUri = "https://api.mockaroo.com/api/generate.json";
	private String defaultSchemaUri = "https://my.api.mockaroo.com/";
	private MockarooReturnType defaultType = MockarooReturnType.JSON;
	
	MockarooConnector mockarooConnector = new MockarooConnector();
	
	
	public JSONObject requestDataFromMockarooSchema(String schemaName) throws Exception {
		
		
		JSONObject results = mockarooConnector.requestSchema(schemaName);
		
		return results;
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
		mockarooConnector.setSchemaRequestUri(defaultSchemaUri);
	}	
	
	public void setApiAccessKey(String key) {
		mockarooConnector.setMockarooApiKey(key);
	}	
	
	public void setUriString(String uri) {
		mockarooConnector.setMockarooApiUriString(uri);
	}
	
	public void setSchemaUri(String uri) {
		mockarooConnector.setSchemaRequestUri(uri);
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
