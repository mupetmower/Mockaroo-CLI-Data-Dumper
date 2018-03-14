package com.interact911.services;

import java.net.URI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.SerializationFeature;

class MockarooConnector {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private String mockarooApiUriStringWithType;
	
	private String mockarooApiUriString;
	private String mockarooApiKey;
	private int recordCount = 1;
	
	private MultiValueMap<String, Object> parameterMap = new LinkedMultiValueMap<String, Object>();
	private JSONArray fields = new JSONArray();
	
	private MockarooReturnType returnType = MockarooReturnType.JSON;
	
	public MockarooConnector() {}
	
	public MockarooConnector(String apiUri, String apiKey) {
		mockarooApiUriString = apiUri;
		mockarooApiKey = apiKey;
		
	}
	
	
	public JSONObject requestMockarooData() throws Exception {
		//JSONObject data = new JSONObject();
		
		mockarooApiUriStringWithType = mockarooApiUriString + returnType.toString();
		
		constructParameterMap();
		
		String dataString = restTemplate.postForObject(mockarooApiUriStringWithType, parameterMap, String.class);
		JSONObject results = new JSONObject( "{ results: " + dataString + " }");
		
		return results;
	}
	
	
	public void initRestTemplate() {
		restTemplate = new RestTemplate();
		
	}
	
	public void initParameterMap() {
		parameterMap.add("key", mockarooApiKey);
		parameterMap.add("count", recordCount);
	}
	
	private void initParameterMap(String apiKey, String recordCount) {
		parameterMap.add("key", apiKey);
		parameterMap.add("count", recordCount);
	}
	
	
	private void constructParameterMap() throws Exception {
		if (mockarooApiUriString.equals("") || mockarooApiUriString.isEmpty() || mockarooApiUriString == null) {
			throw new Exception("Mockaroo Api String is empty.");
		}
		if (mockarooApiKey.equals("") || mockarooApiKey.isEmpty() || mockarooApiKey == null) {
			throw new Exception("Mockaroo Api Key is empty.");
		}
		
		parameterMap.add("key", mockarooApiKey);
		parameterMap.add("count", recordCount);
		
		if (!fields.isNull(0) || fields.length() != 0) {
			parameterMap.add("fields", fields.toString());
		}
						
	}
	
	

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String geMockarooApitKey() {
		return mockarooApiKey;
	}

	public void setMockarooApiKey(String mockarooApiKey) {
		this.mockarooApiKey = mockarooApiKey;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) throws Exception {
		if (recordCount < 1) {
			throw new Exception("Record Count must be higher than 1 (its default).");
		}
		this.recordCount = recordCount;
	}

	public String getMockarooApiUri() {
		return mockarooApiUriString;
	}

	public void setMockarooApiUriString(String mockarooApiUriString) {
		this.mockarooApiUriString = mockarooApiUriString;
	}

	public JSONArray getFields() {
		return fields;
	}

	public void setFields(JSONArray fields) {
		this.fields = fields;
	}
	
	public void addField(String name, String type) throws JSONException {
		JSONObject field = new JSONObject();
		field.put("name", name);
		field.put("type", type);
		fields.put(field);
	}
	
	public void addField(JSONObject field) {
		fields.put(field);
	}

	public MultiValueMap<String, Object> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(MultiValueMap<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}
	
	public void addParamToParameterMap(String key, Object value) {
		parameterMap.add(key, value);
	}

	public MockarooReturnType getReturnType() {
		return returnType;
	}

	public void setReturnType(MockarooReturnType returnType) {
		this.returnType = returnType;
	}
	

}
