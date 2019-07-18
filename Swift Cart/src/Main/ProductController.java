package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ProductController implements Initializable{
	@FXML
	private JFXListView<HBox> list;
	@FXML
	private TextField search;

	private String search1="";
	private ArrayList<String> items;
	int i=0;
	//@FXML
	//private JFXPopup popup;
	@FXML
	private JFXComboBox<Label> dropdw;
	@FXML
	private JFXComboBox<Label> sort;
	private String category;
	private String sorter;
	static public String[]data=new String[2];
	public void load() throws ClassNotFoundException, SQLException, FileNotFoundException {
		i=0;
			HBox[] hbox = new HBox[100]; 
			Label[] name=new Label[100];//Intialization of product name label
			Label[] price=new Label[100];//Intialization of labels of price
			ImageView[] img=new ImageView[100];//Initialization of images
		EventHandler se= new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				search1=search.getText();
				System.out.println(search1);
				try{
					delete();
					load();}
				catch (Exception e){
					e.printStackTrace();

				}
			}
		};
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
					data[c++]=l1.getText();
					i++;}
				}		try {
					Stage primaryStage=new Stage();
					Parent root=FXMLLoader.load(getClass().getResource("/Main/Product_stats.fxml"));
						Scene scene = new Scene(root);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						primaryStage.setScene(scene);
						primaryStage.show();
					} catch(Exception e){
						e.printStackTrace();
					}
				
			
			}};
		search.setOnKeyPressed(se);

            ResultSet res[]=exec();
            for(int k=0;k<res.length;k++) {
            	if(res[k]!=null)
				while (res[k].next()) {


					// System.out.println("hello");

					name[i] = new Label(res[k].getString("pname"));
					  File file = new File("src/Imgs/"+res[k].getString("pname")+ ".jpg");
					img[i] = new ImageView(new Image(file.toURI().toString()));
					img[i].setFitHeight(100);
					img[i].setFitWidth(80);
					String s1=res[k].getString("pname");
					
					int ind=0;
					price[i] = new Label("Rs " + Integer.toString(res[k].getInt("price")));
					Region region1 = new Region();
					HBox.setHgrow(region1, Priority.ALWAYS);

					Region region2 = new Region();
					HBox.setHgrow(region2, Priority.ALWAYS);
					Region region3 = new Region();
					HBox.setHgrow(region3, Priority.ALWAYS);
					Region region4 = new Region();
					HBox.setHgrow(region4, Priority.ALWAYS);
					hbox[i] = new HBox(img[i], region1, name[i], region2, price[i]);

					hbox[i].getStylesheets().add("/User/application.css");
					hbox[i].getStyleClass().add("hbox");
					//hbox[i].setPadding(new Insets(20, 20, 20, 20));
					list.getItems().add(hbox[i]);
					hbox[i].setOnMouseClicked(eh);
			
					i++;

				}
			}
	list.setExpanded(true);
	list.depthProperty().set(1);
	list.setVerticalGap(10.0);


		
	}
	private ResultSet[] exec(){
		ResultSet res[]=new ResultSet[3];
		try{//Class.forName("org.sqlite.JDBC");  // Loading and registering the driver.

		Connection conn = Main.conn;
		//conn = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite"); // Setting up the connection.
		if(sorter==null){

			if(category.equals("All")) {
				PreparedStatement pre = conn.prepareStatement("select * from product where pname like ? ;");
				pre.setString(1,search1+"%");
				res[0]=pre.executeQuery();
				System.out.println("helloloafer");
			}else {
				PreparedStatement pre= conn.prepareStatement("select * from product where category=? and pname like ?;");
				pre.setString(1,category);
				pre.setString(2,search1+"%");
				res[0]=pre.executeQuery();}
		}
		else if(sorter.equals("Relevance")) {
			String c[] = new String[3];
			c[0] = "A";

			if (c[0].equals("C")) {
				c[1] = "B";
				c[2] = "A";

			}
			if (c[0].equals("B")) {
				c[1] = "C";
				c[2] = "A";

			}
			if (c[0].equals("A")) {
				c[1] = "B";
				c[2] = "C";

			}
			PreparedStatement pre = null;
			for (int l = 0; l < c.length; l++) {
				if (category.equals("All")) {
					pre = conn.prepareStatement("select * from product where target=? and pname like ?;");
					pre.setString(1, c[l]);
					pre.setString(2,search1+"%");

				} else {
					pre = conn.prepareStatement("select * from product where category=? and target=? and pname like ?;");
					pre.setString(1, category);
					pre.setString(2, c[l]);
					pre.setString(3,search1+"%");

				}
				res[l] = pre.executeQuery();
			}
		}
		else if(sorter.equals("Price")){
			PreparedStatement pre=null;
			System.out.println(category);
			if (category.equals("All")||category==null) {
				pre = conn.prepareStatement("select * from product where pname like ? order by price desc;");
System.out.println("hello");
pre.setString(1,search1+"%");
			} else {
				pre = conn.prepareStatement("select * from product where category=? and pname like ? order by price desc;");
				pre.setString(2,search1+"%");
				pre.setString(1, category);


			}
			res[0]=pre.executeQuery();
		}
		else if(sorter.equals("Product Name")){
			PreparedStatement pre=null;
			if (category.equals("All")) {
				pre = conn.prepareStatement("select * from product where pname like ? order by pname asc;");
				pre.setString(1,search1+"%");
			} else {
				pre = conn.prepareStatement("select * from product where category=? and pname like ? order by pname asc;");
				pre.setString(1, category);
				pre.setString(2,search1+"%");
			}
			res[0]=pre.executeQuery();
		}
		return res;
	} catch (SQLException e) {
			e.printStackTrace();
		}
	return res;}
		@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		category="All";
		sorter="Relevance";
		items=new ArrayList<String>();
		// TODO Auto-generated method stub
		try {
			load();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.setExpanded(true);
		list.depthProperty().set(1);
		list.setVerticalGap(10.0);
		dropdw.getItems().add(new Label("Vegetable"));
		dropdw.getItems().add(new Label("Fruit"));
		dropdw.getItems().add(new Label("Essentials"));
		dropdw.getItems().add(new Label("All"));
		sort.getItems().add(new Label("Relevance"));
		sort.getItems().add(new Label("Price"));
		sort.getItems().add(new Label("Product Name"));



	}
	public void delete() {
		list.getItems().clear();
	}
	public void category_select() {
//		System.out.println(dropdw.getSelectionModel().getSelectedItem().getText());

		if(dropdw.getSelectionModel().getSelectedItem()==null)
			category="All";
		else
			category=dropdw.getSelectionModel().getSelectedItem().getText();
		if(sort.getSelectionModel().getSelectedItem()==null)
			sorter="Relevance";
		else
		sorter=sort.getSelectionModel().getSelectedItem().getText();
		delete();
		try {
			load();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void add() {
		try {
			Stage primaryStage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/Main/Add.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public void refresh()throws Exception {
		list.getItems().clear();
		load();
	}
	}
	