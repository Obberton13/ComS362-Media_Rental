package MediaRental.Model;

public class Sale
{

	protected Product product;
	protected Transaction transaction;
	protected int id;
	protected double price;

	public Sale(Product product, Transaction transaction, double price, int id)
	{
		this.product = product;
		this.transaction = transaction;
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
