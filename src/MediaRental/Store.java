package MediaRental;

import java.util.ArrayList;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class Store
{
    private static DatabaseSupport db;

    public Store()
    {
        db = new DatabaseSupport();
    }

    public int getFrequentCustomerPoints(int cid)
    {
        Customer customer = db.getCustomer(cid);
        if (customer == null)
        {
            return 0;
        } else
        {
            return customer.getFrequentCustomerPoints();
        }
    }

    public boolean addCustomer(String name, String address)
    {
        Customer customer = new Customer(name, address);
        int id = db.addCustomer(customer);
        return id != 0;
    }

    /**
     * @param cid the ID of the customer to remove
     * @return true on success, false otherwise
     */
    public boolean removeCustomer(int cid)
    {
        db.removeCustomer(cid);
        return true;
    }

    /**
     * @param name the name of the product to create
     * @param type the type of the product to create
     * @return true on success, false otherwise
     */
    public boolean createProduct(String name, String type, String genre, String description)
    {
        Product product = new Product(name, type, genre, description, true);
        return db.putProduct(product, 0);
    }

    /**
     * @param catalogId the ID of the product to add to the store
     * @param qty       the amount of the product to add to the store
     * @return true on success, false otherwise
     */
    public boolean addProduct(int catalogId, int qty)
    {
        Product product = new Product(catalogId, true);
        return db.putProduct(product, qty);
    }

    /**
     * @param cid The ID of the customer who is doing the purchasing
     * @return true on success, false otherwise
     */
    public boolean createTransaction(int cid)
    {
        Customer c = db.getCustomer(cid);
        if (c == null)
        {
            return false;
        }
        Transaction transaction = new Transaction(c);
        int id = db.putTransaction(transaction);
        return id != 0;
    }

    /**
     * @param productID     The ID of the product to add to the transaction
     * @param transactionID The ID of the transaction to be added to
     * @return true on success, false otherwise
     */
    public boolean addSale(int transactionID, int productID, double price)
    {
        Transaction transaction = db.getTransaction(transactionID);
        if (transaction == null || transaction.paid)
        {
            return false;
        }
        Product product = db.getProduct(productID);
        if (product == null)
        {
            return false;
        }
        if (!product.getAvailable())
        {
            return false;
        }
        transaction.addSale(product, price);
        product.available = false;
        db.putProduct(product, 0);
        return (db.putTransaction(transaction) > 0);
    }

    /**
     * @param productID     The ID of the product to add to the transaction
     * @param transactionID The ID of the transaction to be added to
     * @param dueDate       the date the Rental will be due
     * @return true on success, false otherwise
     */
    public boolean addRental(int transactionID, int productID, String dueDate, int daysRented)
    {
        Transaction transaction = db.getTransaction(transactionID);
        if (transaction == null)
        {
            return false;
        }
        Product product = db.getProduct(productID);
        if (transaction.paid)
        {
            return false;
        }
        if (product == null)
        {
            return false;
        }
        if (product.getAvailable())
        {
            return false;
        }
        Rental rental = new Rental(product, dueDate, daysRented);
        transaction.addRental(rental);

        product.available = false;
        db.putProduct(product, 0);
        return (db.putTransaction(transaction) > 0);

    }

    /**
     * @param title the title of the product you are looking for
     * @param type  the type of the product you are looking for
     * @return the products found with the given title and type
     */
    public ArrayList<Product> findProducts(String title, String type)
    {
        return db.findProducts(title, type);
    }

    public Transaction getTransaction(int tid)
    {
        return db.getTransaction(tid);
    }

    public String getTransactionStatement(int tid)
    {
        Transaction transaction = db.getTransaction(tid);
        if (transaction == null)
        {
            return null;
        }
        return transaction.getStatement();
    }


    public boolean createRentalPricingStrategy(double standardRentalCharge, int standardRentalLength, double dailyOverdueCharge, String name)
    {
        RentalPricingStrategy pricing = new RentalPricingStrategy(standardRentalCharge, standardRentalLength, dailyOverdueCharge, name);
        return db.addRentalPricingStrategy(pricing);
    }

    public boolean payForTransaction(int tid)
    {
        Transaction transaction = db.getTransaction(tid);
        if (transaction == null)
        {
            return false;
        }
        transaction.pay();
        db.putTransaction(transaction);
        return transaction.paid;
    }

    public boolean createFrequentCustomerStrategy(int fixedPoints, int pointsPerDay, String name)
    {
        FrequentCustomerStrategy customerStrategy = new FrequentCustomerStrategy(fixedPoints, pointsPerDay, name);
        return db.addFrequentCustomerStrategy(customerStrategy);
    }

    public boolean setFrequentCustomerStrategy(String strategyName, int productID)
    {
        Product p = db.getProduct(productID);
        if (p == null)
        {
            return false;
        }
        FrequentCustomerStrategy strategy = db.getFrequentCustomerStrategy(strategyName);
        if (strategy == null)
        {
            return false;
        }
        p.setCustomerStrategy(strategy);
        return db.putProduct(p, 0);
    }

    public boolean setRentalPricingStrategy(String strategyName, int productID)
    {
        Product p = db.getProduct(productID);
        if (p == null)
        {
            return false;
        }
        RentalPricingStrategy strategy = db.getRentalPricingStrategy(strategyName);
        if (strategy == null)
        {
            return false;
        }
        p.setRentalPricingStrategy(strategy);
        return db.putProduct(p, 0);
    }

    public ArrayList<Product> getCustomerRentalHistory(int cid)
    {
        Customer c = db.getCustomer(cid);
        if (c == null)
        {
            return null;
        }
        return c.getRentalHistory();
    }

    public boolean returnProduct(int pid)
    {
        Product p = db.getProduct(pid);
        if (p == null)
        {
            return false;
        }
        p.returnToStore();
        return db.putProduct(p, 0);
    }

}
