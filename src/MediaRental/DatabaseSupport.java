package MediaRental;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import MediaRental.Model.Customer;

public class DatabaseSupport

{
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/movieRental";
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
        
    
    public boolean createTables(){
        String statement = "CREATE TABLE movieRental.Customer (" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "Name VARCHAR(45) NULL," +
                "Address VARCHAR(120) NULL," +
                "PRIMARY KEY (id));";
        
        String statement2 = "CREATE TABLE movieRental.ProductCatalog (" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "title VARCHAR(45) NULL," +
                "description VARCHAR(120) NULL," +
                "genre VARCHAR(45) NULL," +
                "PRIMARY KEY (id));";
        
        String statement3 = "CREATE TABLE movieRental.Product (" +
                "id INT NOT NULL," +
                "productCatalogID INT NOT NULL," +
                "transactionID INT NULL," +
                "PRIMARY KEY (id)," +
                "FOREIGN KEY (productCatalogID) REFERENCES ProductCatalog(id));";
        
        String statement4 = "CREATE TABLE movieRental.Transaction (" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "customerID INT NOT NULL," +
                "statement VARCHAR(2000) NULL," +
                "paid TINYINT(1) NULL DEFAULT 0," +
                "PRIMARY KEY (id)," +
                "FOREIGN KEY (customerID) REFERENCES Customer(id));";
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