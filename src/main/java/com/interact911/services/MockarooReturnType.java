package com.interact911.services;

public enum MockarooReturnType {
	JSON,
	CSV,
	TXT,
	CUSTOM,
	SQL,
	XML;
	
	@Override
	public String toString() {
		switch(this) {
			case JSON:
				return "json";
			case CSV:
				return "csv";
			case TXT:
				return "txt";
			case SQL:
				return "sql";
			case XML:
				return "xml";
			default:
				return "json";
			}		
	}
}
