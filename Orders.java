import java.util.*;

public class Orders
{
	private static String type;
	private static int size;

	public static void execute(String[] params)
	{
		String type = params[1];
		size = Integer.parseInt(params[2]);
		if(size < 0) 
		{
			System.out.println("Wrong input value "+size);
			return;
		}
		
		List<Order> book = Orderbook.getBook();
		String changedType = "";
		String[] result = new String[0];

		switch (type) {
			case "buy":
				result = Query.findBestAsk(book).split(",");
				changedType = Orderbook.ASK_ORDER;
				break;
			case "sell":
				result = Query.findBestBid(book).split(",");	
				changedType = Orderbook.BID_ORDER;
				break;
			default:
				System.out.println("Wrong order: " + type);
				break;
		}

		if(changedType.equals(Orderbook.ASK_ORDER) || changedType.equals(Orderbook.BID_ORDER))
		{
			int price = 0;
			if(result.length > 1) price = Integer.parseInt(result[0]);
			Order oldOrder = Orderbook.findByPrice(price, changedType);
			int changedSize,
				diff = 0;
			if(oldOrder != null)
			{ 
				changedSize = oldOrder.getSize() - size;
				if(changedSize<0) 
				{
					diff = (-1)*changedSize;
					changedSize=0;
				}
				String[] newUpdParams = new String[]{"u", Integer.toString(price), Integer.toString(changedSize), changedType};
				Update.execute(newUpdParams);
				if(diff > 0)
				{
					String[] newOrdParams= new String[]{"o", type, Integer.toString(diff)};
					execute(newOrdParams);
				}
			}
		}
	}
}