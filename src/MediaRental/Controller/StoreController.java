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
	static DatabaseSupport db = new DatabaseSupport();
	
	/**
	 * @param name the name of the customer to add
	 * @param address the address of the customer to add
	 * @return true on success, false otherwise
	 */
	public static int AddCustomer(String name, String address) {
		DatabaseSupport db = new DatabaseSupport();
		Customer customer = new Customer(name, address);
		return db.addCustomer(customer);

	}

	/**
	 * @param cid the ID of the customer to remove
	 * @return true on success, false otherwise
	 */
	public static boolean RemoveCustomer(int cid) {
		db.removeCustomer(cid);
		return true;
	}

	/**
	 * @param name the name of the product to create
	 * @param type the type of the product to create
	 * @return true on success, false otherwise
	 */
	public static boolean CreateProduct(String name, String type) {
		Product product = new Product();
		product.setTitle(name);
		product.setQuantity(0);
		product.setType(type);
		return db.addProductToCatalog(product) == 0;
	}

	/**
	 * @param pid the ID of the product to add to the store
	 * @param qty the amount of the product to add to the store
	 * @return true on success, false otherwise
	 */
	public static boolean AddProduct(int pid, int qty) {
		for (int i = 0; i < qty; i++)
		{
		    db.addProductToStore(pid);
		}
		
		return true;
	}

	/**
	 * @param cid The ID of the customer who is doing the purchasing
	 * @return true on success, false otherwise
	 */
	public static boolean CreateTransaction(int cid){
		Transaction transaction = new Transaction();
		transaction.setCustomer(db.getCustomer(cid));
		transaction.setId(db.addTransactionToStore(transaction));
		return false;
	}

	/**
	 * @param productID The ID of the product to add to the transaction
	 * @param transactionID The ID of the transaction to be added to
	 * @return true on success, false otherwise
	 */
	public static boolean AddSale(Transaction transaction, Product product, String duedate) {
		transaction.getSales().add(product);
		
		return true;
	}

	/**
	 * @param productID The ID of the product to add to the transaction
	 * @param transactionID The ID of the transaction to be added to
	 * @param rentalLength The length that the specified product will be rented
	 * @return true on success, false otherwise
	 */
	public static boolean AddRental(Product product, Transaction transaction, String dueDate) {
		transaction.getRentals().add(product); 
		return true;
	}

	/**
	 * @param arguments The arguments used to find the product(s).
	 * @return an ArrayList of products containing all products found that match the arguments
	 */
	public static ArrayList<Product> FindProduct(HashMap<String, String> arguments) {
		return new ArrayList<Product>();
	}
}


