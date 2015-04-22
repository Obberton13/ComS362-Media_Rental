package MediaRental;

import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import MediaRental.StoreController;

/**
 * Created by Obberton13 on 2/20/2015.
 */
public class mainClass
{
	private static Pattern isInt = Pattern.compile("/^([0-9]+)$/");
	private static Pattern isDate = Pattern.compile("/^([0-9]{4}-[0-9]{2}-[0-9]{2})$/");
	private static Pattern isDouble = Pattern.compile("/^([0-9\\.]+)$/");

	public static void main(String args[])
	{
		//this is the main loop for the program.
//	    DatabaseSupport db = new DatabaseSupport();
//	    db.createTables();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext())
		{
			String in = sc.nextLine().toLowerCase();
			char c = in.charAt(0);
			switch (c)
			{
				case 'q':
					return;
				case 'a':
					add(sc);
					break;
				case 'e':
					edit(sc);
					break;
				case 'i':
					index(sc);
					break;
				case 'd':
					delete(sc);
					break;
				case 'c':
					if(in.length()<2)
					{
						System.out.println("Please distinguish between co(mmands) and cr(eate)");
						break;
					}
					switch (in.charAt(1))
					{
						case 'r':
							create(sc);
							break;
						default:
							baseCommands();
							break;
					}
					break;
				default:
					System.out.println("Invalid command: " + in);
					break;
			}
		}
		System.out.println("G'bye!");
		sc.close();
	}

	private static void create(Scanner in)
	{
		System.out.println("<c|p|t|q>\n\n" +
				"c adds a customer\n" +
				"p adds a product\n" +
				"t adds a transaction\n" +
				"q returns to the previous menu");
		String input = in.nextLine();
		switch (input.charAt(0))//creates a customer
		{
			case 'c':
				System.out.println("Creating a Customer.");
				System.out.println("Customer name: ");
				//in.nextLine();
				String name = in.nextLine();
				System.out.println(name);
				System.out.println("Customer Address: ");
				String address = in.nextLine();
				System.out.println("Customer ID: ");
				boolean b = new StoreController().addCustomer(name, address);
				System.out.println("Operation success boolean is " + b);
				break;
			case 'p':
				System.out.println("Creating a product.");
				//in.nextLine();
				System.out.println("Product Title: ");
				String title = in.nextLine();
				System.out.println(title);
				System.out.println("Product Type: ");
				String type = in.nextLine();
				System.out.println("Product Genre: ");
				String genre = in.nextLine();
				System.out.println("Product ID: ");
				String description = "";
				b = new StoreController().createProduct(title, type, genre, description);
				System.out.println("Operation success boolean is " + b);
				break;
			case 't':
				System.out.println("Creating a transaction: ");
				System.out.println("Customer ID: ");
				int cid;
                Matcher m = isInt.matcher(in.nextLine());
				if (m.matches())
				{
					cid = Integer.parseInt(m.group(1));
				} else
				{
					System.out.println("Next time, try typing an integer as an ID.");
					return;
				}
				b = new StoreController().createTransaction(cid);
				System.out.println("Operation success boolean is " + b);
				break;
			case 'q':
				return;
			default:
				System.out.println("Invalid thing to add: " + input);
				break;
		}
	}

	private static void add(Scanner in)
	{
		String input = in.nextLine();
		switch (input.charAt(0))
		{
			case 'p':
				System.out.println("Adding a product to the store");
				System.out.println("Product ID: ");
				int pid;
				Matcher m = isInt.matcher(in.nextLine());
				if (m.matches())
				{
					pid = Integer.parseInt(m.group(1));
				} else
				{
					System.out.println("Next time, try typing an integer as an ID.");
					return;
				}
				System.out.println("Quantity: ");
				int qty;
				m = isInt.matcher(in.nextLine());
				if (m.matches())
				{
					qty = Integer.parseInt(m.group(1));
				} else
				{
					System.out.println("Next time, try typing an integer as a quantity.");
					return;
				}
				boolean b = new StoreController().addProduct(pid, qty);
				System.out.println("Operation success boolean is " + b);
				break;
			case 'r':
				System.out.println("Adding rental to transaction");
				System.out.println("Product ID: ");
				m = isInt.matcher(in.nextLine());
				if (m.matches())
				{
					pid = Integer.parseInt(m.group(1));
				} else
				{
					System.out.println("Next time, try typing an integer as an ID.");
					return;
				}
				System.out.println("Transaction ID: ");
				int tid;
				m = isInt.matcher(in.nextLine());
				if (m.matches())
				{
					tid = Integer.parseInt(m.group(1));
				} else
				{
					System.out.println("Next time, try typing an integer as an ID.");
					return;
				}
				System.out.println("Due Date (YYYY-MM-DD): ");
				String date;
				m = isDate.matcher(in.nextLine());
				if (m.matches())
				{
					date = m.group(1);
				} else
				{
					System.out.println("Make sure your date is in the format YYYY-MM-DD");
					return;
				}
				System.out.println("Transaction ID: ");
				int id;
				b = new StoreController().addRental(tid, pid, date);
				System.out.println("Operation success boolean is " + b);
		}
	}

	private static void edit(Scanner in)
	{
		System.out.println("Editing items is not yet supported");
		in.nextLine();
	}

	private static void index(Scanner in)
	{
		String input = in.nextLine();
		switch (input.charAt(0))
		{
			case 'p':
				System.out.println("Indexing Products: \n");
				System.out.println("Title (Leave blank to get all): ");
				String title = in.nextLine();
				System.out.println("Genre (Leave blank to get all): ");
				String genre = in.nextLine();
				for (Product p : new StoreController().findProduct(title, genre))
				{
					System.out.println(p);
				}
				break;
			case 'c':
				System.out.println("<p>\n " +
						"p indexes all products in the store.");
				break;
			default:
				System.out.println("Invalid thing to index: " + input);
		}
	}

	private static void delete(Scanner in)
	{
		String input = in.nextLine();
		switch(input.charAt(0))
		{
			case 'c':
				System.out.println("Removing customer");
				System.out.println("Customer ID: ");
				int cid;
				Matcher m = isInt.matcher(in.nextLine());
				if (m.matches())
				{
					cid = Integer.parseInt(m.group(1));
				} else
				{
					System.out.println("Next time, try typing an integer as an ID.");
					return;
				}
				boolean b = new StoreController().removeCustomer(cid);
				System.out.println("Operation success boolean is " + b);
		}
		System.out.println("Deletion is not yet available.");
		in.nextLine();
	}

	private static void baseCommands()
	{
		System.out.println("Commands:");
		System.out.println("a (add)\n" +
				"e (edit)\n" +
				"i (index)\n" +
				"d (delete)\n" +
				"c (commands)\n" +
				"cr (create)");
	}
}
