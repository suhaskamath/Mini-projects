package Main;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	public static User u1;
	public static Connection conn;
	protected int po;
	public void start(Stage primaryStage) {
		try {
		Parent root=FXMLLoader.load(getClass().getResource("/Main/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)throws Exception {
		Class.forName("org.sqlite.JDBC");  // Loading and registering the driver.

        conn = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
		launch(args);
	}
}
