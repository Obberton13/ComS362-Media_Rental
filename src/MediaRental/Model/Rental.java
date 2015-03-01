package MediaRental.Model;

public class Rental {

	protected Product product;
	protected int id;
	protected int price;
	protected String dueDate; 
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setPrice(int p) {
	price = p;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setDueDate(String len) {
		dueDate = len;
		}
	
	public String getDueDate() {
		return dueDate;
	}
	
	public void setProduct(Product p) {
		product = p;
	}

	public Product getProduct() {
		return product;
	}
	
}
