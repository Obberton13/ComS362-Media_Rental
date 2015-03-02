package MediaRental.Model;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class Product
{
    protected int id;
    protected String title;
    protected String type;
    protected String genre;
    protected int quantity;
    protected int catalogId;

    public Product()
    {
        this.title = "";
        this.type = "";
        this.genre = "";
        this.quantity = 0;
        this.catalogId = 0;
        this.id = 0;
    }

    public Product(String title)
    {
        this.title = title;
        this.type = "";
        this.genre = "";
        this.quantity = 0;
        this.catalogId = 0;
        this.id = 0;
    }

    public Product(String title, String type)
    {
        this.title = title;
        this.type = type;
        this.genre = "";
        this.quantity = 0;
        this.catalogId = 0;
        this.id = 0;
    }

    public Product(String title, String type, String genre)
    {
        this.title = title;
        this.type = type;
        this.genre = genre;
        this.quantity = 0;
        this.catalogId = 0;
        this.id = 0;
    }

    public Product(String title, String type, String genre, int quantity, int id)
    {
        this.title = title;
        this.type = type;
        this.genre = genre;
        this.quantity = quantity;
        this.catalogId = 0;
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


    @Override
    public String toString()
    {
        return "" + id + ", " + title + ", " + type;
    }
}