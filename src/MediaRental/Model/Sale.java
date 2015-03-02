package MediaRental.Model;

public class Sale
{

	protected Product product;
	protected int id;
	protected double price;

	public Sale(Product product, double price, int id)
	{
		this.product = product;
		this.price = price;
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public double getPrice()
	{
		return price;
	}

	public Product getProduct()
	{
		return product;
	}
}
