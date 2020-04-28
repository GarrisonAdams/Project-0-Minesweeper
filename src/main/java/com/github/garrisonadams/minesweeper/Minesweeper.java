/**
 * The Minesweeper contains the methods needed to run the Minesweeper game
 * 
 * Minesweeper(): The constructor initializes all the Tile grid, plants 10 mines throughout the Tile grid, 
 * 		and updates the Tile grid to contain the number of adjacent bombs each tile has.
 * mineInit(): selects a random Tile in the grid. If it is a mine, it does nothing. If it is not a mine, then the Tile becomes one.
 * 		There are 10 mines total. Called in Minesweeper().
 * calculateAdjacentMines(int row, int column): calculates the number of mines adjacent to a Tile, and assigns that value to the Tile's adjacentMines variable
 * 		Does this for every Tile. Called in Minesweeper().
 * display(): This method prints out the Minesweeper grid.
 * hasWon(): This method is called after every command is executed to determine if the user has won. 
 * 		A Minesweeper game is won when every Tile that is not a mine is uncovered. This method returns a boolean value
 * win(): This method is called after it has been confirmed that the user has won a game
 * 		This method uncovers all of the tiles, calls the display() method, and prints a congratulatory message
 * lose(): This method is called when hasLost, which has a default value of false, is assigned a value of true.
 * 		This only happens when the user selects a Tile with a mine.
 * 		This method uncovers all of the tiles, calls the display() method, and tells the user they lost

 */

package com.github.garrisonadams.minesweeper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.github.garrisonadams.minesweeper.database.Database;
import com.github.garrisonadams.minesweeper.io.InputHandler;

public class Minesweeper {

	protected boolean isPlaying = true;
	protected boolean hasLost = false;
	Database database = Database.getInstance();

	protected Tile[][] grid = new Tile[8][8];

	public Minesweeper() {

		// Initializes the tiles of the Minesweeper grid
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Tile(i, j);
			}
		}

		//mineInit();
		grid[3][0].setMine(true);
		grid[3][1].setMine(true);
		grid[3][2].setMine(true);
		grid[0][4].setMine(true);
		grid[1][4].setMine(true);
		grid[2][4].setMine(true);
		grid[5][4].setMine(true);
		grid[6][4].setMine(true);
		grid[7][4].setMine(true);
		grid[5][1].setMine(true);


		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				calculateAdjacentMines(i, j);
			}
		}
	}

	protected void mineInit() {
		int numOfMines = 0;

		while (numOfMines < 10) {
			int row = (int) (Math.random() * 8);
			int column = (int) (Math.random() * 8);

			if (!grid[row][column].isMine()) {
				grid[row][column].setMine(true);
				numOfMines++;
			}
		}
	}

	protected void calculateAdjacentMines(int row, int column) {

		int numOfMines = 0;

		for (int i = row - 1; i < row + 2; i++) {
			for (int j = column - 1; j < column + 2; j++) {
				if (i == row && j == column) {
					continue;
				}

				if ((i >= 0 && i < 8) && (j >= 0 && j < 8)) {
					if (grid[i][j].isMine()) {
						numOfMines++;
					}
				}
			}
		}

		grid[row][column].setAdjacentMines(numOfMines);
	}

	public static void main(String[] args) {
		String inputfile = "";
		if(args[0] != null)
		{
			inputfile = args[0];
		}
		Minesweeper game = new Minesweeper();
		game.startup(inputfile);
	}

	protected void startup(String inputFile) {

		String userInput = "";
		String username = "";
		String password = "";
		boolean isAuthenticated = false;
		try {
			 FileReader in = new FileReader(
			 		"C:\\Users\\Garrison\\Project-0-Garrison\\src\\main\\resources\\" + inputFile);
			 BufferedReader br = new BufferedReader(in);

			// This while loop is the UI
			System.out.println("This is Minesweeper! \n Please enter your username");

			username = InputHandler.read(br);
			password = InputHandler.read(br);

			isAuthenticated = database.authenticateUser(username, password);

			while (isPlaying && isAuthenticated) {
				display();
				System.out.println();
				System.out.println();

				System.out.println("Please enter exit in order to exit the game");
				System.out.println("To select a tile: enter select [row number] [column number]");
				System.out.println("To flag a tile: enter flag [row number] [column number]");
				System.out.println("To unflag a tile: enter unflag [row number] [column number]");

				userInput = InputHandler.read(br);
				System.out.println(userInput);

				String[] command = userInput.split(" ");

				executeCommand(command);

				if (hasWon()) {
					win();
					database.incrementWin(username);
				}

				if (hasLost) {
					lose();
					database.incrementLoss(username);
				}

			}

			database.printUsernameStats(username);

			System.out.println("Would you like to restart the game? (Y/N):");

			// userInput = myScanner.nextLine();
			userInput = InputHandler.read(br);

			if (userInput.equals("Y")) {
				isPlaying = true;
				//restart();
			} else {
				System.out.println("Thank you for playing!");
				System.exit(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//	protected void restart() {
//		Minesweeper game = new Minesweeper();
//		game.startup();
//	}

	protected void executeCommand(String[] command) {

		String subcommand = "";
		int row = 0;
		int column = 0;

		subcommand = command[0];

		if (command.length == 3) {
			try {
				row = Integer.parseInt(command[1]);
				column = Integer.parseInt(command[2]);
			} catch (NumberFormatException e) {
				System.out.println("Invalid command.  [row] and [column] values must be a number between 0 and 7");
				return;
			}
		}

		if (!((row >= 0 && row <= 7) && (column >= 0 && column <= 7))) {
			System.out.println("Numbers are not in the correct range. Invalid command");
			return;
		}

		switch (subcommand) {
		case "exit":
			System.out.println("Thank you for playing");
			System.exit(0);
			break;
		case "select":
			this.selectTile(row, column);
			break;
		case "flag":
			this.flagTile(row, column);
			break;
		case "unflag":
			this.unflagTile(row, column);
			break;
		default:
			System.out.println("Invalid Command");
			break;
		}

	}

	protected boolean hasWon() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].isCovered() && !grid[i][j].isMine()) {
					return false;
				}
			}
		}
		return true;
	}

	protected void win() {

		uncoverAllTiles();
		display();
		System.out.println("Congratulations! You won!");
		isPlaying = false;

	}

	protected void uncoverTile(int row, int column) {
		grid[row][column].setTileDisplayValue(String.valueOf(grid[row][column].getAdjacentMines()));
		grid[row][column].setCovered(false);
	}

	protected void lose() {
		uncoverAllTiles();
		display();
		System.out.println("You hit a bomb!");
		System.out.println("Game Over!");
		isPlaying = false;
	}

	protected void uncoverAllTiles()
	{
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].isMine()) {
					grid[i][j].setTileDisplayValue("B");
				} else {
					grid[i][j].setTileDisplayValue(String.valueOf(grid[i][j].getAdjacentMines()));
				}
			}
		}
	}

	protected void selectTile(int row, int column) {
		if (grid[row][column].isMine()) 
		{
			hasLost = true;
		} else if (!grid[row][column].isFlag()) 
		{
			if (grid[row][column].isCovered() == true) 
			{
				this.uncoverTile(row, column);

				if (grid[row][column].getAdjacentMines() == 0) 
				{
					this.uncoverAdjacentTiles(row, column);
				}
			}
		}
	}

	protected void uncoverAdjacentTiles(int row, int column) 
	{
		for (int i = row - 1; i < row + 2; i++) 
		{
			for (int j = column - 1; j < column + 2; j++) 
			{
				if (i == row && j == column) 
				{
					continue;
				}

				if ((i >= 0 && i < 8) && (j >= 0 && j < 8)) 
				{
					this.selectTile(i, j);
				}
			}
		}
	}

	protected void flagTile(int row, int column) {
		if (grid[row][column].isCovered()) {
			grid[row][column].setFlag(true);
			grid[row][column].setTileDisplayValue("F");
		}
	}

	protected void unflagTile(int row, int column) {
		if (grid[row][column].isFlag()) {
			grid[row][column].setFlag(false);
			grid[row][column].setTileDisplayValue(" ");
		}
	}

	protected void display() {
		// Sets up the numbers at the top of the display
		for (int i = 0; i < 8; i++) {
			if (i == 7) {
				System.out.println("   " + i + "   column number");
				System.out.println("----------------------------------");
			} else {
				System.out.print("  " + i + " ");
			}
		}
		// Sets up the rows of the grid
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (j == 7) {
					System.out.print("| " + grid[i][j].getTileDisplayValue() + " |");
					System.out.println("   " + i + "   row number");
					System.out.println("----------------------------------");
				} else {
					System.out.print("| " + grid[i][j].getTileDisplayValue() + " ");
				}
			}
		}
	}

}