/**
 * InputHandler is a class that handles the input from a .CSV file
 * The read(BufferedReader br) method has a BufferedReader object as input and outputs the next line of the BufferedReader object.
 */

package com.github.garrisonadams.minesweeper.io;

import java.io.BufferedReader;
import java.io.IOException;

public class InputHandler
{
	
	public static String read(BufferedReader br)
	{
		String output = "";
		try {
			output = br.readLine();
			if(output == null)
			{
				System.out.println("File is empty");
				System.exit(0);
			}
			else
			{
				return output;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}

}
