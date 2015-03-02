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

	public Product(String title, String type, String genre, int id)
	{
		this.title = title;
		this.type = type;
		this.genre = genre;
		this.quantity = 0;
		this.id = id;
	}

	public Product(String title, String type, String genre, int quantity, int id)
	{
		this.title = title;
		this.type = type;
		this.genre = genre;
		this.quantity = quantity;
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public String getTitle()
	{
		return title;
	}

	public String getType()
	{
		return type;
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
