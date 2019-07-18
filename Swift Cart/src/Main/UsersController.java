package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class UsersController implements Initializable {
	@FXML
	private TableView<User> table;
	@FXML
	private TableColumn<User,String> name;
	@FXML
	private TableColumn<User,String> email;
	//public static User u;
	@FXML
	private TableColumn<User,String> pass;
	@FXML
	private TableColumn<User,Integer> age;
	@FXML
	private TableColumn<User,String> gender;
	@FXML
	private TableColumn<User,String> category;
	@FXML
	private TableColumn<User,Float> score;
	@FXML
	private TableColumn<User,Float> bal;
	@FXML
	private TableColumn<User,Float> salary;
	ObservableList<User> lis= FXCollections.observableArrayList();

	// Event Listener on TableView[#table].onMouseClicked
	@FXML
	public void show(MouseEvent event) throws IOException {
	Main.u1=table.getSelectionModel().getSelectedItem();
	Stage primaryStage=new Stage();
		Parent root= FXMLLoader.load(getClass().getResource("/User/History.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	// Event Listener on JFXButton.onAction
	@FXML
	public void load() {
		try {
			Connection conn=Main.conn;
	lis.clear();
			ResultSet res=conn.createStatement().executeQuery("select * from user");
			while(res.next())
			{
				lis.add(new User(res.getString("username"),res.getFloat("balance"),res.getString("category"),res.getString("password"),res.getString("email"),res.getString("gender"),res.getFloat("Salary"),res.getFloat("buying_score"),res.getInt("age")));

			}

			table.setItems(lis);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.setCellValueFactory(new PropertyValueFactory<>("user_name") );
		bal.setCellValueFactory(new PropertyValueFactory<>("balance") );
		category.setCellValueFactory(new PropertyValueFactory<>("Category") );
		pass.setCellValueFactory(new PropertyValueFactory<>("password") );
		email.setCellValueFactory(new PropertyValueFactory<>("email") );
		gender.setCellValueFactory(new PropertyValueFactory<>("gender") );
		salary.setCellValueFactory(new PropertyValueFactory<>("Salary") );
		score.setCellValueFactory(new PropertyValueFactory<>("score") );
		age.setCellValueFactory(new PropertyValueFactory<>("age") );

		load();
	}
}
