package MediaRental.Model;

import java.util.ArrayList;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class Customer {

    protected int id;
    protected Store store;
	protected String name;
	protected String address;
	protected ArrayList<Transaction> transactions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

    public Customer()
    {
		transactions = new ArrayList<Transaction>();
    }

    public int getId() {
		return id;
    }

    public void setId(int id) {
		this.id = id;
    }

    public Store getStore() {
		return store;
    }

    public void setStore(Store store) {
		this.store = store;
    }

    public ArrayList<Transaction> getTransactions() {
		return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
    }
}
