package com.github.garrisonadams.minesweeper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector 
{
	
	private static final String CONNECTION_USERNAME = "user";
	private static final String CONNECTION_PASSWORD = "password";
	private static final String URL = "jdbc:postgresql://18.216.144.66:5432/user";
	private static Connection connection;
	
	
	public static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");

			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);

		}
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
				connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}
	

	
	
}
