package MediaRental.Model;

import MediaRental.DatabaseSupport;

import java.util.ArrayList;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class Transaction
{
	protected int id;
	protected ArrayList<Rental> rentals;
	protected ArrayList<Sale> sales;

	public Transaction(int id)
	{
		rentals = new ArrayList<Rental>();
		sales = new ArrayList<Sale>();
		this.id = id;
	}

	public Transaction(int id, ArrayList<Sale> sales, ArrayList<Rental> rentals)
	{
		this.id = id;
		this.sales = sales;
		this.rentals = rentals;
	}

	public int getId()
	{
		return id;
	}

	public ArrayList<Rental> getRentals()
	{
		return rentals;
	}

	public ArrayList<Sale> getSales()
	{
		return sales;
	}


	public boolean addSale(int productID, double price, int id)
	{
		Product product = new DatabaseSupport().getProduct(productID);
		Sale sale = new Sale(product, price, id);
		return sales.add(sale);
	}

	public boolean addRental(int productID, double price, int id, String dueDate)
	{
		Product product = new DatabaseSupport().getProduct(productID);
		Rental rental = new Rental(product, dueDate, price, id);
		return rentals.add(rental);
	}
}
