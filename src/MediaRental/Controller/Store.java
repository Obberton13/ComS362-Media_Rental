package MediaRental.Controller;

import MediaRental.Model.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class Store {
	public boolean AddCustomer(String name, String address) {
		return true;
	}

	public boolean RemoveCustomer(int cid) {
		return true;
	}

	public boolean CreateProduct(String name, String type, ArrayList<String> keywords) {
		return true;
	}

	public boolean AddProduct(int qty) {
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
}
