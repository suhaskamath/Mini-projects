package Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	@FXML
	private JFXTextField usr;

	@FXML
	private JFXPasswordField pass;

	@FXML
	private Label lab;
	@FXML
	private Label Admin;
	@FXML
	private Label User;
	@FXML
	private JFXButton Login;
	@FXML
	private JFXToggleButton tog;
	

	public void Login() throws Exception {
		if(Admin.isVisible()) {
		if (usr.getText().equals("test")&&pass.getText().equals("test")) {
			lab.setText("Sucessful");
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/Main/Home.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			// Stage stage = (Stage) Login.getScene().getWindow();
			// do what you have to do
			// stage.close();
		} else
			lab.setText("Unsucessful");
	}
		else
		{
			Class.forName("org.sqlite.JDBC");  // Loading and registering the driver.

            Connection conn = Main.conn;
            PreparedStatement prep=conn.prepareStatement("select * from user where email=? and password=?;");
            prep.setString(1, usr.getText());
            prep.setString(2, pass.getText());
	            
	            ResultSet r1=prep.executeQuery();
	            if(r1.next()) {
	            	System.out.println("User name ="+r1.getString("username")+" Passsword "+r1.getString("password"));
	            	Main.u1=new User(r1.getString("username"),r1.getFloat("balance"),r1.getString("category"));
	            	Main.u1.setEmail(r1.getString("email"));
	            	Main.u1.setGender(r1.getString("gender"));
	            	Main.u1.setPassword(r1.getString("password"));
	            	Main.u1.setAge(r1.getInt("age"));
	            
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/User/UserHome.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.resizableProperty().setValue(false);
				primaryStage.show();}
		}
	}

	public void Signup() throws IOException {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/Main/SignUp.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Admin.setVisible(true);
		User.setVisible(false);
	//	usr.setText("shalinik@gmail.com");
      //  pass.setText("shalini123");
		tog.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent ev) {
				// TODO Auto-generated method stub
				JFXToggleButton t1=(JFXToggleButton)ev.getSource();
				if(t1.isSelected())
				{
					Admin.setVisible(false);
					User.setVisible(true);
				}
				else
				{
					Admin.setVisible(true);
					User.setVisible(false);
				}
				
			}
			
		});
	}

}
