package com.github.garrisonadams;

public class Tile
{
    //To Do List:
    
    //The location of the tile in the Minesweeper display
    int row;
    int column;

    //The states of a tile
    // covered: ' '
    // uncovered w/ bomb: 'X'
    // uncovered w/o bomb: 'num'

    private boolean isCovered;


	//Whether or not the tile has a bomb
    private boolean isMine;

    //The number of bombs that are adjacent to this tile
    private int adjacentMines;

    //What the tile displays on the command line
    private String tileDisplayValue;

    private boolean isMarked;

    public Tile(int row, int column)
    {
        this.row = row;
        this.column = column;
        this.tileDisplayValue = " ";
        this.isCovered = true;
        this.adjacentMines = 0;
        this.isMarked = false;
        this.isMine = false;

    }

    @Override
    public String toString()
    {
        return "Row: " + this.row + " Column: " + this.column 
        		+ " isMarked " + this.isMarked() +  " isCovered " + this.isCovered() 
        		+ " isMine " + this.isMine();
    }


    public void setTileDisplayValue(String value)
    {
        this.tileDisplayValue = value;
    }

    public String getTileDisplayValue()
    {
        return this.tileDisplayValue;
    }



    public void setCovered(boolean bool)
    {
        this.isCovered = bool;
    }


    public boolean isCovered()
    {
        return this.isCovered;
    }

    

    public void setMarked(boolean bool)
    {
        this.isMarked = bool;
    }

    public boolean isMarked()
    {
        return this.isMarked;
    }

    
    public void setAdjacentMines(int num)
    {
       this.adjacentMines = num;
    }

    public int getAdjacentMines()
    {
        return this.adjacentMines;
    }

    
    public boolean isMine() {
 		return this.isMine;
 	}

 	public void setMine(boolean isMine) {
 		this.isMine = isMine;
 	}
}