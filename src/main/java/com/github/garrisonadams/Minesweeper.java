package com.github.garrisonadams;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class Minesweeper {

	private boolean isPlaying = true;
	private boolean hasLost = false;

	private Tile[][] grid = new Tile[8][8];

	public Minesweeper() {
		// Initializes the tiles of the Minesweeper grid
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Tile(i, j);
			}
		}

		mineInit();

		// Initializes the tiles of the Minesweeper grid
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				calculateAdjacentMines(i, j);
			}
		}
	}

	private void calculateAdjacentMines(int row, int column) {
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
		Minesweeper game = new Minesweeper();
		game.play();
	}

	private void mineInit() {
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

	private void play() {
		String userInput = "";

		Scanner myScanner = new Scanner(System.in);
		// This while loop is the UI
		
		while (isPlaying) 
		{
			display();
			System.out.println();
			System.out.println();

			System.out.println("Please enter exit in order to exit the game");
			System.out.println("To select a tile: enter select [row number] [column number]");
			System.out.println("To flag a tile: enter flag [row number] [column number]");
			System.out.println("To unflag a tile: enter unflag [row number] [column number]");

			 userInput = myScanner.nextLine();
//				userInput = InputOutput.read(br);
			System.out.println(userInput);

			String[] command = userInput.split(" ");

			executeCommand(command);

			if(hasWon())
			{
				win();
			}
			
			if(hasLost)
			{
				lose();
			}

		}
		
		System.out.println("Would you like to restart the game? (Y/N):");
		
		userInput = myScanner.nextLine();

		if(userInput.equals("Y"))
		{
			isPlaying = true;
			restart();
		}
		else
		{
			System.out.println("Thank you for playing!");
			myScanner.close();
//			br.close();
			System.exit(0);
		}
		
	}
	
	
	private void restart()
	{
		Minesweeper game = new Minesweeper();
		game.play();
	}

	
	private void executeCommand(String[] command) {
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
			isPlaying = false;
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

	private boolean hasWon() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].isCovered() && !grid[i][j].isMine()) {
					return false;
				}
			}
		}
		
		return true;
	}

	private void win()
	{
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].isMine()) {
					grid[i][j].setTileDisplayValue("B");
				}
			}
		}
		display();
		System.out.println("Congratulations! You won!");
		isPlaying = false;

	}

	private void uncoverTile(int row, int column) {
		grid[row][column].setTileDisplayValue(String.valueOf(grid[row][column].getAdjacentMines()));
		grid[row][column].setCovered(false);
	}

	private void lose() {

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].isMine()) {
					grid[i][j].setTileDisplayValue("B");
				} else {
					grid[i][j].setTileDisplayValue(String.valueOf(grid[i][j].getAdjacentMines()));
				}
			}
		}

		display();
		System.out.println("You hit a bomb!");
		System.out.println("Game Over!");

		isPlaying = false;
	}


	private void selectTile(int row, int column) {
		if (grid[row][column].isMine()) {
			hasLost = true;
		}
		else if (!grid[row][column].isFlag()) {
			if (grid[row][column].isCovered() == true) {
				this.uncoverTile(row, column);

				if (grid[row][column].getAdjacentMines() == 0) {
					this.uncoverAdjacentTiles(row, column);
				}
			}
		}
	}

	private void uncoverAdjacentTiles(int row, int column) {
		
		for (int i = row - 1; i < row + 2; i++) {
			for (int j = column - 1; j < column + 2; j++) {
				if (i == row && j == column) {
					continue;
				}

				if ((i >= 0 && i < 8) && (j >= 0 && j < 8)) {
					this.selectTile(i, j);
				}
			}
		}
	}

	private void flagTile(int row, int column) {
		if (grid[row][column].isCovered()) {
			grid[row][column].setFlag(true);
			grid[row][column].setTileDisplayValue("F");
		}
	}

	private void unflagTile(int row, int column) {
		if (grid[row][column].isFlag()) {
			grid[row][column].setFlag(false);
			grid[row][column].setTileDisplayValue(" ");
		}
	}

	private void display() {
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
