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


    public Customer(String name, String address)
    {
        id = 0;
        this.name = name;
        this.address = address;
        this.transactions = new ArrayList<Transaction>();
    }


    public Customer(String name, String address, int id)
    {
        this.id = id;
        this.name = name;
        this.address = address;
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
    
    public int getFrequentCustomerPoints(){
        int points = 0;
        for (int i=0; i<this.transactions.size(); i++){
            Transaction transaction = this.transactions.get(i);
            points += transaction.getFrequentCustomerPoints();
        }
        return points;
    }

    @Override
    public String toString()
    {
        return "" + id + ", " + name + ", " + address;
    }
}