package com.github.garrisonadams;

public class Tile {
	// To Do List:

	// The location of the tile in the Minesweeper display
	private int row;
	private int column;

	// The states of a tile
	// covered: ' '
	// uncovered w/ bomb: 'X'
	// uncovered w/o bomb: 'num'

	private boolean isCovered;

	// Whether or not the tile has a bomb
	private boolean isMine;

	// The number of bombs that are adjacent to this tile
	private int adjacentMines;

	// What the tile displays on the command line
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