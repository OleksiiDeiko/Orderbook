/*----importing packages and constants----*/

import java.io.IOException;
import java.util.*;

/*----implementation of order book----*/

public class Orderbook
{

	private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

	private static final String PATH_IN = "./input.txt";
	private static final String PATH_OUT = "./output.txt";
	public static final String BID_ORDER = "bid";
	public static final String ASK_ORDER = "ask";
	private static List<Order> book = new ArrayList<Order>();
	private static List<String> output = new ArrayList<String>();

	public static List<Order> getBook() { return book; }

	public static Order findByPrice(int price)
	{
		for(Order order : book)
		{
			if(order.getPrice() == price) return order;
		}
		return null;
	}

	public static Order findByPrice(int price, String type)
	{
		for(Order order : book)
		{
			if(order.getPrice() == price && order.getType().equals(type)) return order;
		}
		return null;
	}

	public static Order findBySize(int size)
	{
		for(Order order : book)
		{
			if(order.getSize() == size) return order;
		}
		return null;
	}

	public static void replaceOrder(int index, Order newOrder)
	{
		book.set(index, newOrder);
	}

	public static void replaceOrder(Order oldOrder, Order newOrder)
	{
		int index = book.indexOf(oldOrder);
		Orderbook.replaceOrder(index, newOrder);
	}

	private static void execute(String command)
	{
		String[] params = command.split(",");
		String action = params[0];
		switch(action){
			case "u": 
				Update.execute(params);
				break;
			case "q": 
				output.add(Query.execute(params));
				break;
			case "o":			
				Orders.execute(params);
				break;
			default:
				System.out.println("Wrong command: "+ action);
				break;
		}
	}

	public static void main(String[] args)
	{ 
		try {
			Input.readFromFile(PATH_IN);
		} catch(IOException e){
			e.printStackTrace();	
		}

		for(String command : Input.commands)
		{
			Orderbook.execute(command);
		}

		Output.writeToFile(output.toArray(new String[0]), PATH_OUT);

		Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: "
                + bytesToMegabytes(memory));
	}
}