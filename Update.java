import java.util.*;

public class Update
{
	private static Order newOrder;

	public static void execute(String[] params)
	{
		int price = Integer.parseInt(params[1]), size = Integer.parseInt(params[2]);
		if(price < 1 /*|| price > (int)(Math.pow(10,9))*/ || size < 0 /*|| size > (int)(Math.pow(10,8))*/) 
		{
			System.out.println("Wrong input value");
			return;
		}
		String type = params[3];
		if(type.equals(Orderbook.BID_ORDER) || type.equals(Orderbook.ASK_ORDER))
		{ 
			List<Order> book = Orderbook.getBook();
			Order replaceOrder = null;		
			newOrder = new Order(price, size, type);
			replaceOrder = Orderbook.findByPrice(newOrder.getPrice(), type);
			if(replaceOrder != null) Orderbook.replaceOrder(replaceOrder, newOrder);
			else book.add(newOrder);
		}
		else System.out.println("Wrong update: "+ type);
	}
}