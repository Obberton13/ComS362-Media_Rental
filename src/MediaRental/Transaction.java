package MediaRental;

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
	protected Boolean paid;
	protected String statement;

	public Transaction(Customer customer)
	{
		this.customer = customer;
		rentals = new ArrayList<Rental>();
		sales = new ArrayList<Sale>();
		id = 0;
	}

	public Transaction(Customer customer, ArrayList<Rental> rentals, ArrayList<Sale> sales)
	{
		this.customer = customer;
		this.rentals = rentals;
		this.sales = sales;
		id = 0;
	}

	public Transaction(Customer customer, ArrayList<Rental> rentals, ArrayList<Sale> sales, Boolean paid, String statement, int id)
	{
		this.customer = customer;
		this.rentals = rentals;
		this.sales = sales;
		this.id = id;
		this.paid = paid;
		this.statement = statement;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public ArrayList<Rental> getRentals()
	{
		return rentals;
	}

	public void setRentals(ArrayList<Rental> rentals)
	{
		this.rentals = rentals;
	}

	public ArrayList<Sale> getSales()
	{
		return sales;
	}

	public void setSales(ArrayList<Sale> sales)
	{
		this.sales = sales;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public void addSale(Sale sale)
	{
		sales.add(sale);
	}

	public void addRental(Rental rental)
	{
		rentals.add(rental);
	}
	public boolean pay()
	{
		paid = true;
		return paid == true;
	}
}