package MediaRental.Controller;

import MediaRental.Model.*;
import MediaRental.DatabaseSupport;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class Store {
	public boolean AddCustomer(String name, String address) {
		MediaRental.Model.Store store = this.getStore();
		Customer customer = new Customer();
		customer.setStore(store);
		customer.setName(name);
		customer.setAddress(address);
		return DatabaseSupport.putCustomer(customer);
	}

	public boolean RemoveCustomer(int cid) {
		return DatabaseSupport.removeCustomer(cid);
	}

	public boolean CreateProduct(String name, String type, ArrayList<String> keywords) {
		Product product = new Product();
		product.setStore(this.getStore());
		product.setTitle(name);
		product.setQuantity(0);
		product.setType(type);
		return DatabaseSupport.putProduct(product);
	}

	public boolean AddProduct(int pid, int qty) {
		return true;
	}

	public boolean CreateTransaction(int cid){
		return true;
	}

	public boolean AddSale(int productID, int transactionID) {
		return true;
	}

	public boolean AddRental(int productID, int transactionID, int rentalLenght) {
		return true;
	}

	public ArrayList<Product> FindProduct(HashMap<String, String> arguments) {
		return new ArrayList<Product>();
	}
	private MediaRental.Model.Store getStore()
	{
		return new MediaRental.Model.Store();
	}
}
