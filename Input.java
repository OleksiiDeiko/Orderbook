import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Input
{
	public static String[] commands;

	public static void readFromFile(String filename) throws IOException
	{
		List<String> lines = new ArrayList<String>();
		File file = new File(filename);
		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line;

		while( (line = reader.readLine()) != null)
		{
			if(line.equals("") || lines.size() > 25) break;
			lines.add(line);
		}
		reader.close();

		commands = lines.toArray(new String[0]);

	}
}