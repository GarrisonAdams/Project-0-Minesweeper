package com.github.garrisonadams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector 
{
	private static final String CONNECTION_USERNAME = "pequoxwk";
	private static final String CONNECTION_PASSWORD = "hTLzPJ8qAJ_riIupzMXO4opdb8kmQTfV";
	private static final String URL = "jdbc:postgresql://drona.db.elephantsql.com:5432/pequoxwk";
	private static Connection connection;
	
	
	public static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
				System.out.println("Here 1");

			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			System.out.println("Here 2");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);

		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			System.out.println("Here 3");
			System.out.println("Opening new connection...");
				connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}
	
	
}
