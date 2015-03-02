package MediaRental.Model;

import java.util.regex.Pattern;

public class Rental
{
	protected Product product;
	protected int id;
	protected double price;
	protected String dueDate;

	public Rental(Product product, String dueDate, double price, int id)
	{
		this.product = product;
		this.dueDate = isDate(dueDate) ? dueDate : "1990-01-01";
		this.id = id;
		this.price = price;
	}

	public int getId()
	{
		return id;
	}

	public double getPrice()
	{
		return price;
	}

	public String getDueDate()
	{
		return dueDate;
	}

	public Product getProduct()
	{
		return product;
	}

	private boolean isDate(String date)
	{
		return Pattern.compile("/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/").matcher(date).matches();
	}
}
