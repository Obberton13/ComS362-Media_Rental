package MediaRental;

import java.util.regex.Pattern;

public class Rental
{

    protected Product product;
    protected int id;
    protected double price;
    protected String dueDate;
    protected int daysRented;

    public Rental(Product product, String dueDate, int daysRented)
    {
        this.product = product;
        this.dueDate = dueDate;
        id = 0;
    }

    public Rental(Product product, String dueDate, int id, int daysRented)
    {
        this.product = product;
        this.dueDate = dueDate;
        this.id = id;
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
        return product.getPrice(this.daysRented);
    }
    
    public int getDaysRented(){
        return this.daysRented;
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