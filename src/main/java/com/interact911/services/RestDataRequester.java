package com.interact911.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


public interface RestDataRequester {
	
	public JSONObject generateCustomDataFromMockaroo(int recordCount) throws Exception;
	
	public String requestDataFromMockarooSchema(String schemaName);
	

}
