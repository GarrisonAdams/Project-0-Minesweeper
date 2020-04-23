package com.github.garrisonadams;

import java.io.BufferedReader;
import java.io.IOException;

public class InputOutput 
{
	
	protected static String read(BufferedReader br)
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
