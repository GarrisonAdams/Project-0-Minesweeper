package com.github.garrisonadams.minesweeper.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

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

	public boolean authenticateUser(String username, String password)

	{
		boolean authenticated = false;

		while(!authenticated)
		{
		try {
			connection = DatabaseConnector.getConnection();
			String sql = "SELECT * FROM MinesweeperGame where username=?"; // Our SQL query
			stmt = connection.prepareStatement(sql); // Creates the prepared statement from the query
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery(); // Queries the database
			
			if(rs.next())
			{
				System.out.println("Username is " + username);
				System.out.println("Please enter your password: ");
				System.out.println("Or enter \\q to exit the application");
				
				System.out.println(password);
				if(password.equals("\\q")) {
					System.out.println("Now exitting application");
					System.exit(0);
				}
				
				String sql2 = "SELECT * FROM UserDatabase where username=? AND password=?"; // Our SQL query
				stmt = connection.prepareStatement(sql2); // Creates the prepared statement from the query
				stmt.setString(1, username);
				stmt.setString(2, password);
				ResultSet rs2 = stmt.executeQuery(); // Queries the database
				if(rs2.next())
				{
					System.out.println("User authenticated");
					return true;
				}
				else {
					System.out.println("User not authenticated");
					return false;
				}
				
				
			}
			else
			{
				System.out.println("Username is not found. \n Please enter a password: ");
				newUser(username,password);
				return true;
			}
			
			} catch (SQLException e)
			{
				e.printStackTrace();
			} finally {
				closeResources();
			}
		}
		return false;
	}
	
	public void newUser(String username, String password)

	{
		try {
		connection = DatabaseConnector.getConnection();
		String sql = "INSERT INTO MinesweeperGame VALUES (?,0,0)"; // Our SQL query
		stmt = connection.prepareStatement(sql); // Creates the prepared statement from the query
		stmt.setString(1, username);
		stmt.executeUpdate(); // Queries the database
		
		String sql2 = "INSERT INTO UserDatabase VALUES (?,?)"; // Our SQL query
		stmt = connection.prepareStatement(sql2); // Creates the prepared statement from the query
		stmt.setString(1, username);
		stmt.setString(2, password);
		stmt.executeUpdate(); // Queries the database
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally {
			closeResources();
		}
	}

	public void incrementWin(String username) {
		try {
			int wins=0;
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
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources();
		}

	}

	public void printMinesweeperGame() {
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
	
	public void printUserAuthentication() {
		try {
			connection = DatabaseConnector.getConnection();
			String sql = "SELECT * FROM UserAuthentication"; // Our SQL query
			stmt = connection.prepareStatement(sql); // Creates the prepared statement from the query
			ResultSet rs = stmt.executeQuery(); // Queries the database

			while (rs.next()) {
				System.out.println("Username: " + rs.getString("username") + "  password: " + rs.getInt("password"));
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
			
			if(rs.next())
				System.out.println("Username: " + username + " Wins: " + rs.getInt("wins") + " Losses: " + rs.getInt("losses"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources();
		}
	}
	public static void main(String[] args) {

		Database db = Database.getInstance();
		db.printMinesweeperGame();
		db.printUserAuthentication();
		db.printUsernameStats("garrison");


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
