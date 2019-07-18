package Main;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

public class Product_det {
    String pname;//THe javafx property integers and string
    String  tcat;
	String cat;
	Integer qty;
	protected int port;
	public Product_det(String pname, String tcat, String cat, Integer qty) {
		super();
		this.pname = pname;
		this.tcat = tcat;
		this.cat = cat;
		this.qty = qty;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getTcat() {
		return tcat;
	}
	public void setTcat(String tcat) {
		this.tcat = tcat;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	

}
