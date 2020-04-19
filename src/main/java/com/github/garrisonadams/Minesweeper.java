package com.github.garrisonadams;
import java.util.Scanner;

public class Minesweeper
{
    boolean isPlaying = true;
    boolean hasWon = false;

    static Tile[][] grid = new Tile[8][8];
    //Things to do
    //Set up the UI
    //Figure out how to connect to the database
    //Figure out how to calculate the number of bombs in a tile
    //Include winning conditions and losing conditions
    //Include marking tiles to indicate a bomb
    //Include restart method

    public Minesweeper()
    {
        //Initializes the tiles of the Minesweeper grid
        for(int i = 0; i <grid.length; i++)
        {
             for(int j = 0; j < grid[i].length; j++)
            {
                grid[i][j] = new Tile(i,j);
            }
        }
        
        grid[0][4].setMine(true);
        grid[0][1].setMine(true);
        grid[2][7].setMine(true);
        grid[0][3].setMine(true);
        grid[4][4].setMine(true);
        grid[2][5].setMine(true);
        grid[3][6].setMine(true);
        grid[0][7].setMine(true);
        grid[1][0].setMine(true);
        grid[0][3].setMine(true);

        
        
        //Initializes the tiles of the Minesweeper grid
        for(int i = 0; i <grid.length; i++)
        {
             for(int j = 0; j < grid[i].length; j++)
            {
                calculateAdjacentMines(i,j);
            }
        }
    
    

    }

    public static void calculateAdjacentMines(int row, int column)
    {
    	int numOfMines = 0;
    	
        for(int i=row-1;i<row+2;i++)
        {
            for(int j = column-1; j<column+2; j++)
            {
                if(i == row && j == column)
                {
                    continue;
                }
                
                if((i >= 0 && i <8) && (j >= 0 && j < 8))
                {
                	if(grid[i][j].isMine())
                	{
                		numOfMines++;
                	}
                }
            }
        }
        
        grid[row][column].setAdjacentMines(numOfMines);
    }
    
    public static void main(String[] args)
    {
        Minesweeper game = new Minesweeper();
        game.play();
    }
 
    public void restart()
    {
    	
    }
    public void play()
    {
        

        String userInput = "";
        Scanner myScanner = new Scanner(System.in);
 
        //This while loop is the UI
        while(isPlaying)
        {
            this.display();;
            System.out.println();
            System.out.println();

            System.out.println("Please enter exit in order to exit the game");
            System.out.println("To select a tile: enter select [row number] [column number]");
            System.out.println("To mark a tile: enter mark [row number] [column number]");
            System.out.println("To unmark a tile: enter unmark [row number] [column number]");

            userInput = myScanner.nextLine();
            String[] command = userInput.split(" ");
            
            executeCommand(command);
            
            win();

        }
        myScanner.close();

    }  

    public void executeCommand(String[] command)
    {
        String subcommand = "";
        int row = 0;
        int column = 0;

        if(command.length == 1)
        {
            subcommand = command[0];
        }
        else if(command.length == 3)
        {
            subcommand = command[0];
            try {
                row = Integer.parseInt(command[1]);
                column = Integer.parseInt(command[2]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid command.  [row] and [column] values must be a number between 0 and 7");
                return;
            }
        }

        if( !((row >= 0 && row <= 7) && (column >= 0 && column <= 7)))
        {
            System.out.println("Numbers are not in the correct range. Invalid command");
            return;
        }

        switch (subcommand) 
        {
            case "exit":
                System.out.println("Thank you for playing");
                isPlaying = false;
                break;
            case "select":
                 this.selectTile(row,column);
                break;
            case "mark":
                this.markTile(row,column);
                break;
            case "unmark":
                this.unmarkTile(row,column);
                break;       
            default:
                System.out.println("Invalid Command");
                break;
        }

    }
    

    public void win()
    {
        for(int i = 0; i <grid.length; i++)
        {
             for(int j = 0; j < grid[i].length; j++)
            {
            	 if(grid[i][j].isCovered() && !grid[i][j].isMine())
            	 {
            		 return;
            	 }
            }
        }
        
        for(int i = 0; i <grid.length; i++)
        {
             for(int j = 0; j < grid[i].length; j++)
            {
            	 if(grid[i][j].isMine())
            	 {
            		 grid[i][j].setTileDisplayValue("B");
            	 }
            }
        }
        	display();
        	System.out.println("Congratulations! You won!");
        	isPlaying = false;

    }
    
    public void uncoverTile(int row, int column)
    {
        grid[row][column].setTileDisplayValue(String.valueOf(grid[row][column].getAdjacentMines()));
        grid[row][column].setCovered(false);
    }

    

    
    
    
    
    public void lose ()
    {
    	display();
    	System.out.println("You hit a bomb!");
    	System.out.println("Game Over!");

    	isPlaying = false;
    	
    }

    
    public void selectTile(int row, int column)
    {
    	if(grid[row][column].isMine())
    	{
            grid[row][column].setTileDisplayValue("B");
            lose();
    	}
    	
        if(!grid[row][column].isMarked())
        {
            if(grid[row][column].isCovered() == true)
            {
                this.uncoverTile(row,column);

                if(grid[row][column].getAdjacentMines()==0)
                {
                    this.uncoverAdjacentTiles(row,column);
                }
            }
        }
    }
    
    
    public void uncoverAdjacentTiles(int row, int column)
    {
        for(int i=row-1;i<row+2;i++)
        {
            for(int j = column-1; j<column+2; j++)
            {
                if(i == row && j == column)
                {
                    continue;
                }
                
                if((i >= 0 && i <8) && (j >= 0 && j < 8))
                {
                    this.selectTile(i,j);
                }
            }
        }
    }

    
    
    public void markTile(int row, int column)
    {
        if(grid[row][column].isCovered())
        {
            grid[row][column].setMarked(true);
            grid[row][column].setTileDisplayValue("M");
        }
    }

    
    
    public void unmarkTile(int row, int column)
    {
        if(grid[row][column].isMarked())
        {
            grid[row][column].setMarked(false);
            grid[row][column].setTileDisplayValue(" ");
        }

    }

    
    public void display()
    {
         //Sets up the numbers at the top of the display
         for(int i = 0; i < 8; i++)
         {
             if(i == 7)
             {
                 System.out.println("  " + i + " ");
                 System.out.println("----------------------------------");
             }
             else
             {
                 System.out.print("  " + i + " ");
             }   
         }
 
         //Sets up the rows of the grid
         for(int i = 0; i < 8; i++)
         {
             for(int j = 0; j < 8; j++)
             {
                if(j == 7)
                {
                    System.out.print("| " + grid[i][j].getTileDisplayValue() + " |");
                    System.out.println("   " + i);
                    System.out.println("----------------------------------");
                }
                else
                {
                    System.out.print("| " + grid[i][j].getTileDisplayValue() + " ");
                }
             }
         }
    }

}

