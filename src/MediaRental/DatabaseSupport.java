package MediaRental;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
    
    
    public static boolean createTables(){
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