package MediaRental.Controller;

import MediaRental.Model.*;
import MediaRental.DatabaseSupport;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class StoreController
{
	private Store store;
	static DatabaseSupport db = new DatabaseSupport();

	/**
	 * @param name    the name of the customer to add
	 * @param address the address of the customer to add
	 * @return true on success, false otherwise
	 */
	public boolean addCustomer(String name, String address, int id)
	{
		return this.getStoreInstance().addCustomer(name, address, id);
	}

	/**
	 * @param cid the ID of the customer to remove
	 * @return true on success, false otherwise
	 */
	public boolean removeCustomer(int cid)
	{
		return this.getStoreInstance().removeCustomer(cid);
	}

	/**
	 * @param name the name of the product to create
	 * @param type the type of the product to create
	 * @return true on success, false otherwise
	 */
	public boolean createProduct(String name, String type, String genre, int id)
	{
		return getStoreInstance().createProduct(name, type, genre, id);
	}

	/**
	 * @param pid the ID of the product to add to the store
	 * @param qty the amount of the product to add to the store
	 * @return true on success, false otherwise
	 */
	public boolean addProduct(int pid, int qty)
	{
		return getStoreInstance().addProduct(pid, qty);
	}

	/**
	 * @param cid The ID of the customer who is doing the purchasing
	 * @return true on success, false otherwise
	 */
	public boolean createTransaction(int cid, int tid)
	{
		return getStoreInstance().createTransaction(cid, tid);
	}

	/**
	 * @param productID     The ID of the product to add to the transaction
	 * @param transactionID The ID of the transaction to be added to
	 * @return true on success, false otherwise
	 */
	public boolean addSale(int tid, int pid, double price, int id)
	{
		return getStoreInstance().addSale(tid, pid, price, id);
	}

	/**
	 * @param product     The product to add to the transaction
	 * @param transaction The transaction to be added to
	 * @param dueDate     the date the Rental will be due
	 * @return true on success, false otherwise
	 */
	public boolean addRental(int tid, int pid, String dueDate, double price, int id)
	{
		return getStoreInstance().addRental(tid, pid, dueDate, price, id);
	}

	/**
	 * @param title the title of the product you are looking for
	 * @param type  the type of the product you are looking for
	 * @return
	 */
	public ArrayList<Product> findProduct(String title, String type)
	{
		return getStoreInstance().findProducts(title, type);
	}

	private Store getStoreInstance()
	{
		if (store == null)
		{
			store = new Store();
		}
		return store;
	}
}


