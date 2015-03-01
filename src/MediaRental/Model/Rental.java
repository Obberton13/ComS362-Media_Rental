package MediaRental.Model;

public class Rental {

	protected Product product;
	protected int id;
	protected int price;
	protected int rentalLength; 
	
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
	
	public void setRentalLength(int len) {
		rentalLength = len;
		}
	
	public int getRentalLength() {
		return rentalLength;
	}
	
	public void setProduct(Product p) {
		product = p;
	}

	public Product getProduct() {
		return product;
	}
	
}
