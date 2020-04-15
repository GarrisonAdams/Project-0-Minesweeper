package com.github.garrisonadams;

public class Tile
{
    //To Do List:
    //Access modifiers?

    //The location of the tile in the Minesweeper display
    int row;
    int column;

    //The states of a tile
    // covered: ' '
    // uncovered w/ bomb: 'X'
    // uncovered w/o bomb: 'num'


    boolean isCovered;

    //Whether or not the tile has a bomb
    boolean hasBomb;

    //The number of bombs that are adjacent to this tile
    int adjacentBombs;

    //What the tile displays on the command line
    String tileDisplayValue;

    public Tile(int row, int column)
    {
        this.row = row;
        this.column = column;
        this.tileDisplayValue = " ";
        this.isCovered = true;
        this.adjacentBombs = 1;
    }

    public void setTileDisplayValue(String value)
    {
        this.tileDisplayValue = value;
    }

    public String getTileDisplayValue()
    {
        return this.tileDisplayValue;
    }

    //How to display grid one last time if bomb?
    //Option 1: have the Minesweeper object be a parameter and execute it here
    //Option 2: have this method be executed in the Minesweeper.java class


    public String toString()
    {
        return String.valueOf(this.row) + " " + String.valueOf(this.column);
    }

    public void uncoverTile()
    {
        if(isCovered)
        {
            this.setTileDisplayValue(String.valueOf(adjacentBombs));
            this.toggleIsCovered();
        }
    }


    public void toggleIsCovered()
    {
        this.isCovered = !this.isCovered;
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