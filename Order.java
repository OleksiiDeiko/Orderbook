public class Order
{
	private int price;
	private int size;
	private String type;
	Order(int price, int size, String type)
	{
		this.price = price;
		this.size = size;
		this.type = type;
	}

	public int getPrice(){return this.price;}

	public int getSize(){return this.size;}

	public String getType(){return this.type;}

	public void setPrice(int size){this.price = price;}

	public void setSize(int size){this.size = size;}

	public String toString()
	{
		return price+" "+size+" "+type;
	}
}