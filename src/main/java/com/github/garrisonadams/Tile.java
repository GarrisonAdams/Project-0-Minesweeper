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
    private boolean isBomb;

    //The number of bombs that are adjacent to this tile
    private int adjacentBombs;

    //What the tile displays on the command line
    private String tileDisplayValue;

    private boolean isMarked;

    public Tile(int row, int column)
    {
        this.row = row;
        this.column = column;
        this.tileDisplayValue = " ";
        this.isCovered = true;
        this.adjacentBombs = 1;
        this.isMarked = false;
    }

    @Override
    public String toString()
    {
        return "Row: " + this.row + " Column: " + this.column + " isMarked " + this.getIsMarked() +  " isCovered " + this.getIsCovered();
    }


    public void setTileDisplayValue(String value)
    {
        this.tileDisplayValue = value;
    }

    public String getTileDisplayValue()
    {
        return this.tileDisplayValue;
    }



    public void setIsCovered(boolean bool)
    {
        this.isCovered = bool;
    }


    public boolean getIsCovered()
    {
        return this.isCovered;
    }


    public void setIsMarked(boolean bool)
    {
        this.isMarked = bool;
    }


    public boolean getIsMarked()
    {
        return this.isMarked;
    }


    public void setAdjacentBombs(int bombs)
    {
        this.adjacentBombs = bombs;
    }

    public int getAdjacentBombs()
    {
        return this.adjacentBombs;
    }

}