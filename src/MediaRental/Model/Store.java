package MediaRental.Model;

import MediaRental.DatabaseSupport;

import java.util.ArrayList;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class Store
{
	private DatabaseSupport db = null;

	public Store()
	{
	}

	public boolean addCustomer(String name, String address, int id)
	{
		Customer c = new Customer(name, address, id);
		return this.getDatabaseSupportInstance().addCustomer(c);
	}

	public boolean removeCustomer(int id)
	{
		return this.getDatabaseSupportInstance().removeCustomer(id);
	}

	public boolean createProduct(String title, String type, String genre, int id)
	{
		Product product = new Product(title, type, genre, id);
		return getDatabaseSupportInstance().addProductToCatalog(product);
	}

	public boolean addProduct(int productID, int qty)
	{
		return getDatabaseSupportInstance().addProductToStore(productID, qty);
	}

	public boolean addRental(int tid, int pid, String dueDate, double price, int id)
	{
		Transaction transaction = getDatabaseSupportInstance().getTransaction(tid);
		return transaction.addRental(pid, price, id, dueDate);
	}

	public boolean addSale(int tid, int pid, double price, int id)
	{
		Transaction transaction = getDatabaseSupportInstance().getTransaction(tid);
		return transaction.addSale(pid, price, id);
	}

	public boolean createTransaction(int cid, int tid)
	{
		return getDatabaseSupportInstance().getCustomer(cid).addTransaction(tid);
	}

	public ArrayList<Product> findProducts(String title, String genre)
	{
		return getDatabaseSupportInstance().findProducts(title, genre);
	}

	private DatabaseSupport getDatabaseSupportInstance()
	{
		if (db == null)
		{
			db = new DatabaseSupport();
		}
		return db;
	}
}
