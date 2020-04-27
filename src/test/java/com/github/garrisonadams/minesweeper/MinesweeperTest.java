package com.github.garrisonadams.minesweeper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;


public class MinesweeperTest {


	
	@Test
    public void whenFlaggingTiles() {
    	Minesweeper game = new Minesweeper();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				game.flagTile(i,j);
				assertEquals(true,game.grid[i][j].isFlag());
				assertEquals("F",game.grid[i][j].getTileDisplayValue());
			}
		}


		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				game.unflagTile(i,j);
				assertEquals(false,game.grid[i][j].isFlag());
				assertEquals(" ",game.grid[i][j].getTileDisplayValue());
			}
		}
    	
    }
	    @Test
	    public void whenUnflaggingTiles() {
	    	Minesweeper game = new Minesweeper();
	    	
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					game.flagTile(i,j);
				}
			}
			
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					game.unflagTile(i,j);
					assertEquals(false,game.grid[i][j].isFlag());
					assertEquals(" ",game.grid[i][j].getTileDisplayValue());
				}
			}
	    }
	    
	    @Test
	    public void whenUncoveringTilesThenFlaggingThem()
	    {
	    	Minesweeper game = new Minesweeper();

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					game.uncoverTile(i,j);
				}
			}
			
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					game.flagTile(i,j);
					assertEquals(false,game.grid[i][j].isFlag());
					assertNotEquals(" ",game.grid[i][j].getTileDisplayValue());
					assertNotEquals("F",game.grid[i][j].getTileDisplayValue());

				}
			}
	    }
	    
	    @Test
	    public void whenFlaggingTilesThenSelectingThem()
	    {
	    	Minesweeper game = new Minesweeper();

	    	for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					game.flagTile(i,j);
				}
			}
			
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					game.selectTile(i,j);
					assertEquals(true,game.grid[i][j].isFlag());
					assertNotEquals(" ",game.grid[i][j].getTileDisplayValue());
					assertEquals("F",game.grid[i][j].getTileDisplayValue());

				}
			}
	    	
	    }
	    
	    @Test
	    public void whenCountingMines()
	    {
	    	Minesweeper game = new Minesweeper();
	    	int numOfMines = 0;
	    	
	    	for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if(game.grid[i][j].isMine())
						numOfMines++;
				}
			}
	    	
	    	assertEquals(10,numOfMines);
	    	
	    }
}
