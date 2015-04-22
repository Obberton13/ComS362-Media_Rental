package MediaRental;

import java.util.regex.Pattern;

public class Rental
{

    protected Product product;
    protected int id;
    protected double price;
    protected String dueDate;

    public Rental(Product product, String dueDate, double price)
    {
        this.product = product;
        this.dueDate = isDate(dueDate) ? dueDate : "1990-01-01";
        id = 0;
        this.price = price;
    }

    public Rental(Product product, String dueDate, double price, int id)
    {
        this.product = product;
        this.dueDate = isDate(dueDate) ? dueDate : "1990-01-01";
        this.id = id;
        this.price = price;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setPrice(double p)
    {
        price = p;
    }

    public double getPrice()
    {
        return price;
    }

    public void setDueDate(String len)
    {
        dueDate = len;
    }

    public String getDueDate()
    {
        return dueDate;
    }

    public void setProduct(Product p)
    {
        product = p;
    }

    public Product getProduct()
    {
        return product;
    }

    private boolean isDate(String date)
    {
        return Pattern.compile("/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/").matcher(date).matches();
    }

}