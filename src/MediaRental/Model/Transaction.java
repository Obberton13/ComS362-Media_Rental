package MediaRental.Model;

import java.util.ArrayList;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class Transaction {
	protected int id;
	protected ArrayList<Product> rentals;
	protected ArrayList<Product> sales;
	protected Customer customer;
	protected Store store;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Product> getRentals() {
		return rentals;
	}

	public void setRentals(ArrayList<Product> rentals) {
		this.rentals = rentals;
	}

	public ArrayList<Product> getSales() {
		return sales;
	}

	public void setSales(ArrayList<Product> sales) {
		this.sales = sales;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
}
