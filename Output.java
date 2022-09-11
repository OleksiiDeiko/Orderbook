import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Output
{
	public static void writeToFile(String[] contents, String filename)
	{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			for(int i=0;i<contents.length;i++)
			{
				writer.write(contents[i]);
				if(i!=contents.length-1) writer.newLine();
			}
			writer.close();
		}

		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}