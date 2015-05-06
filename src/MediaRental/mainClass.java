package MediaRental;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Obberton13 on 2/20/2015.
 */
public class mainClass
{
    private static Pattern isDate = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}$");

    public static void main(String args[])
    {
        //this is the main loop for the program.
//	    DatabaseSupport db = new DatabaseSupport();
//	    db.createTables();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext())
        {
            baseCommands();
            String in = sc.nextLine().toLowerCase();
            if (in.isEmpty())
            {
                continue;
            }
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
                case 'o':
                    other(sc);
                    break;
                case 's':
                    set(sc);
                    break;
                case 'c':
                    if (in.length() < 2)
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
                            System.out.println("Invalid command: " + in);
                            baseCommands();
                            break;
                    }
                    break;
                case 'g':
                    get(sc);
                    break;
                default:
                    System.out.println("Invalid command: " + in);
                    baseCommands();
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
                "r adds a rentalPricingStrategy\n" +
                "f adds a frequentRenterStrategy\n" +
                "q returns to the previous menu");
        String input = in.nextLine();
        if (input.isEmpty())
        {
            return;
        }
        switch (input.charAt(0))//creates a customer
        {
            case 'c':
                System.out.println("Creating a Customer.");
                System.out.println("Customer name: ");
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
                cid = in.nextInt();
                in.nextLine();
                b = new StoreController().createTransaction(cid);
                System.out.println("Operation success boolean is " + b);
                break;
            case 'r':
                System.out.println("Creating a rental strategy: ");
                System.out.println("Strategy Name: ");
                name = in.nextLine();
                System.out.println("Standard Rental Charge: ");
                double standardRentalCharge = in.nextDouble();
                in.nextLine();
                System.out.println("StandardRentalLength: ");
                int standardRentalLength = in.nextInt();
                in.nextLine();
                System.out.println("Daily overdue charge: ");
                double dailyOverdueCharge = in.nextDouble();
                in.nextLine();
                b = new StoreController().createRentalPricingStrategy(standardRentalCharge, standardRentalLength, dailyOverdueCharge, name);
                System.out.println("Operation success boolean is " + b);
                break;
            case 'f':
                System.out.println("Creating a frequent renter strategy: ");
                System.out.println("Strategy Name: ");
                name = in.nextLine();
                System.out.println("Fixed Points: ");
                int fixedPoints = in.nextInt();
                in.nextLine();
                System.out.println("Points Per day: ");
                int ppd = in.nextInt();
                in.nextLine();
                b = new StoreController().createFrequentCustomerStrategy(fixedPoints, ppd, name);
                System.out.println("Operation success boolean is " + b);
                break;
            case 'q':
                return;
            default:
                System.out.println("Invalid thing to create: " + input);
                break;
        }
    }

    private static void get(Scanner in)
    {
        System.out.println("<t|c>\n" +
                "t gets a transaction\n" +
                "c gets a customer's rental history\n" +
                "f gets a customer's frequent rental points");
        String input = in.nextLine();
        if (input.isEmpty())
        {
            return;
        }
        switch (input.charAt(0))
        {
            case 't':
                System.out.println("Getting transaction statement");
                System.out.println("Transaction ID: ");
                int tid = in.nextInt();
                in.nextLine();
                String s = new StoreController().getTransactionStatement(tid);
                System.out.println(s);
                break;
            case 'c':
                System.out.println("Getting Customer Rental History");
                System.out.println("Customer ID: ");
                int cid = in.nextInt();
                in.nextLine();
                ArrayList<Product> rentedProducts = new StoreController().getCustomerRentalHistory(cid);
                if (rentedProducts == null)
                {
                    System.out.println("A customer with id " + cid + " does not exist.");
                } else
                {
                    for (Product p : rentedProducts)
                    {
                        System.out.println(p);
                    }
                }
                break;
            case 'f':
                System.out.println("Getting frequent customer points");
                System.out.println("Customer ID: ");
                cid = in.nextInt();
                in.nextLine();
                int f = new StoreController().getFrequentCustomerPoints(cid);
                System.out.println(f);
                break;
        }
    }

    private static void set(Scanner in)
    {
        String input = in.nextLine();
        if (input.isEmpty())
        {
            return;
        }
        switch (input.charAt(0))
        {
            case 'f':
                System.out.println("Set frequent customer strategy on product");
                System.out.println("Product ID: ");
                int pid = in.nextInt();
                in.nextLine();
                System.out.println("Strategy Name: ");
                String strategyName = in.nextLine();

                boolean b = new StoreController().setFrequentCustomerStrategy(strategyName, pid);
                System.out.println("Operation success boolean is " + b);
                break;
            case 'r':
                System.out.println("Set rental pricing strategy on product");
                System.out.println("Product ID: ");
                pid = in.nextInt();
                in.nextLine();
                System.out.println("Strategy Name");
                strategyName = in.nextLine();

                b = new StoreController().setRentalPricingStrategy(strategyName, pid);
                System.out.println("Operation success boolean is " + b);
                break;
        }
    }

    private static void other(Scanner in)
    {
        System.out.println("<p|r>\n" +
                "p pays for a transaction\n" +
                "r returns a product to the store");
        String input = in.nextLine();
        if (input.isEmpty())
        {
            return;
        }
        switch (input.charAt(0))
        {
            case 'p':
                System.out.println("Paying for transaction");
                System.out.println("Transaction ID: ");
                int tid = in.nextInt();
                in.nextLine();
                boolean b = new StoreController().payForTransaction(tid);
                System.out.println("Operation success boolean is " + b);
                break;
            case 'r':
                System.out.println("Returning a product");
                System.out.println("Product ID: ");
                int pid = in.nextInt();
                in.nextLine();
                b = new StoreController().returnProduct(pid);
                System.out.println("operation success boolean is " + b);
        }
    }

    private static void add(Scanner in)
    {
        System.out.println("<p|r|s>\n\n" +
                "p adds a product\n" +
                "r adds a rental\n" +
                "s adds a sale\n");
        String input = in.nextLine();
        if (input.isEmpty())
        {
            return;
        }
        switch (input.charAt(0))
        {
            case 'p':
                System.out.println("Adding a product to the store");
                System.out.println("Product Catalog ID: ");
                int pid = in.nextInt();
                in.nextLine();
                System.out.println("Quantity: ");
                int qty = in.nextInt();
                in.nextLine();
                boolean b = new StoreController().addProduct(pid, qty);
                System.out.println("Operation success boolean is " + b);
                break;
            case 'r':
                System.out.println("Adding rental to transaction");
                System.out.println("Product ID: ");
                pid = in.nextInt();
                in.nextLine();
                System.out.println("Transaction ID: ");
                int tid;
                tid = in.nextInt();
                in.nextLine();
                System.out.println("Due Date (YYYY-MM-DD): ");
                String date = in.next(isDate);
                in.nextLine();
                System.out.println("Days rented: ");
                int daysRented = in.nextInt();
                in.nextLine();
                System.out.println("Transaction ID: ");
                b = new StoreController().addRental(tid, pid, date, daysRented);
                System.out.println("Operation success boolean is " + b);
                break;
            case 's':
                System.out.println("Adding Sale to transaction");
                System.out.println("Product ID: ");
                pid = in.nextInt();
                in.nextLine();
                System.out.println("Transaction ID: ");
                tid = in.nextInt();
                in.nextLine();
                System.out.println("Price: ");
                double price = in.nextDouble();
                in.nextLine();
                b = new StoreController().addSale(tid, pid, price);
                System.out.println("Operation success boolean is " + b);
        }
    }

    private static void edit(Scanner in)
    {
        System.out.println("<p|c>\n" +
                "p updates the title of a product\n" +
                "c updates the name of a customer");
        String input = in.nextLine();
        if(input.isEmpty()) return;
        switch(input.charAt(0))
        {
            case 'c':
                System.out.println("Updating name of a customer.");
                System.out.println("Please enter the ID of the customer you would like to update");
                int cid = in.nextInt();
                in.nextLine();
                System.out.println("Please enter the new name of the customer");
                String name = in.nextLine();
                boolean b = new StoreController().updateCustomerName(cid, name);
                System.out.println("Operation success boolean is " + b);
                break;
            case 'p':
                System.out.println("Updating name of a customer.");
                System.out.println("Please enter the ID of the customer you would like to update");
                int pid = in.nextInt();
                in.nextLine();
                System.out.println("Please enter the new name of the customer");
                String title = in.nextLine();
                b = new StoreController().updateProductTitle(pid, title);
                System.out.println("Operation success boolean is " + b);
                break;

        }
    }

    private static void index(Scanner in)
    {
        System.out.println("<p>\n" +
                "p finds a product\n");
        String input = in.nextLine();
        if (input.isEmpty())
        {
            return;
        }
        switch (input.charAt(0))
        {
            case 'p':
                System.out.println("Indexing Products: \n");
                System.out.println("Title (Leave blank to get all): ");
                String title = in.nextLine();
                System.out.println("Genre (Leave blank to get all): ");
                String genre = in.nextLine();
                ArrayList<Product> products = new StoreController().findProduct(title, genre);
                if (products.isEmpty())
                {
                    System.out.println("Found no products.");
                    return;
                }
                System.out.println("Found products: ");
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
        System.out.println("<c>\n" +
                "c deletes a customer");
        String input = in.nextLine();
        if (input.isEmpty())
        {
            return;
        }
        switch (input.charAt(0))
        {
            case 'c':
                System.out.println("Removing customer");
                System.out.println("Customer ID: ");
                int cid = in.nextInt();
                in.nextLine();
                boolean b = new StoreController().removeCustomer(cid);
                System.out.println("Operation success boolean is " + b);
        }
    }

    private static void baseCommands()
    {
        System.out.println("Commands:");
        System.out.println("<cr|a|e|i|d|o|q>\n\n" +
                "cr - create\n" +
                "a - add\n" +
                "e - edit\n" +
                "i - index\n" +
                "d - delete\n" +
                "o - other\n" +
                "s - set\n" +
                "q returns to the previous menu");
    }
}
