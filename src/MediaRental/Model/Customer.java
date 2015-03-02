package MediaRental.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class Customer
{

	private int id;
	private String name;
	private String address;
	private ArrayList<Transaction> transactions;

	public Customer(String name, String address, int id)
	{
		this.id = id;
		this.name = name;
		this.address = address;
		this.transactions = new ArrayList<Transaction>();
	}

	public String getName()
	{
		return name;
	}

	public String getAddress()
	{
		return address;
	}

	public int getId()
	{
		return id;
	}

	public ArrayList<Transaction> getTransactions()
	{
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions)
	{
		this.transactions = transactions;
	}

	public boolean addTransaction(int tid)
	{
		Transaction t = new Transaction(this, tid);
		return transactions.add(t);
	}

	@Override
	public String toString()
	{
		return "" + id + ", " + name + ", " + address;
	}
}
