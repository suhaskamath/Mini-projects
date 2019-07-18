package Main;

import java.sql.PreparedStatement;
import java.util.Random;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;

public class addController {
	@FXML
	private JFXTextField cat;
	@FXML
	private JFXTextField pid;
	@FXML
	private JFXTextField pname;
	@FXML
	private JFXTextField price;
	@FXML
	private JFXTextField type;
	public void save() throws Exception {
		Random rn=new Random(100);
		PreparedStatement pre=Main.conn.prepareStatement("insert into product values (?,?,?,?,?);");
		pre.setString(1, pid.getText());
		pre.setString(2, pname.getText());
		pre.setString(3, price.getText());
		pre.setString(4, type.getText());
		pre.setString(5, cat.getText());
		pre.executeUpdate();
	}
}
