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
        grid[4][5].setAdjacentBombs(0);  
        grid[4][6].setAdjacentBombs(0);   
        grid[3][6].setAdjacentBombs(0);     
        grid[5][3].setAdjacentBombs(0);    
        grid[3][5].setAdjacentBombs(0);  
        grid[3][4].setAdjacentBombs(0);    
        grid[6][2].setAdjacentBombs(0);          
      
       
      
 
         
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
        System.out.println("Inside selectTile");
        System.out.println("row: " + row + " " + "column: " + column + " is covered: " + grid[row][column].getIsCovered());

        if(grid[row][column].getIsCovered() == true)
        {
            System.out.println("Going to uncoverTile() method");
            grid[row][column].uncoverTile();
            if(grid[row][column].getAdjacentBombs()==0)
            {
                this.uncoverAdjacentTiles(row,column);

            }
        }

    }


    public void uncoverAdjacentTiles(int row, int column)
    {

        System.out.println("Inside uncoverAdjacentTiles");
        System.out.println("Input parameters: row: " + row + " " + "column: " + column);

        for(int i=row-1;i<row+2;i++)
        {
            for(int j = column-1; j<column+2; j++)
            {
                System.out.println("Inside for loop: " + "row" +  i  + "column: " + j);
                System.out.println("This Tile has " + grid[i][j].getAdjacentBombs() +" bombs");
                if(i == row && j == column)
                {
                    System.out.println(" i equals row and j equals column");
                    continue;
                }
                    this.selectTile(i,j);
                
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

