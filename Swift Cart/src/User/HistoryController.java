package User;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import Main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class HistoryController implements Initializable{
	@FXML
	private AnchorPane top;
	@FXML
	private JFXListView<HBox> list;

	@FXML
	private Label bal;
	@FXML
	private Label usr;
	@FXML
	private Label gen;
	@FXML
	private Label age;
	@FXML
	private Label email;
private  int i=0;
static public String []data=new String[2];
	// Event Listener on JFXButton.onAction
	@FXML
	public void adder(ActionEvent event) throws Exception{
		TextInputDialog dialog = new TextInputDialog("walter");
		dialog.setTitle("Add Balance");
		dialog.setHeaderText("Input Dialog");
		dialog.setContentText("Please enter the balance to add:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		
	
		if(result.isPresent()) {
			load();
			String s1=result.get();
			//int l=Integer.parseInt(bal.getText());
			PreparedStatement pre=Main.conn.prepareStatement("update user set balance=balance+? where email=?;");
			pre.setString(1,s1);
			pre.setString(2, Main.u1.getEmail());
			pre.executeUpdate();
			//bal.setText(result.get());
		}
	}
	@FXML
	public void edit(ActionEvent ev){
		Stage primaryStage = new Stage();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/User/Editer.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Hello");
		try {
			load();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public void load() throws SQLException {
		i=0;
		HBox[] hbox = new HBox[100];
		Label[] cost=new Label[100];//Initialization of Total cost label
		Label[] date=new Label[100];//Initialization of labels of date
		Label[] time=new Label[100];//Initialization of labels of time
		EventHandler eh = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent ev) {
				HBox h1=(HBox)ev.getSource();
				Iterator<Node> itr=h1.getChildren().iterator();
				int i=0;
				int c=0;
				while(itr.hasNext()){
					Node n2=itr.next();
					if(n2 instanceof Label){

					Label l1=(Label)n2;
					if(i>0)
					data[c++]=l1.getText();
					i++;}
				}
				String s1=data[0];
				data[0]=s1.substring(6,10)+"-"+s1.substring(3,5)+"-"+s1.substring(0,2);
				Stage primaryStage = new Stage();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/User/Transaction.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
				}catch (IOException e){
					e.printStackTrace();
				}
			}

		};


		ResultSet res=exec();
			if(res!=null)
				while (res.next()) {

					String str=res.getString("dop");
					// System.out.println("hello");
					String rev=str.substring(8,10)+"-"+str.substring(5,7)+"-"+str.substring(0,4);
					date[i] = new Label(rev);
					time[i] = new Label(res.getString("top"));

					cost[i] = new Label("Rs " + Integer.toString(res.getInt("sum(tprice)")));
					Region region1 = new Region();
					HBox.setHgrow(region1, Priority.ALWAYS);

					Region region2 = new Region();
					HBox.setHgrow(region2, Priority.ALWAYS);

					hbox[i] = new HBox(cost[i], region1, date[i],region2,time[i]);

					hbox[i].getStylesheets().add("/User/application.css");
					hbox[i].getStyleClass().add("hbox");
					hbox[i].setOnMouseClicked(eh);
					//hbox[i].setPadding(new Insets(20, 20, 20, 20));
					list.getItems().add(hbox[i]);
					i++;


		}
		list.setExpanded(true);
		list.depthProperty().set(1);
		list.setVerticalGap(10.0);
		PreparedStatement pre=Main.conn.prepareStatement("select username,email,age,gender,balance from user where email=?");
		pre.setString(1,Main.u1.getEmail());
		res=pre.executeQuery();
		while (res.next()){
			bal.setText("Current Balance : "+res.getFloat("balance"));
			usr.setText("User Name: "+res.getString("username"));
			email.setText("Email : "+res.getString("email"));
			age.setText("Age :"+res.getInt("age"));
			gen.setText("Gender :"+res.getString("gender"));
		}
	}
	public ResultSet exec(){
		ResultSet res=null;
		try {
			Connection conn = Main.conn;
			PreparedStatement pre = conn.prepareStatement("select sum(tprice),dop,top from bought where name=? group by dop,top order by dop desc,top desc ");
			pre.setString(1,Main.u1.getUser_name());
			res = pre.executeQuery();
			return res;
		}catch (Exception e){
			e.printStackTrace();
		}
		return res;
	}
	public void refresh(ActionEvent ev)throws  IOException{
		JFXButton l1=(JFXButton)ev.getSource();
		Node n1=l1.getParent();
		AnchorPane ac=(AnchorPane)n1.getParent();
		Node node=(Node)FXMLLoader.load(getClass().getResource("/User/History.fxml"));
		ac.getChildren().clear();
		ac.getChildren().add(node);

	}
}
