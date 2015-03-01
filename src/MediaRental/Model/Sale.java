package MediaRental.Model;

public class Sale {

	protected Product product;
	protected int id;
	protected int price;
	
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
	
	
	public void setProduct(Product p) {
		product = p;
	}

	public Product getProduct() {
		return product;
	}
}
