package com.github.garrisonadams;
import java.util.Scanner; 


public class Minesweeper
{
    static Tile[][] grid = new Tile[8][8];

    //Things to do
    //Set up the UI
    //Figure out how to connect to the database
    //Figure out how to calculate the number of bombs in a tile


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

        grid[4][4].setAdjacentBombs(0);
           
    }
 
    public static void main(String[] args)
    {
        boolean isPlaying = true; 
        String userInput = "";
        Scanner myScanner = new Scanner(System.in);
        Minesweeper test = new Minesweeper();
        int row,column;

        //This while loop is the UI
        while(isPlaying)
        {
            test.display();

            System.out.println("Please enter X in order to exit the game");
            System.out.println("Please enter select in order to select a tile");


            userInput = myScanner.nextLine();

            if(userInput.equals("X"))
            {
                System.out.println("Thank you for playing");
                System.exit(0);
            }
            else if (userInput.equals("select"))
            {
                System.out.println("Please enter which row you want to select");
                row = myScanner.nextInt();
                System.out.println("Please enter which column you want to select");
                column = myScanner.nextInt();
                test.selectTile(row,column);


            }
        }

           myScanner.close();

    }

    public void selectTile(int row, int column)
    {
        grid[row][column].uncoverTile();
        this.uncoverAdjacentTiles(row,column);

    }

    public void uncoverAdjacentTiles(int row, int column)
    {
        if(grid[row][column].getAdjacentBombs() == 0)
        {
            for(int i=row-1;i<row+2;i++)
            {
                for(int j = column-1; j<column+2; j++)
                {
                
                    grid[i][j].uncoverTile();
                    this.uncoverAdjacentTiles(i,j);
                }
            }
        }
    }

    //displays the Minesweeper grid on the CL
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

