package MediaRental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import MediaRental.Controller.*;
import MediaRental.Model.Product;

/**
 * Created by Obberton13 on 2/20/2015.
 */
public class mainClass {
	public static void main(String args[])
	{
		System.out.println("Hey, it did something!");
		Scanner sc = new Scanner(System.in);
		DatabaseSupport.createTables();

		//this is the main loop for the program.
		while(sc.hasNext())
		{
			String input = sc.nextLine().toLowerCase();
			String[] in = input.split(" ");
			for(String s : in)
			{
				System.out.println(s);
			}
			if(in[0].startsWith("q")) break;
			if(in[0].startsWith("a")) Create(in);
			if(in[0].startsWith("e")) Edit(in);
			if(in[0].startsWith("i")) Index(in);
			if(in[0].startsWith("d")) Delete(in);
			if(in[0].startsWith("c")) BaseCommands();
		}
		System.out.println("G'bye!");
		sc.close();
	}
	private static void Create(String[] in)
	{
		if(in.length<2||in[1].equals("commands"))
		{
			System.out.println("<c|p|t>\n\n" +
					"c adds a customer\n" +
					"p adds a product\n" +
					"t adds a transaction");
			return;
		}
		if(in[1].startsWith("c"))//creates a customer
		{
			if(in.length<4)
			{
				System.out.println("Usage: add c \"<name>\" \"<address>\"");
				return;
			}
			System.out.println(StoreController.AddCustomer(in[2], in[3]));
		}
		if(in[1].startsWith("p"))
		{
			if(in.length<4)
			{
				System.out.println("Usage: add p <Title> <Type>");
				return;
			}
			StoreController.CreateProduct(in[2], in[3]);
		}
		if(in[1].startsWith("t"))
		{
			if(in.length<4)
			{
				System.out.println("Usage: add t <Customer ID>");
				return;
			}
			StoreController.CreateTransaction(Integer.parseInt(in[2]));
		}
	}

	private static void Edit(String[] in)
	{
		System.out.println("Editing items is not yet supported");
	}

	private static void Index(String[] in)
	{
		if(in.length<2||in[1].equals("commands"))
		{
			System.out.println("p (product)");
			return;
		}
		if(in[1].startsWith("p"))
		{
			ArrayList<Product> products = StoreController.FindProduct(new HashMap<String, String>());
			for(Product p : products)
			{
				System.out.println(p.getTitle() + " " + p.getType() + " " + p.getQuantity());
			}
		}
	}

	private static void Delete(String[] in)
	{

	}

	private static void BaseCommands()
	{
		System.out.println("Commands:");
		System.out.println("a (add)\ne (edit)\ni (index)\nd (delete)");
	}
}
