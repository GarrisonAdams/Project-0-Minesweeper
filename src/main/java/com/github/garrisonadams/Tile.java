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
    boolean isBomb;

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

    @Override
    public String toString()
    {
        return String.valueOf(this.row) + " " + String.valueOf(this.column);
    }

    public void uncoverTile()
    {
        System.out.println("Inside uncoverTile() method");
        System.out.println("Tile " + this.row + " " + this.column +" isCovered: " + this.isCovered);
        this.setTileDisplayValue(String.valueOf(adjacentBombs));
        this.setIsCovered(false);
        System.out.println("Tile " + this.row + " " + this.column + " is " + this.getIsCovered() + " and has " + this.getAdjacentBombs() + " adjacent bombs");
        System.out.println("Exiting uncoverTile() method");
        System.out.println();

    }


    public void setIsCovered(boolean bool)
    {
        this.isCovered = bool;
    }

    public boolean getIsCovered()
    {
        return this.isCovered;
    }

    public void setAdjacentBombs(int bombs)
    {
        this.adjacentBombs = bombs;
    }

    public int getAdjacentBombs()
    {
        return this.adjacentBombs;
    }
    
    public void setIsBomb(boolean bool)
    {
        this.isBomb = bool;
    }

    public boolean getIsBomb()
    {
        return this.isBomb;
    }



}