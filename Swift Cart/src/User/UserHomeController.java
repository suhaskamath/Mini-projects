package User;
import Main.User;
import Main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class UserHomeController implements Initializable{
	@FXML
	private AnchorPane screen;
	@FXML
	private AnchorPane sidemenu;
	@FXML
	private HBox menu1;
	@FXML
	private AnchorPane pro;
	@FXML
	private AnchorPane his;

	// Event Listener on HBox[#menu1].onMouseClicked
	@FXML
	public void dothis(MouseEvent event) {
	pro.setVisible(true);
	his.setVisible(false);
	}
	// Event Listener on Label.onMouseClicked

	// Event Listener on HBox.onMouseClicked
	@FXML
	public void dothis2(MouseEvent event) {
		pro.setVisible(false);
		his.setVisible(true);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pro.setVisible(true);
		his.setVisible(false);
		Node node;
		try {
			node = (Node)FXMLLoader.load(getClass().getResource("/User/Purchase.fxml"));
			pro.getChildren().setAll(node);
			node = (Node)FXMLLoader.load(getClass().getResource("/User/History.fxml"));
			his.getChildren().setAll(node);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
}
