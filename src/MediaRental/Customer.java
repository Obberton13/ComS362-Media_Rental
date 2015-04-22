package MediaRental;

import java.util.ArrayList;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class Customer
{

    protected int id;
    protected String name;
    protected String address;
    protected ArrayList<Transaction> transactions;

    public Customer()
    {
        id = 0;
        name = "";
        address = "";
        transactions = new ArrayList<Transaction>();
    }

    public Customer(String name, String address)
    {
        id = 0;
        this.name = name;
        this.address = address;
        this.transactions = new ArrayList<Transaction>();
    }

    public Customer(String name, String address, ArrayList<Transaction> transactions)
    {
        id = 0;
        this.name = name;
        this.address = address;
        this.transactions = transactions;
    }

    public Customer(String name, String address, ArrayList<Transaction> transactions, int id)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.transactions = transactions;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public ArrayList<Transaction> getTransactions()
    {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions)
    {
        this.transactions = transactions;
    }

    @Override
    public String toString()
    {
        return "" + id + ", " + name + ", " + address;
    }
}