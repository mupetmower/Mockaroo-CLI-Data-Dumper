package com.interact911.services.requestcomponents;

import java.util.List;
import java.util.Map;

public class RequestField {
	
	List<NameTypePair> fields;
	
	
	public void setFields(List<NameTypePair> fields) {
		this.fields = fields;
	}
	
	public List<NameTypePair> getFields() {
		return fields;
	}
	
	public void addToFields(NameTypePair pair) {
		fields.add(pair);
	}
}
