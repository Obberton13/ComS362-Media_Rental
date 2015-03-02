package MediaRental;

import java.util.HashMap;
import java.util.Scanner;

import MediaRental.Controller.*;
import MediaRental.Model.Product;

/**
 * Created by Obberton13 on 2/20/2015.
 */
public class mainClass
{
	public static void main(String args[])
	{
		//this is the main loop for the program.
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext())
		{
			String in = sc.next().toLowerCase();
			char c = in.charAt(0);
			switch (c)
			{
				case 'q':
					break;
				case 'a':
					Create(sc);
					break;
				case 'e':
					Edit(sc);
					break;
				case 'i':
					Index(sc);
					break;
				case 'd':
					Delete(sc);
					break;
				case 'c':
					BaseCommands();
					break;
				default:
					System.out.println("Invalid command: " + in);
			}
			if (c == 'q')
			{
				break;
			}
		}
		System.out.println("G'bye!");
		sc.close();
	}

	private static void Create(Scanner in)
	{
		System.out.println("<c|p|t|q>\n\n" +
				"c adds a customer\n" +
				"p adds a product\n" +
				"t adds a transaction\n" +
				"q returns to the previous menu");
		String input = in.next();
		switch (input.charAt(0))//creates a customer
		{
			case 'c':
				System.out.println("Adding a Customer.\nCustomer name: ");
				String name = in.nextLine();
				System.out.println("Customer Address: ");
				String address = in.nextLine();
				StoreController.AddCustomer(name, address);
				break;
			case 'p':
				System.out.println("Product Title: ");
				String title = in.nextLine();
				System.out.println("Product Type: ");
				String type = in.nextLine();
				StoreController.CreateProduct(title, type);
				break;
			case 't':
				System.out.println("Customer ID: ");
				int id = Integer.parseInt(in.next());
				StoreController.CreateTransaction(id);
				break;
			case 'q':
				return;
			default:
				System.out.println("Invalid thing to add: " + input);
				break;
		}
	}

	private static void Edit(Scanner in)
	{
		System.out.println("Editing items is not yet supported");
		in.next();
	}

	private static void Index(Scanner in)
	{
		String input = in.next();
		switch (input.charAt(0))
		{
			case 'p':
				System.out.println("Indexing Products: \n");
				System.out.println("Title (Leave blank to get all): ");
				String title = in.nextLine();
				System.out.println("Type (Leave blank to get all): ");
				String type = in.nextLine();
				for (Product p : StoreController.FindProduct(title, type))
				{
					System.out.println(p);
				}
				break;
			case 'c':
				System.out.println("p");
				break;
			default:
				System.out.println("Invalid thing to index: " + input);
		}
	}

	private static void Delete(Scanner in)
	{
		System.out.println("Deletion is not yet available.");
		in.next();
	}

	private static void BaseCommands()
	{
		System.out.println("Commands:");
		System.out.println("a (add)\ne (edit)\ni (index)\nd (delete)");
	}
}
