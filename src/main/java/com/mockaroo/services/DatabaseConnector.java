package com.mockaroo.services;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DatabaseConnector {

	@Value("${database_ip:unknown}")
	private String ipaddress;
	
	@Value("${database_port:unknown}")
	private String port;
	
	@Value("${database_username:unknown}")
	private String username;
	
	@Value("${database_password:unknown}")
	private String password;
	
	@Value("${database_name:unknown}")
	private String databaseName;
	
	@Value("${database_table:unknown}")
	private String databaseTable;
	
	Connection connection;
	
	List<String> columnNames;
	
	
	public DatabaseConnector() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Driver d = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();  
		String connectionUrl = initConnString();
		
		connection = d.connect(connectionUrl, new Properties());
		
	}
	
	
//	public boolean insertData(String data) {
//		
//		return true;
//	}
	
	
	public boolean insertData(JSONArray data) throws SQLException, JSONException {
		if (data == null || data.length() <= 0) {
			return false;
		}		
				
		columnNames = getColumnNames();
		String prepareString = "INSERT INTO " + databaseTable + " (";
		for (int i = 0; i < columnNames.size(); i++) {
			prepareString += columnNames.get(i);
			if (i < columnNames.size()-1) {
				prepareString += ", ";				
			}
		}
		prepareString += ") VALUES (";
		for (int i = 0; i < columnNames.size(); i++) {
			prepareString += " ? ";
			if (i < columnNames.size()-1) {
				prepareString += ", ";				
			}
		}
		
		prepareString += ");";
		
		
		PreparedStatement pstmt = connection.prepareStatement(prepareString);		
		
		for (int i = 0; i < data.length(); i++) {			
			for (int j = 0; j < columnNames.size(); j++) {
				String name = columnNames.get(j);
				
				if (data.getJSONObject(i).get(name) != null) {
					pstmt.setObject(j + 1, data.getJSONObject(i).get(name));
					
				} else {
					pstmt.setObject(j + 1, " ");
				}				
			}		
			
			pstmt.addBatch();
		}
		
		pstmt.executeBatch();
		connection.commit();
		
		System.out.println("Data Committed");
		
		return true;
	}
		
	
	private String initConnString() {
		String connectionUrl = "jdbc:sqlserver://localhost:1433;database=MockarooTestDB;user=sa;password=1nt3rACT;";
		
		//connectionUrl = "jdbc:sqlserver://" + ipaddress + ":" + port + ";database=" + databaseName + ";user=" + username + ";password=" + password + ";";
		
		return connectionUrl;
	}
		
	
	public List<String> getColumnNames() throws SQLException {
		List<String> names = new ArrayList<String>();
		
		ResultSet result = connection.createStatement().executeQuery("SELECT TOP 1 * FROM " + databaseTable);
		
		//if (result.first()) {
			ResultSetMetaData rsmd = result.getMetaData();
			int cols = rsmd.getColumnCount();
		
			for (int i = 2; i <= cols; i++) {
				//if (!rsmd.getColumnName(i).equals("id"))
					names.add(rsmd.getColumnName(i));
			}
		//}
		
		return names;
	}
	
}
