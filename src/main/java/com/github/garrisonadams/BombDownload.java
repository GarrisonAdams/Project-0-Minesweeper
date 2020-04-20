package com.github.garrisonadams;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BombDownload 
{
	
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;

	public void bombDownloading(Minesweeper game)
	{
		try 
		{
			connection = DatabaseConnector.getConnection();
			String sql = "SELECT * FROM Game1";
			stmt = connection.prepareStatement(sql);
						
			ResultSet rs = stmt.executeQuery();

			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
		{
			closeResources();
		}
	}
	
	//-------------------------------------
	
	// Closing all resources is important, to prevent memory leaks. 
	// Ideally, you really want to close them in the reverse-order you open them
	private void closeResources()
	{
		try 
		{
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) 
		{
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try 
		{
			if (connection != null)
				connection.close();
		} catch (SQLException e) 
		{
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

}
