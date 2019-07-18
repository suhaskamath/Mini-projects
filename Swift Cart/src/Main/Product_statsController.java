package Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

public class Product_statsController implements Initializable{
@FXML
private PieChart pie;
@FXML 
private Label product;
@FXML
private JFXTextField cat;
@FXML
private JFXTextField pname;
@FXML
private JFXTextField price;
@FXML
private JFXTextField type;
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	Connection conn=Main.conn;
	try {
	PreparedStatement pre=conn.prepareStatement("select * from stat where pname=?;");
	pre.setString(1, ProductController.data[0]);
	ResultSet res=pre.executeQuery();
	while(res.next()) {
	PieChart.Data slice1 = new PieChart.Data(res.getString("category")+"-"+res.getString("qty"),Integer.parseInt(res.getString("qty")));
	pie.getData().add(slice1);
	}
	pre=conn.prepareStatement("select * from product where pname=?;");
	pre.setString(1, ProductController.data[0]);
	res=pre.executeQuery();
	while(res.next()) {
		cat.setText(res.getString("target"));
		pname.setText(res.getString("pname"));
		price.setText(Float.toString(res.getFloat("price")));
		type.setText(res.getString("category"));
	}
	}catch(Exception e) {
		e.printStackTrace();
	}
	
}
public void save() throws SQLException{
	PreparedStatement pre=Main.conn.prepareStatement("update stat set tar_cate=?,pname=? where pname=?;");
	pre.setString(1, cat.getText());
	pre.setString(2, pname.getText());
	pre.setString(3, ProductController.data[0]);
	pre.executeUpdate();
	pre=Main.conn.prepareStatement("update product set pname=?,price=?,category=?,target=? where pname=?;");
	pre.setString(4, cat.getText());
	pre.setString(1, pname.getText());
	pre.setString(2, price.getText());
	pre.setString(3, type.getText());
	pre.setString(5, ProductController.data[0]);
	pre.executeUpdate();
	pre=Main.conn.prepareStatement("update bought set pname=? where pname=?;");
	pre.setString(2, ProductController.data[0]);
	pre.setString(1, pname.getText());
	pre.executeUpdate();
}

}
