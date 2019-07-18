package Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class HomeController implements Initializable{

	Main m1=new Main();
	m1.
	@FXML
    private AnchorPane screen;

    @FXML
    private AnchorPane sidemenu;

    @FXML
    private HBox menu1;

    @FXML
    private AnchorPane anc;

    @FXML
    private AnchorPane table;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		table.setVisible(true);
		anc.setVisible(false);
		Node node;
		try {
			node = (Node)FXMLLoader.load(getClass().getResource("/Main/Products.fxml"));
			table.getChildren().setAll(node);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 
    public void dothis() throws IOException {
    	System.out.println("hello");
	table.setVisible(true);
		anc.setVisible(false);
		Node node;
		node = (Node)FXMLLoader.load(getClass().getResource("/Main/List.fxml"));
		table.getChildren().setAll(node);
	    }
	public void users()throws  IOException{
		table.setVisible(false);
		anc.setVisible(true);
		Node node = (Node)FXMLLoader.load(getClass().getResource("/Main/Users.fxml"));
		anc.getChildren().setAll(node);
	}
	
}
