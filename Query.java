import java.util.*;

public class Query
{
	private static String type;

	public static String execute(String[] params)
	{
		type = params[1];
		List<Order> book = Orderbook.getBook();
		switch (type) {
			case "best_bid": 
				return findBestBid(book);
			case "best_ask":
				return findBestAsk(book);
			case "size":
				int price = Integer.parseInt(params[2]);
				return findSize(book, price);
			default:
				return "Wrong query: " + type;
		}
	}
	public static String findBestBid(List<Order> book)
	{
		int	topPrice = 0,
			size = 0;

		for(Order order : book)
		{
			if(order.getType().equals(Orderbook.BID_ORDER))
			{
				int currentPrice = order.getPrice(),
					currentSize = order.getSize();
				if(currentPrice > topPrice && currentSize > 0)
				{
					topPrice = currentPrice;
					size = currentSize;
				}
			}
		}
		//return topPrice + ","+size;
		return size != 0 ? topPrice + "," + size : Integer.toString(size);

	}

	public static String findBestAsk(List<Order> book)
	{
		int minPrice = (int)Math.pow(10, 9)+1,
			size = 0;

		for(Order order : book)
		{
			if(order.getType().equals(Orderbook.ASK_ORDER))
			{
				int currentPrice = order.getPrice(),
					currentSize = order.getSize();
				if(currentPrice < minPrice && currentSize > 0)
				{
					minPrice = currentPrice;
					size = currentSize;
				}
			}
		}
		//return (int)minPrice + ","+(int)size;

		return size != 0 ? minPrice + "," + size : Integer.toString(size);
	}	

	public static String findSize(List<Order> book, int price)
	{

		int size = 0;
		Order result = Orderbook.findByPrice(price);
		if(result != null) size = result.getSize();

		return Integer.toString(size);

		//return size > 0 ? Integer.toString(size) : "";
	}
}