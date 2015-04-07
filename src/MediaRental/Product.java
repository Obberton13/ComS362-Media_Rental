package MediaRental;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class Product
{
    protected int id;
    protected String title;
    protected String type;
    protected String genre;
    protected int catalogId;
    protected String description;
    protected FrequentCustomerStrategy s;
    
    
    public Product(int catalogId)
    {
        this.catalogId = catalogId;
    }


    public Product(String title, String type, String genre, String description)
    {
        this.title = title;
        this.type = type;
        this.genre = genre;
        this.catalogId = 0;
        this.id = 0;
    }
    
    public Product(String title, String type, String genre, String description, int catalogID)
    {
        this.title = title;
        this.type = type;
        this.genre = genre;
        this.catalogId = catalogID;
        this.id = 0;
    }
    
    public Product(String title, String type, String genre, String description, int id, int catalogID)
    {
        this.title = title;
        this.type = type;
        this.genre = genre;
        this.catalogId = catalogID;
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

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getCatalogId()
    {
        return catalogId;
    }

    public void setCatalogId(int id)
    {
        catalogId = id;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public String getGenre()
    {
        return genre;
    }
    
    public void setCustomerStrategy(FrequentCustomerStrategy strategy)
    {
    	s = strategy;
    }


    @Override
    public String toString()
    {
        return "" + id + ", " + title + ", " + type;
    }
}