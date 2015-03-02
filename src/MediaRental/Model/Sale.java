package MediaRental.Model;

public class Sale
{

	protected Product product;
	protected int id;
	protected double price;

	public Sale()
	{
		product = null;
		id = 0;
		price = 0;
	}

	public Sale(Product product)
	{
		this.product = product;
		id = 0;
		price = 0;
	}

	public Sale(Product product, double price)
	{
		this.product = product;
		this.price = price;
	}

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

	public void setId(int id)
	{
		this.id = id;
	}

	public void setPrice(double p)
	{
		price = p;
	}

	public double getPrice()
	{
		return price;
	}


	public void setProduct(Product p)
	{
		product = p;
	}

	public Product getProduct()
	{
		return product;
	}
}
