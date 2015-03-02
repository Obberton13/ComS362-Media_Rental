package MediaRental;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import MediaRental.Model.Customer;
import MediaRental.Model.Product;
import MediaRental.Model.Rental;
import MediaRental.Model.Sale;
import MediaRental.Model.Transaction;

public class DatabaseSupport
{
	public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/movie_rental";
	public static final String USER = "root";
	public static final String PASSWORD = "pass";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";

	private Connection getConnection() throws SQLException
	{
		try
		{
			Class.forName(DB_DRIVER);
		} catch (Exception E)
		{
			System.err.println("Unable to load driver.");
			E.printStackTrace();
		}
		return DriverManager.getConnection(DB_URL, USER, PASSWORD);
	}

	public boolean putCustomer(Customer c)
	{
		if (this.getCustomer(c.getId()) == null)
		{
			return writeCustomer(c);
		} else if (c.getTransactions().size() > 0)
		{
			return addTransaction(c);
		} else
		{
			return false;
		}
	}

	public boolean writeCustomer(Customer c)
	{
		try
		{
			Connection conn = this.getConnection();
			String query = "INSERT INTO customer(id, name, address) VALUES (" + c.getId() + ", '" + c.getName() + "', '" + c.getAddress() + "')";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			for (Transaction t : c.getTransactions())
			{
				query = "INSERT INTO transaction(customer_id, id) VALUES (" + c.getId() + ", " + t.getId() + ")";
				stmt.executeUpdate(query);
				for (Rental r : t.getRentals())
				{
					query = "INSERT INTO rental(id, product_id, price, due_date, transaction_id) VALUES(" +
							r.getId() + ", " + r.getProduct().getId() + ", " + r.getPrice() + ", '" + r.getDueDate() + "', " + t.getId()
							+ ")";
					stmt.executeUpdate(query);
				}
				for (Sale s : t.getSales())
				{
					query = "INSERT INTO rental(id, product_id, price, transaction_id) VALUES(" +
							s.getId() + ", " + s.getProduct().getId() + ", " + s.getPrice() + ", " + t.getId() + ")";
					stmt.executeUpdate(query);
				}
			}
			stmt.close();
			conn.commit();
			conn.close();
			return true;
		} catch (SQLException E)
		{
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState: " + E.getSQLState());
			System.out.println("VendorError: " + E.getErrorCode());
			return false;
		}
	}

	private boolean addTransaction(Customer c)
	{
		try
		{
			String query = "INSERT INTO transaction(id, customer_id) VALUES(" +
					c.getTransactions().get(c.getTransactions().size() - 1).getId()
					+ ", " + c.getId() + ")";
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			Transaction t = c.getTransactions().get(c.getTransactions().size() - 1);
			for (Rental r : t.getRentals())
			{
				query = "INSERT INTO rental(id, product_id, price, due_date, transaction_id) VALUES (" +
						r.getId() + ", " + r.getProduct().getId() + ", " + r.getPrice() + ", " + r.getDueDate() + ", " + t.getId() + ")";
				stmt.executeUpdate(query);
			}
			for(Sale s : t.getSales())
			{
				query = "INSERT INTO sale(id, product_id, price, transaction_id) VALUES (" +
						s.getId() + ", " + s.getProduct().getId() + ", " + s.getPrice() + ", " + t.getId() + ")";
				stmt.executeUpdate(query);
			}
			stmt.close();
			conn.commit();
			conn.close();
			return true;
		} catch (SQLException E)
		{
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState: " + E.getSQLState());
			System.out.println("VendorError: " + E.getErrorCode());
			return false;
		}
	}

	public Customer getCustomer(int id)
	{
		String statement = "SELECT * FROM customer WHERE id = " + id + ";";
		try
		{
			Connection conn = this.getConnection();
			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(statement);
			Customer customer = null;
			if (rs1.next())
			{
				String name = rs1.getString("name");
				String address = rs1.getString("address");
				ResultSet rst = stmt1.executeQuery("SELECT * FROM transaction WHERE customer_id = " + id);
				ArrayList<Transaction> transactions = new ArrayList<Transaction>();
				while(rst.next())
				{
					int tid = rst.getInt("id");
					transactions.add(this.getTransaction(tid));
				}
				customer = new Customer(name, address, id, transactions);
			}
			stmt1.close();
			rs1.close();
			conn.close();

			return customer;
		} catch (SQLException E)
		{
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState: " + E.getSQLState());
			System.out.println("VendorError: " + E.getErrorCode());
			return null;
		}
	}

	public Transaction getTransaction(int tid)
	{
		try
		{
			Connection conn = this.getConnection();
			Statement stmt1 = conn.createStatement();
			ResultSet rss = stmt1.executeQuery("SELECT * FROM sale WHERE transaction_id = " + tid);
			ResultSet rsr = stmt1.executeQuery("SELECT * FROM rental WHERE transaction_id = " + tid);
			ArrayList<Rental> rentals = new ArrayList<Rental>();
			ArrayList<Sale> sales = new ArrayList<Sale>();
			while (rsr.next())
			{
				int rid = rsr.getInt("id");
				rentals.add(this.getRental(rid));
			}
			while (rss.next())
			{
				int sid = rss.getInt("id");
				sales.add(this.getSale(sid));
			}
			return new Transaction(tid, sales, rentals);
		}catch (SQLException E)
		{
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState: " + E.getSQLState());
			System.out.println("VendorError: " + E.getErrorCode());
			return null;
		}
	}

	public Rental getRental(int rid)
	{
		Rental rental = null;
		try
		{
			Connection conn = this.getConnection();
			Statement stmt1 = conn.createStatement();
			ResultSet rsr = stmt1.executeQuery("SELECT * FROM rental WHERE transaction_id = " + rid);
			if(rsr.next())
			{
				String due_date = rsr.getString("due_date");
				double price = rsr.getDouble("price");
				Product product = this.getProduct(rsr.getInt("product_id"));
				rental = new Rental(product, due_date, price, rid);
			}
			conn.close();
			stmt1.close();
			rsr.close();
		}catch (SQLException E)
		{
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState: " + E.getSQLState());
			System.out.println("VendorError: " + E.getErrorCode());
			return null;
		}
		return rental;
	}

	public Sale getSale(int sid)
	{

		Sale sale = null;
		try
		{
			Connection conn = this.getConnection();
			Statement stmt1 = conn.createStatement();
			ResultSet rsr = stmt1.executeQuery("SELECT * FROM sale WHERE transaction_id = " + sid);
			if(rsr.next())
			{
				double price = rsr.getDouble("price");
				Product product = this.getProduct(rsr.getInt("product_id"));
				sale = new Sale(product, price, sid);
			}
			conn.close();
			stmt1.close();
			rsr.close();
		}catch (SQLException E)
		{
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState: " + E.getSQLState());
			System.out.println("VendorError: " + E.getErrorCode());
			return null;
		}
		return sale;
	}

//======================================================================================================================

	/**
	 * @param title - can be null
	 * @param genre - can be null
	 * @return customers that have a name or address similar to the passed in values
	 */
	public ArrayList<Product> findProducts(String title, String genre)
	{
		String statement = "Select id, title, genre from ProductCatalog";
		String whereClause = "";
		ArrayList<Product> products = new ArrayList<Product>();
		if (title != null && !title.isEmpty())
		{
			whereClause += "where title like %'" + title + "%'";
			if (genre != null && !genre.isEmpty())
			{
				whereClause += "and genre like %'" + genre + "%'";
			}
		} else if (genre != null && !genre.isEmpty())
		{
			whereClause += "where genre like %'" + genre + "%'";
		}
		statement += whereClause + ";";
		try
		{
			Connection conn = this.getConnection();
			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(statement);
			while (rs1.next())
			{
				int id = rs1.getInt("id");
				title = rs1.getString("title");
				String type = rs1.getString("type");
				genre = rs1.getString("genre");
				Product prod = new Product(title, type, genre, id);
				products.add(prod);
			}
			rs1.close();
			stmt1.close();
			conn.close();
		} catch (SQLException E)
		{
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState: " + E.getSQLState());
			System.out.println("VendorError: " + E.getErrorCode());
		}
		return products;

	}

	public Product getProduct(int pid)
	{
		String statement = "SELECT * FROM product_catalog WHERE id = " + pid;
		Product product = null;
		try
		{
			Connection conn = this.getConnection();
			Statement stmt1 = conn.createStatement();
			ResultSet rs = stmt1.executeQuery(statement);
			if (rs.next())
			{
				String title = rs.getString("title");
				String type = rs.getString("type");
				String genre = rs.getString("genre");
				int id = rs.getInt("id");
				product = new Product(title, type, genre, id);
			}
			rs.close();
			stmt1.close();
			conn.close();
		} catch (SQLException E)
		{
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState: " + E.getSQLState());
			System.out.println("VendorError: " + E.getErrorCode());
			return null;
		}
		return product;
	}

	/**
	 * Remove a customer from the db
	 *
	 * @param id
	 */
	public boolean removeCustomer(int id)
	{
		String statement = "DELETE FROM customer WHERE id = " + id;
		try
		{
			Connection conn = getConnection();
			Statement stmt1 = conn.createStatement();
			stmt1.execute(statement);
			stmt1.close();
			conn.close();
		} catch (SQLException E)
		{
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState: " + E.getSQLState());
			System.out.println("VendorError: " + E.getErrorCode());
			return false;
		}
		return true;
	}

	/**
	 * Add a customer to the database
	 *
	 * @param customer object to add (id should not be set yet)
	 * @return id of customer added
	 */
	public boolean addCustomer(Customer customer)
	{
		String statement = "INSERT INTO customer (id, name, address)" +
				" VALUES (" + customer.getId() + "'" + customer.getName() + "', '" + customer.getAddress() + "');";
		try
		{
			Connection conn = this.getConnection();
			PreparedStatement stmt1 = conn.prepareStatement(statement);
			stmt1.executeUpdate();
			stmt1.close();
			conn.close();
		} catch (SQLException E)
		{
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState: " + E.getSQLState());
			System.out.println("VendorError: " + E.getErrorCode());
			return false;
		}
		return true;
	}

	/**
	 * Used when creating a completely new product in the store
	 *
	 * @param product - should have a title and a type but no id
	 * @return new id of product
	 */
	public boolean addProductToCatalog(Product product)
	{
		String statement = "INSERT INTO product_catalog (title, genre)" +
				" VALUES ('" + product.getTitle() + "', '" + product.getType() + "');";
		try
		{
			Connection conn = this.getConnection();
			PreparedStatement stmt1 = conn.prepareStatement(statement);
			stmt1.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException E)
		{
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState: " + E.getSQLState());
			System.out.println("VendorError: " + E.getErrorCode());
		}
		return false;
	}

	/**
	 * Add a product to the database.
	 *
	 * @param catalog_id: Id of the catalog item
	 * @return - id of the product from the product db
	 */
	public boolean addProductToStore(int catalog_id, int qty)
	{
		String statement = "INSERT INTO product (product_catalog_id) VALUES (" + catalog_id + ");";
		try
		{
			Connection conn = this.getConnection();
			PreparedStatement stmt1 = conn.prepareStatement(statement);
			for (int i = 0; i < qty; i++)
			{
				stmt1.executeUpdate();
			}
			stmt1.close();
			conn.close();
			return true;
		} catch (SQLException E)
		{
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState: " + E.getSQLState());
			System.out.println("VendorError: " + E.getErrorCode());
		}
		return false;
	}

	/**
	 * Add a product to a transaction in the db
	 *
	 * @param rental_id   The ID of the product to add to the transaction.
	 * @param duedate     - format YYYY-MM-DD
	 * @param transaction The transaction to be added to
	 */
	public boolean addRentalToTransaction(int rental_id, String duedate, Transaction transaction)
	{
		String statement = "UPDATE Rental SET transaction_id=" + transaction.getId() +
				", due_date=" + duedate + " WHERE id=" + rental_id;
		try
		{
			Connection conn = this.getConnection();
			Statement stmt1 = conn.createStatement();
			stmt1.executeUpdate(statement);
			stmt1.close();
			conn.close();
			return true;
		} catch (SQLException E)
		{
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState: " + E.getSQLState());
			System.out.println("VendorError: " + E.getErrorCode());
		}
		return false;
	}

	public boolean addSaleToTransaction(int sale_id, Transaction transaction)
	{
		String statement = "UPDATE sale SET transaction_id=" + transaction.getId() + " WHERE id=" + sale_id;
		try
		{
			Connection conn = this.getConnection();
			Statement stmt1 = conn.createStatement();
			stmt1.executeUpdate(statement);
			stmt1.close();
			conn.close();
			return true;
		} catch (SQLException E)
		{
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState: " + E.getSQLState());
			System.out.println("VendorError: " + E.getErrorCode());
		}
		return false;
	}

	/**
	 * Create db tables. Only run this if initializing db for the very first time
	 *
	 * @return boolean indicating success
	 */
	public boolean createTables()
	{
		String[] statements = new String[12];
		statements[0] = "DROP TABLE rental";
		statements[1] = "DROP TABLE sale";
		statements[2] = "DROP TABLE transaction";
		statements[3] = "DROP TABLE product";
		statements[4] = "DROP TABLE product_catalog";
		statements[5] = "DROP TABLE customer";
		statements[6] = "CREATE TABLE customer ( " +
				"id INT NOT NULL, " +
				"name VARCHAR(45) NULL, " +
				"address VARCHAR(120) NULL, " +
				"PRIMARY KEY (id)); ";

		statements[7] = "CREATE TABLE product_catalog ( " +
				"id INT NOT NULL, " +
				"title VARCHAR(45) NULL, " +
				"genre VARCHAR(45) NULL, " +
				"PRIMARY KEY (id)); ";

		statements[8] = "CREATE TABLE product ( " +
				"id INT NOT NULL AUTO_INCREMENT, " +
				"product_catalog_id INT NOT NULL, " +
				"PRIMARY KEY (id), " +
				"FOREIGN KEY (productCatalogID) REFERENCES product_catalog(id) ON DELETE CASCADE);";

		statements[9] = "CREATE TABLE transaction ( " +
				"id INT NOT NULL, " +
				"customer_id INT NOT NULL, " +
				"PRIMARY KEY (id), " +
				"FOREIGN KEY (customerID) REFERENCES customer(id) ON DELETE CASCADE); ";

		statements[10] = "CREATE TABLE sale (" +
				"id INT NOT NULL, " +
				"product_id INT NOT NULL, " +
				"price FLOAT 0.0, " +
				"transaction_id INT NULL, " +
				"PRIMARY KEY (id), " +
				"FOREIGN KEY (transactionID) REFERENCES transaction(id) ON DELETE CASCADE, " +
				"FOREIGN KEY (productID) REFERENCES product(id) ON DELETE CASCADE);";

		statements[12] = "CREATE TABLE rental (" +
				"id INT NOT NULL, " +
				"product_id INT NOT NULL," +
				"price FLOAT 0.0, " +
				"due_date DATE NULL, " +
				"transaction_id INT NULL, " +
				"PRIMARY KEY (id), " +
				"FOREIGN KEY (transactionID) REFERENCES transaction(id) ON DELETE CASCADE, " +
				"FOREIGN KEY (productID) REFERENCES product(id) ON DELETE CASCADE);";
		boolean toReturn = true;
		for (String stmt : statements)
		{
			if (!update(stmt))
			{
				toReturn = false;
			}
		}
		return toReturn;
	}

	private boolean update(String sql)
	{
		try
		{
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
			conn.close();
			return true;
		} catch (SQLException E)
		{
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState: " + E.getSQLState());
			System.out.println("VendorError: " + E.getErrorCode());
		}
		return false;
	}
}