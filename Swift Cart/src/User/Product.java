package User;

public class Product {
private String name;
private float price;
private String category;
private float qty;
public Product(String name, float price, String category,float qty) {
	this.name = name;
	this.price = price;
	this.category = category;
	this.qty=qty;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public float getPrice() {
	return price;
}
	public float getQty() {
		return qty;
	}
public void setPrice(float price) {
	this.price = price;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}

	public void setQty(float qty) {
		this.qty = qty;
	}
}
