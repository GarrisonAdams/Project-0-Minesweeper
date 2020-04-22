package com.github.garrisonadams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

	Connection connection = null; // Our connection to the database
	PreparedStatement stmt = null; // We use prepared statements to help protect against SQL injection

	public void printDatabase() {
		try {
			connection = DatabaseConnector.getConnection();
			String sql = "SELECT * FROM MinesweeperGame"; // Our SQL query
			stmt = connection.prepareStatement(sql); // Creates the prepared statement from the query
			ResultSet rs = stmt.executeQuery(); // Queries the database

			while (rs.next()) {
				System.out.println("Username: " + rs.getString("username") + "wins: " + rs.getInt("wins") + "losses: "
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
			connection = DatabaseConnector.getConnection();
			String sql = "SELECT wins FROM MinesweeperGame where username=?"; // Our SQL query
			stmt = connection.prepareStatement(sql); // Creates the prepared statement from the query
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery(); // Queries the database

			int wins = rs.getInt("wins");

			String sql2 = "UPDATE MinesweeperGame SET wins=? WHERE username=?";
			stmt = connection.prepareStatement(sql2);
			stmt.setInt(1, wins + 1);
			stmt.setString(2, username);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources();
		}

	}

	public void incrementLoss(String username) {
		try {
			connection = DatabaseConnector.getConnection();
			String sql = "SELECT losses FROM MinesweeperGame where username=?"; // Our SQL query
			stmt = connection.prepareStatement(sql); // Creates the prepared statement from the query
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery(); // Queries the database

			int losses = rs.getInt("losses");

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
