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

		//this is the main loop for the program.
		while(sc.hasNext())
		{
			String in = sc.next().toLowerCase();
			if(in.startsWith("q")) break;
			if(in.startsWith("a")) Create(sc);
			if(in.startsWith("e")) Edit(sc);
			if(in.startsWith("i")) Index(sc);
			if(in.startsWith("d")) Delete(sc);
			if(in.startsWith("c")) BaseCommands();
		}
		sc.close();
	}
	private static void Create(Scanner sc)
	{
		String in = sc.next().toLowerCase();
		if(in.equals("commands"))
		{
			System.out.println("c (customer)\np (product)\nt (transaction)");
			return;
		}
		if(in.startsWith("c")) StoreController.AddCustomer(sc.next(), sc.next());
		if(in.startsWith("p")) StoreController.CreateProduct(sc.next(), sc.next());
		if(in.startsWith("t")) StoreController.CreateTransaction(sc.nextInt());
	}

	private static void Edit(Scanner sc)
	{
		System.out.println("Editing items is not yet supported");
	}

	private static void Index(Scanner sc)
	{
		String in = sc.next();
		if(in.equals("commands"))
		{
			System.out.println("p (product)");
		}
		if(in.startsWith("p"))
		{
			ArrayList<Product> products = StoreController.FindProduct(new HashMap<String, String>());
			for(Product p : products)
			{
				System.out.println(p.getTitle() + " " + p.getType() + " " + p.getQuantity());
			}
		}
	}

	private static void Delete(Scanner sc)
	{

	}

	private static void BaseCommands()
	{
		System.out.println("Commands:");
		System.out.println("a (add)\ne (edit)\ni (index)\nd (delete)");
	}
}
