package MediaRental;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import MediaRental.Model.Customer;
import MediaRental.Model.Product;
import MediaRental.Model.Transaction;

public class DatabaseSupport

{
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/movie_rental";
    public static final String USER = "root";
    public static final String PASSWORD = "pass";
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static Connection conn;
    
    public DatabaseSupport(){
        try {
            Class.forName(DB_DRIVER);
        }
        catch (Exception E){
            System.err.println("Unable to load driver.");
            E.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        }
        catch (SQLException E){
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState: " + E.getSQLState());
            System.out.println("VendorError: " + E.getErrorCode());
        }    
    }
    
    /**
     * 
     * @param name - can be null
     * @param address - can be null
     * @return customers that have a name or address similar to the passed in values
     */
    public ArrayList<Customer> findCustomers(String name, String address){
        String statement = "Select id, name, address from Customer";
        String whereClause = "";
        ArrayList<Customer> customers = new ArrayList();
        if(name != null && !name.isEmpty()){
            whereClause += "where name like %'" + name + "%'";
            if(address != null && !address.isEmpty()){
                whereClause += "and address like %'" + address + "%'";               
            }
        }
        else if(address != null && !address.isEmpty()){
            whereClause += "where address like %'" + address + "%'";
        }
        statement += whereClause + ";";
        try {
            Statement stmt1 = conn.createStatement ();
            ResultSet rs1 = stmt1.executeQuery (statement);
            while(rs1.next()){
                int id = rs1.getInt("id");
                String n = rs1.getString("name"); 
                String a = rs1.getString("address");
                Customer cust = new Customer(name, address);
                cust.setId(id);
                customers.add(cust);
            }    
        }
        catch (SQLException E){
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState: " + E.getSQLState());
            System.out.println("VendorError: " + E.getErrorCode());
        }
        return customers;
        
    }
    
    /**
     * Get a customer from the database by id
     * @param id
     * @return a customer object
     */
    public Customer getCustomer(int id){
        String statement = "Select name, address from Customer where id = " + id + ";";
        try {
            Statement stmt1 = conn.createStatement ();
            ResultSet rs1 = stmt1.executeQuery (statement);
            rs1.next();
            String name = rs1.getString (1); 
            String address = rs1.getString (2);
            return new Customer(name, address);
        }
        catch (SQLException E){
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState: " + E.getSQLState());
            System.out.println("VendorError: " + E.getErrorCode());
            return null;
        } 
    }
    
    /**
     * Remove a customer from the db
     * @param id
     */
    public void removeCustomer(int id){
        String statement = "delete from Customer where id = " + id + ";";
        try {
            Statement stmt1 = conn.createStatement ();
            stmt1.execute(statement);
        }
        catch (SQLException E){
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState: " + E.getSQLState());
            System.out.println("VendorError: " + E.getErrorCode());
        } 
    }
    
    /**
     * Add a customer to the database
     * @param customer object to add (id should not be set yet)
     * @return id of customer added
     */
    public int addCustomer(Customer customer){
        String statement = "INSERT INTO Customer (Name, Address)" +
        		" VALUES ('" + customer.getName() +"', '" + customer.getAddress() + "');";
        try {
            PreparedStatement stmt1 = conn.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
            stmt1.executeUpdate();
            ResultSet rs = stmt1.getGeneratedKeys();
            if (rs.next()){
                int id = rs.getInt(1);
                customer.setId(id);
                return (id);
            }

        }
        catch (SQLException E){
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState: " + E.getSQLState());
            System.out.println("VendorError: " + E.getErrorCode());
        } 
        return 0;
    }
    
    /**
     * Used when creating a completely new product in the store
     * @param product - should have a title and a type but no id
     * @return new id of product
     */
    public int addProductToCatalog(Product product){
        String statement = "INSERT INTO ProductCatalog (title, genre)" +
                " VALUES ('" + product.getTitle() + "', '" + product.getType() + "');";
        try {
            PreparedStatement stmt1 = conn.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
            stmt1.executeUpdate();
            ResultSet rs = stmt1.getGeneratedKeys();
            if (rs.next()){
                int id = rs.getInt(1);
                product.setId(id);
                return id;
            }

        }
        catch (SQLException E){
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState: " + E.getSQLState());
            System.out.println("VendorError: " + E.getErrorCode());
        } 
        return 0;
    }
    
    /**
     * Add a product to the database.
     * @param catalog_id: Id of the catalog item
     * @return - id of the product from the product db
     */
    public int addProductToStore(int catalog_id){
        String statement = "INSERT INTO Product (productCatalogID) VALUES (" + catalog_id + ");";
        try {
            PreparedStatement stmt1 = conn.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
            stmt1.executeUpdate();
            ResultSet rs = stmt1.getGeneratedKeys();
            if (rs.next()){
                int id = rs.getInt(1);      
                return id;
            }

        }
        catch (SQLException E){
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState: " + E.getSQLState());
            System.out.println("VendorError: " + E.getErrorCode());
        } 
        return 0;
    }
    
    /**
     * Add a transaction to the database
     * @param transaction
     * @return id of transaction
     */
    public int addTransactionToStore(Transaction transaction){
        

        String statement = "INSERT INTO Transaction (customerID, paid) " +
        		"VALUES (" + transaction.getCustomer().getId() + ", 0);";
        try {
            PreparedStatement stmt1 = conn.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
            stmt1.executeUpdate();
            ResultSet rs = stmt1.getGeneratedKeys();
            if (rs.next()){
                int id = rs.getInt(1);
                transaction.setId(id);
                return id;
            }

        }
        catch (SQLException E){
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState: " + E.getSQLState());
            System.out.println("VendorError: " + E.getErrorCode());
        }
        return 0;
    }
    
    /**
     * Add a product to a transaction in the db
     * @param product_id The ID of the product to add to the transaction.
     * @param duedate - format YYYY-MM-DD
     */
    public void addProductToTransaction(int product_id, String duedate, Transaction transaction){
        String statement = "UPDATE Product SET transactionID=" + transaction.getId() + 
                           ", dueDate=" + duedate + " WHERE id=" + product_id;
        try {
            Statement stmt1 = conn.createStatement();
            stmt1.executeUpdate(statement);
        }
        catch (SQLException E){
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState: " + E.getSQLState());
            System.out.println("VendorError: " + E.getErrorCode());
        } 
    }
    
    /**
     * Create db tables. Only run this if initializing db for the very first time
     * @return boolean indicating success
     */
    public boolean createTables(){
        String statement = "CREATE TABLE Customer ( " +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "Name VARCHAR(45) NULL, " +
                "Address VARCHAR(120) NULL, " +
                "PRIMARY KEY (id)); ";
        
        String statement2 = "CREATE TABLE ProductCatalog ( " +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "title VARCHAR(45) NULL, " +
                "description VARCHAR(120) NULL, " +
                "genre VARCHAR(45) NULL, " +
                "PRIMARY KEY (id)); ";
        
        String statement3 = "CREATE TABLE Product ( " +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "productCatalogID INT NOT NULL, " +
                "transactionID INT NULL, " +
                "dueDate DATE NULL, " +
                "PRIMARY KEY (id), " +
                "FOREIGN KEY (productCatalogID) REFERENCES ProductCatalog(id));";
        
        String statement4 = "CREATE TABLE Transaction ( " +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "customerID INT NOT NULL, " +
                "statement VARCHAR(2000) NULL, " +
                "paid TINYINT(1) NULL DEFAULT 0, " +
                "PRIMARY KEY (id), " +
                "FOREIGN KEY (customerID) REFERENCES Customer(id)); ";
        try {
            Statement stmt = conn.createStatement ();
            stmt.execute(statement);
            stmt.execute(statement2);
            stmt.execute(statement3);
            stmt.execute(statement4);
            stmt.close();
            return true;
        }
        catch (SQLException E){
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState: " + E.getSQLState());
            System.out.println("VendorError: " + E.getErrorCode());
            return false;
        }
        
    }

}