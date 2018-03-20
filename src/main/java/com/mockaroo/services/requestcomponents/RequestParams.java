package com.mockaroo.services.requestcomponents;

import java.util.List;

public class RequestParams {
	
	private String key;
	private Integer count;
	
	private List<NameTypePair> fields;
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getCount() {
		return count;
	}
	
	
	public void setFields(List<NameTypePair> fields) {
		this.fields = fields;
	}
	
	public List<NameTypePair> getFields() {
		return fields;
	}
	
}
