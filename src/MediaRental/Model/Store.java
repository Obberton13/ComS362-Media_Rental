package MediaRental.Model;

import java.util.ArrayList;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class Store {
	protected int id;
	protected ArrayList<Transaction> transactions;
	protected ArrayList<Customer> customers;
	protected ArrayList<Product> products;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
}
