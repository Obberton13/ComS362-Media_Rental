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
	protected Customer customer;

	public Transaction()
	{
		rentals = new ArrayList<Rental>();
		sales = new ArrayList<Sale>();
		customer = null;
		id = 0;
	}

	public Transaction(Customer customer, int id)
	{
		this.customer = customer;
		rentals = new ArrayList<Rental>();
		sales = new ArrayList<Sale>();
		this.id = id;
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

	public Customer getCustomer()
	{
		return customer;
	}

	public boolean addSale(int productID, double price, int id)
	{
		Product product = new DatabaseSupport().getProduct(productID);
		Sale sale = new Sale(product, this, price, id);
		return sales.add(sale);
	}

	public boolean addRental(int productID, double price, int id, String dueDate)
	{
		Product product = new DatabaseSupport().getProduct(productID);
		Rental rental = new Rental(product, this, dueDate, price, id);
		return rentals.add(rental);
	}
}
