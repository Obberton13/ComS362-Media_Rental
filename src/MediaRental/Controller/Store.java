package MediaRental.Controller;

import MediaRental.Model.*;
import MediaRental.DatabaseSupport;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class Store {

	/**
	 * @param name the name of the customer to add
	 * @param address the address of the customer to add
	 * @return true on success, false otherwise
	 */
	public boolean AddCustomer(String name, String address) {
		MediaRental.Model.Store store = this.getStore();
		Customer customer = new Customer(name, address);
		customer.setStore(store);
		return false;
		//return DatabaseSupport.putCustomer(customer);
	}

	/**
	 * @param cid the ID of the customer to remove
	 * @return true on success, false otherwise
	 */
	public boolean RemoveCustomer(int cid) {
		return false;
		//return DatabaseSupport.removeCustomer(cid);
	}

	/**
	 * @param name the name of the product to create
	 * @param type the type of the product to create
	 * @return true on success, false otherwise
	 */
	public boolean CreateProduct(String name, String type) {
		Product product = new Product();
		product.setStore(this.getStore());
		product.setTitle(name);
		product.setQuantity(0);
		product.setType(type);
		return false;
		//return DatabaseSupport.putProduct(product);
	}

	/**
	 * @param pid the ID of the product to add to the store
	 * @param qty the amount of the product to add to the store
	 * @return true on success, false otherwise
	 */
	public boolean AddProduct(int pid, int qty) {
		return true;
	}

	/**
	 * @param cid The ID of the customer who is doing the purchasing
	 * @return true on success, false otherwise
	 */
	public boolean CreateTransaction(int cid){
		return true;
	}

	/**
	 * @param productID The ID of the product to add to the transaction
	 * @param transactionID The ID of the transaction to be added to
	 * @return true on success, false otherwise
	 */
	public boolean AddSale(int productID, int transactionID) {
		return true;
	}

	/**
	 * @param productID The ID of the product to add to the transaction
	 * @param transactionID The ID of the transaction to be added to
	 * @param rentalLength The length that the specified product will be rented
	 * @return true on success, false otherwise
	 */
	public boolean AddRental(int productID, int transactionID, int rentalLength) {
		return true;
	}

	/**
	 * @param arguments The arguments used to find the product(s).
	 * @return an ArrayList of products containing all products found that match the arguments
	 */
	public ArrayList<Product> FindProduct(HashMap<String, String> arguments) {
		return new ArrayList<Product>();
	}

	//private helper methods
	private MediaRental.Model.Store getStore()
	{
		return new MediaRental.Model.Store();
	}
}
