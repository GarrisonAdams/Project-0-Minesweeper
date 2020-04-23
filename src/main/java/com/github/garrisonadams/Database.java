package com.github.garrisonadams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	private static Database instance;

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}
	Connection connection = null; // Our connection to the database
	PreparedStatement stmt = null; // We use prepared statements to help protect against SQL injection

	public void newUser(String username)
	{
		try {
		connection = DatabaseConnector.getConnection();
		String sql = "INSERT INTO MinesweeperGame VALUES (?,0,0)"; // Our SQL query
		stmt = connection.prepareStatement(sql); // Creates the prepared statement from the query
		stmt.setString(1, username);
		stmt.executeUpdate(); // Queries the database
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally {
			closeResources();
		}
	}
	
	public void printDatabase() {
		try {
			connection = DatabaseConnector.getConnection();
			String sql = "SELECT * FROM MinesweeperGame"; // Our SQL query
			stmt = connection.prepareStatement(sql); // Creates the prepared statement from the query
			ResultSet rs = stmt.executeQuery(); // Queries the database

			while (rs.next()) {
				System.out.println("Username: " + rs.getString("username") + "  wins: " + rs.getInt("wins") + "  losses: "
						+ rs.getInt("losses"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources();
		}

	}
	
	public void printUsernameStats(String username) {
		try {
			connection = DatabaseConnector.getConnection();
			String sql = "SELECT wins,losses FROM MinesweeperGame WHERE username=?"; // Our SQL query
			stmt = connection.prepareStatement(sql); // Creates the prepared statement from the query
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery(); // Queries the database

			System.out.println("Username: " + username + " Wins: " + rs.getInt("wins") + " Losses: " + rs.getInt("losses"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources();
		}

	}

	public void incrementWin(String username) {
		try {
			int wins = 0;
			connection = DatabaseConnector.getConnection();
			String sql = "SELECT * FROM MinesweeperGame where username=username"; // Our SQL query
			stmt = connection.prepareStatement(sql); // Creates the prepared statement from the query
			ResultSet rs = stmt.executeQuery(); // Queries the database
			if(rs.next())
				 wins = rs.getInt("wins");

			String sql2 = "UPDATE MinesweeperGame SET wins=? WHERE username=?";
			stmt = connection.prepareStatement(sql2);
			stmt.setInt(1, wins + 1);
			stmt.setString(2, username);
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources();
		}

	}

	public void incrementLoss(String username) {
		try {
			int losses = 0;
			connection = DatabaseConnector.getConnection();
			String sql = "SELECT losses FROM MinesweeperGame where username=username"; // Our SQL query
			stmt = connection.prepareStatement(sql); // Creates the prepared statement from the query
			ResultSet rs = stmt.executeQuery(); // Queries the database

			if(rs.next())
				losses = rs.getInt("losses");

			String sql2 = "UPDATE MinesweeperGame SET losses=? WHERE username=?";
			stmt = connection.prepareStatement(sql2);
			stmt.setInt(1, losses + 1);
			stmt.setString(2, username);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources();
		}

	}

	public static void main(String[] args) {
		Database db = new Database();
		db.printDatabase();
		db.newUser("Tracey");
		//db.incrementWin("Garrison");
		db.printDatabase();

	}

	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}

		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
}
