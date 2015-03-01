package MediaRental;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import MediaRental.Controller.*;
import MediaRental.Model.Product;

/**
 * Created by Obberton13 on 2/20/2015.
 */
public class mainClass {
	private static Pattern isInt = Pattern.compile("/^([0-9]+)$/*");
	private static Pattern isDate = Pattern.compile("/^([0-9]{4}-[0-9]{2}-[0-9]{2})$/");
	public static void main(String args[])
	{
		//this is the main loop for the program.
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
		{
			String in = sc.next().toLowerCase();
			char c = in.charAt(0);
			switch(c)
			{
				case 'q': break;
				case 'a': add(sc); break;
				case 'e': edit(sc); break;
				case 'i': index(sc); break;
				case 'd': delete(sc); break;
				case 'c':
					switch(in.charAt(1))
					{
						case 'r': create(sc); break;
						default: baseCommands(); break;
					}
					break;
				default: System.out.println("Invalid command: " + in); break;
			}
			if(c=='q') break;
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
		String input = in.next();
		switch(input.charAt(0))//creates a customer
		{
			case 'c':
				System.out.println("Creating a Customer.\n\nCustomer name: ");
				String name = in.nextLine();
				System.out.println("Customer Address: ");
				String address = in.nextLine();
				StoreController.AddCustomer(name, address);
				break;
			case 'p':
				System.out.println("Creating a product: \n");
				System.out.println("Product Title: ");
				String title = in.nextLine();
				System.out.println("Product Type: ");
				String type = in.nextLine();
				StoreController.CreateProduct(title, type);
				break;
			case 't':
				System.out.println("Creating a transaction: ");
				System.out.println("Customer ID: ");
				int id;
				Matcher m = isInt.matcher(in.next());
				if(m.matches())
					id = Integer.parseInt(m.group(1));
				else
				{
					System.out.println("Next time, try typing an integer as an ID.");
					return;
				}
				System.out.println(StoreController.CreateTransaction(id));
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
		String input = in.next();
		switch(input.charAt(0)) {
			case 'p':
				System.out.println("Adding a product to the store");
				System.out.println("Product ID: ");
				int pid;
				Matcher m = isInt.matcher(in.next());
				if (m.matches())
					pid = Integer.parseInt(m.group(1));
				else {
					System.out.println("Next time, try typing an integer as an ID.");
					return;
				}
				System.out.println("Quantity: ");
				int qty;
				m = isInt.matcher(in.next());
				if (m.matches())
					qty = Integer.parseInt(m.group(1));
				else {
					System.out.println("Next time, try typing an integer as a quantity.");
					return;
				}
				StoreController.AddProduct(pid, qty);
				break;
			case 'r':
				System.out.println("Adding rental to transaction");
				System.out.println("Product ID: ");
				m = isInt.matcher(in.next());
				if(m.matches())
					pid = Integer.parseInt(m.group(1));
				else
				{
					System.out.println("Next time, try typing an integer as an ID.");
					return;
				}
				System.out.println("Transaction ID: ");
				int tid;
				m = isInt.matcher(in.next());
				if(m.matches())
					tid = Integer.parseInt(m.group(1));
				else
				{
					System.out.println("Next time, try typing an integer as an ID.");
					return;
				}
				String date;
				m = isDate.matcher(in.next());
				if(m.matches())
				{
					date = m.group(1);
				}
				else
				{
					System.out.println("Make sure your date is in the format YYYY-MM-DD");
					return;
				}

		}
	}

	private static void edit(Scanner in)
	{
		System.out.println("Editing items is not yet supported");
		in.nextLine();
	}

	private static void index(Scanner in)
	{
		String input = in.next();
		switch(input.charAt(0))
		{
			case 'p':
				HashMap<String, String> args = new HashMap<String, String>();
				System.out.println("Title (leave blank for all): ");
				String title = in.nextLine().trim();
				if(!title.isEmpty())
					args.put("title", title);
				System.out.println("Type (leave blank for all): ");
				String type = in.nextLine().trim();
				if(!title.isEmpty())
					args.put("type", type);
				for(Product p : StoreController.FindProduct(args))
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
		System.out.println("Deletion is not yet available.");
		in.next();
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
