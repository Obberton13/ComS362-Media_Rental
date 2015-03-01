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
	public static boolean AddCustomer(String name, String address) {
		DatabaseSupport db = new DatabaseSupport();
		Customer customer = new Customer(name, address);
		customer.setId(db.addCustomer(customer));
		return db.addCustomer(customer) != 0;

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
		Product product = new Product(name);
		product.setType(type);
		product.setId(db.addProductToCatalog(product));
		
		return db.addProductToCatalog(product) != 0;
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
		
		return db.addProductToStore(pid) != 0;
	}

	/**
	 * @param cid The ID of the customer who is doing the purchasing
	 * @return true on success, false otherwise
	 */
	public static boolean CreateTransaction(int cid){
		Transaction transaction = new Transaction();
		transaction.setCustomer(db.getCustomer(cid));
		transaction.setId(db.addTransactionToStore(transaction));
		return db.addTransactionToStore(transaction) != 0;
	}

	/**
	 * @param productID The ID of the product to add to the transaction
	 * @param transactionID The ID of the transaction to be added to
	 * @return true on success, false otherwise
	 */
	public static boolean AddSale(Transaction transaction, Product product) {
		Sale sale = new Sale();
		sale.setProduct(product);
		transaction.addSale(sale);
		sale.setId(db.addSaleToStore(sale));
		db.addSaleToTransaction(sale.getId(),transaction);
		
		return db.addSaleToStore(sale) != 0;
	}

	/**
	 * @param productID The ID of the product to add to the transaction
	 * @param transactionID The ID of the transaction to be added to
	 * @param rentalLength The length that the specified product will be rented
	 * @return true on success, false otherwise
	 */
	public static boolean AddRental(Product product, Transaction transaction, String dueDate) {
		Rental rental = new Rental();
		rental.setProduct(product);
		rental.setDueDate(dueDate);
		transaction.addRental(rental);
		rental.setId(db.addRentalToStore(rental));
		db.addRentalToTransaction(rental.getId(),rental.getDueDate(),transaction);
		
		return db.addRentalToStore(rental) != 0;
	}

	/**
	 * @param arguments The arguments used to find the product(s).
	 * @return an ArrayList of products containing all products found that match the arguments
	 */
	public static ArrayList<Product> FindProduct(String title, String type) {
		return db.findProducts(title,type);
	}
}


