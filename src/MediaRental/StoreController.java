package MediaRental;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class StoreController
{
	private Store store = new Store();
	static DatabaseSupport db = new DatabaseSupport();
	
	public boolean createRentalPricingStrategy(double standardRentalCharge, int standardRentalLength, double dailyOverdueCharge, String name){
	    return this.store.createRentalPricingStrategy(standardRentalCharge, standardRentalLength, dailyOverdueCharge, name);
	}

	public boolean setRentalPricingStrategy(String strategyName, int productID)
	{
		return this.store.setRentalPricingStrategy(strategyName, productID);
	}
	
	public String getTransactionStatement(int tid){
        return this.store.getTransactionStatement(tid);
    }

	/**
	 * @param name    the name of the customer to add
	 * @param address the address of the customer to add
	 * @return true on success, false otherwise
	 */
	public boolean addCustomer(String name, String address)
	{
		return this.store.addCustomer(name, address);
	}

	/**
	 * @param cid the ID of the customer to remove
	 * @return true on success, false otherwise
	 */
	public boolean removeCustomer(int cid)
	{
		return this.store.removeCustomer(cid);
	}

	/**
	 * @param name the name of the product to create
	 * @param type the type of the product to create
	 * @return true on success, false otherwise
	 */
	public boolean createProduct(String name, String type, String genre, String description)
	{
		return this.store.createProduct(name, type, genre, description);
	}

	/**
	 * @param pid the ID of the product to add to the store
	 * @param qty the amount of the product to add to the store
	 * @return true on success, false otherwise
	 */
	public boolean addProduct(int pid, int qty)
	{
		return this.store.addProduct(pid, qty);
	}

	/**
	 * @param cid The ID of the customer who is doing the purchasing
	 * @return true on success, false otherwise
	 */
	public boolean createTransaction(int cid)
	{
		return this.store.createTransaction(cid);
	}

	/**
	 * @param productID     The ID of the product to add to the transaction
	 * @param transactionID The ID of the transaction to be added to
	 * @return true on success, false otherwise
	 */
	public boolean addSale(int tid, int pid)
	{
		return this.store.addSale(tid, pid);
	}

	/**
	 * @param product     The product to add to the transaction
	 * @param transaction The transaction to be added to
	 * @param dueDate     the date the Rental will be due
	 * @return true on success, false otherwise
	 */
	public boolean addRental(int tid, int pid, String dueDate)
	{
		return this.store.addRental(tid, pid, dueDate);
	}

	/**
	 * @param title the title of the product you are looking for
	 * @param type  the type of the product you are looking for
	 * @return
	 */
	public ArrayList<Product> findProduct(String title, String type)
	{
	    return this.store.findProducts(title, type);
	}
	
	public boolean payForTransaction(int tid)
	{
		return this.store.payForTransaction(tid);
	}
	
	public boolean createFrequentCustomerStrategy(int fixedPoints, int pointsPerDay, String name)
	{
		return this.store.createFrequentCustomerStrategy(fixedPoints, pointsPerDay, name);
	}
	
	public boolean setFrequentCustomerStrategy(String strategyName, int productID)
	{
		return this.store.setFrequentCustomerStrategy(strategyName, productID);
	}
	
	public String getCustomerContactInfo(int customerID)
	{
		return this.store.getCustomerContactInfo(customerID);
	}
}


