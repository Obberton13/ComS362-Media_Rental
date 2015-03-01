package MediaRental.Model;

/**
 * Created by Obberton13 on 2/16/2015.
 */
public class Product {
	protected int id;
	protected String title;
	protected String type;
	protected int quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString()
	{
		return "" + id + ", " + title + ", " + type;
	}
}
