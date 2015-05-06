package MediaRental;


import java.util.ArrayList;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class Store
{
	private static DatabaseSupport db = new DatabaseSupport();

	public Store()
	{
	}

	public boolean addCustomer(String name, String address) {
        Customer customer = new Customer(name, address);
        int id =  db.addCustomer(customer);
        return id != 0;
    }

    /**
     * @param cid the ID of the customer to remove
     * @return true on success, false otherwise
     */
    public boolean removeCustomer(int cid) {
        db.removeCustomer(cid);
        return true;
    }

    /**
     * @param name the name of the product to create
     * @param type the type of the product to create
     * @return true on success, false otherwise
     */
    public boolean createProduct(String name, String type, String genre, String description) {
        Product product = new Product(name,type, genre, description);
        return db.putProduct(product, 0);
    }

    /**
     * @param pid the ID of the product to add to the store
     * @param qty the amount of the product to add to the store
     * @return true on success, false otherwise
     */
    public boolean addProduct(int catalogId, int qty) {
        Product product = new Product(catalogId);
        return db.putProduct(product, qty);
    }

    /**
     * @param cid The ID of the customer who is doing the purchasing
     * @return true on success, false otherwise
     */
    public boolean createTransaction(int cid){
        Transaction transaction = new Transaction(DatabaseSupport.getCustomer(cid));
        int id = db.putTransaction(transaction);
        return id != 0;
    }

    /**
     * @param productID The ID of the product to add to the transaction
     * @param transactionID The ID of the transaction to be added to
     * @return true on success, false otherwise
     */
    public boolean addSale(int transactionID, int productID) {
        Transaction transaction = DatabaseSupport.getTransaction(transactionID);
        Product product = DatabaseSupport.getProduct(productID);
        Sale sale = new Sale(product, 0);
        transaction.addSale(sale);
        return (db.putTransaction(transaction) > 0);
    }

    /**
     * @param product The product to add to the transaction
     * @param transaction The transaction to be added to
     * @param dueDate the date the Rental will be due
     * @return true on success, false otherwise
     */
    public boolean addRental(int transactionID, int productID, String dueDate) {
        Transaction transaction = DatabaseSupport.getTransaction(transactionID);
        if (transaction == null){
            return false;
        }
        Product product = DatabaseSupport.getProduct(productID);
        if (product == null){
            return false;
        }
        Rental rental = new Rental(product, dueDate, 0);
        transaction.addRental(rental);
        return (db.putTransaction(transaction) > 0);

    }

    /**
     * @param title the title of the product you are looking for
     * @param type the type of the product you are looking for
     * @return the products found with the given title and type
     */
    public ArrayList<Product> findProducts(String title, String type) {
        return db.findProducts(title,type);
    }

    public Transaction getTransaction(int tid)
    {
        return db.getTransaction(tid);
    }
    
    public String getTransactionStatement(int tid){
        Transaction transaction = db.getTransaction(tid);
        if (transaction == null)
        	return "transaction does not exist";
        return transaction.getStatement();
    }
        
    
    public boolean createRentalPricingStrategy(double standardRentalCharge, int standardRentalLength, double dailyOverdueCharge, String name) {
        RentalPricingStrategy pricing = new RentalPricingStrategy(standardRentalCharge, standardRentalLength, dailyOverdueCharge, name);
        return db.addRentalPricingStrategy(pricing);
    }
    
    public boolean payForTransaction(int tid)
    {
    	Transaction transaction = DatabaseSupport.getTransaction(tid);
    	if (transaction == null)
    		return false;
    	transaction.pay();
    	db.putTransaction(transaction);
    	return transaction.paid == true; 
    }
    
    public boolean createFrequentCustomerStrategy(int fixedPoints, int pointsPerDay, String name)
    {
    	FrequentCustomerStrategy customerStrategy = new FrequentCustomerStrategy(fixedPoints,pointsPerDay,name);
    	return db.addFrequentCustomerStrategy(customerStrategy);
    }
    
    public boolean setFrequentCustomerStrategy(String strategyName, int productID)
    {
    	Product p = db.getProduct(productID);
    	if (p == null)
    		return false;
    	FrequentCustomerStrategy strategy = DatabaseSupport.getFrequentCustomerStrategy(strategyName);
    	if (strategy == null)
    		return false;
    	p.setCustomerStrategy(strategy);
    	return db.putProduct(p, 0);
    }

    public boolean setRentalPricingStrategy(String strategyName, int productID)
    {
        Product p = DatabaseSupport.getProduct(productID);
        if (p == null)
        	return false;
        RentalPricingStrategy strategy = DatabaseSupport.getRentalPricingStrategy(strategyName);
        if (strategy == null)
        	return false;
        p.setRentalPricingStrategy(strategy);
        return db.putProduct(p, 0);
    }
    
    public String getCustomerContactInfo(int customerID)
    {
    	Customer c = db.getCustomer(customerID);
    	if (c == null)
    		return "customer does not exist";
    	return c.contactInfo();
    }
}
