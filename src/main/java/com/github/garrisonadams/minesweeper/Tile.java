/**
 * This is the Tile class of the Minesweeper project.
 * For each Minesweeperg game, these Tiles are arranged in an 8x8 grid.
 * 
 * row and column describe a Tile's place in a grid
 * isMine is a boolean variable that shows a Tile to be a mine
 * isFlag is a boolean variable that shows a Tile to be flagged
 * isCovered is a boolean variable that shows whether or not a Tile has been "flipped over" or not
 * tileDisplayValue is the String variable that the Tile displays in the grid. Acceptable values are " " "F" "B" and the numbers "0" through "8"
 * adjacentMines is the integer value that gives the number of mines adjacent to the tile. Acceptable values are 0 through 8. It is displayed when a tile is uncovered.
 * 
 * There are getters and setters for all of these variables except row and column. 
 */
package com.github.garrisonadams.minesweeper;

public class Tile {

	private int row;
	private int column;


	private boolean isCovered;

	private boolean isMine;

	private int adjacentMines;

	private String tileDisplayValue;

	private boolean isFlag;

	protected Tile(int row, int column) {
		this.row = row;
		this.column = column;
		this.tileDisplayValue = " ";
		this.isCovered = true;
		this.adjacentMines = 0;
		this.isFlag = false;
		this.isMine = false;

	}

	@Override
	public String toString() {
		return "Row: " + this.row + " Column: " + this.column + " isFlagged " + this.isFlag() + " isCovered "
				+ this.isCovered() + " isMine " + this.isMine();
	}


	protected void setTileDisplayValue(String value) {
		this.tileDisplayValue = value;
	}

	protected String getTileDisplayValue() {
		return this.tileDisplayValue;
	}

	protected void setCovered(boolean bool) {
		this.isCovered = bool;
	}

	protected boolean isCovered() {
		return this.isCovered;
	}

	protected void setFlag(boolean bool) {
		this.isFlag = bool;
	}

	protected boolean isFlag() {
		return this.isFlag;
	}

	protected void setAdjacentMines(int num) {
		this.adjacentMines = num;
	}

	protected int getAdjacentMines() {
		return this.adjacentMines;
	}

	protected boolean isMine() {
		return this.isMine;
	}

	protected void setMine(boolean isMine) {
		this.isMine = isMine;
	}
}