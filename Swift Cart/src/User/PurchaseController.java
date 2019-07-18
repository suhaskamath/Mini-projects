package User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;

import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class PurchaseController implements Initializable{
	@FXML
	private JFXListView<HBox> list;
	@FXML
	private TableView <Product> bought;
	@FXML
	private TableColumn<Product,String> product;

	@FXML
	private Label bill;
	@FXML
	private TextField search;

	private String search1="";
	@FXML
	private TableColumn<Product,Integer> cost;
	@FXML
	private TableColumn<Product,Integer> qty;
	private ArrayList<String> items;
	public ObservableList<Product> tb_list=FXCollections.observableArrayList();
	int i=0;
	//@FXML
	//private JFXPopup popup;
	@FXML
	private JFXComboBox<Label> dropdw;
	@FXML
	private JFXComboBox<Label> sort;
	private String category;
	private String sorter;
	public void load() throws ClassNotFoundException, SQLException, FileNotFoundException {
		i=0;
			HBox[] hbox = new HBox[100]; 
			Label[] name=new Label[100];//Intialization of product name label
			Label[] price=new Label[100];//Intialization of labels of price
			CheckBox[] c = new CheckBox[100];//Intitialization of checkbox
			ImageView[] img=new ImageView[100];//Initialization of images
		TextField[] txt=new TextField[100];
		EventHandler k1=new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				bought.refresh();
			}
		};
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
		search.setOnKeyPressed(se);
		EventHandler ke=new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("hello ke");
				TextField t1=(TextField) event.getSource();
				HBox h1=(HBox)t1.getParent();
				Iterator<Node> itr=h1.getChildren().iterator();
				String s1="";
				while (itr.hasNext()){
					Node n=itr.next();
					if(n instanceof Label){
						Label l1=(Label)n;
						s1=l1.getText();
						break;
					}
				}
				Iterator itr1= tb_list.iterator();
				int ind=0;

				while(itr1.hasNext())
				{
					Product i1=(Product)itr1.next();
					if(i1.getName().equals(s1)) {
						//System.out.println("Table "+i1.getName()+" List "+name[t].getText()+" t: "+t);
						break;
					}
					//System.out.println("Table "+i1.getName()+" List "+name[t].getText()+" t: "+t);
					ind++;

				}
				if((Integer)bought.getItems().size()>ind){
					float qty=bought.getItems().get(ind).getQty();
					try {
						bought.getItems().get(ind).setQty(Float.parseFloat(t1.getText()));

						float c = bought.getItems().get(ind).getPrice();
						float c2 = c * qty;
						float c1 = c * Float.parseFloat((t1.getText()));
						float tc = Float.parseFloat(bill.getText().substring(13));
						System.out.println("c1: " + c1 + " c2: " + c2 + " tc: " + tc);
						tc = tc + c1 - c2;

						bill.setText("Total Cost : " + Float.toString(tc));
						Main.u1.setBill_Amount(tc);
					}
					catch (Exception e)
					{
						System.out.println("Entered Wrong Input");
					}

				}

				bought.refresh();
				bought.refresh();

			}
		};
	        EventHandler eh = new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent ev) {
	            	if (ev.getSource() instanceof CheckBox) {
	                    CheckBox chk = (CheckBox) ev.getSource();
	                    if(chk.isSelected())
	                    {int t=Integer.parseInt(chk.getId().toString());
	                    if(txt[t].getText()==null)
	                    	txt[t].setText("1.00");
	                   // System.out.println(" Selected is "+name[t].getText().toString());
	                   tb_list.add(new Product(name[t].getText(),Float.parseFloat(price[t].getText().substring(3)),"",Float.parseFloat(txt[t].getText())));
	                   bought.setItems(tb_list);
	                   Main.u1.setBill_Amount(Main.u1.getBill_Amount()+(Float.parseFloat(price[t].getText().substring(3))*Float.parseFloat(txt[t].getText())));
	                   bill.setText("Total cost : "+Main.u1.getBill_Amount());
	                   items.add(name[t].getText());
	                    }
	                    else
	                    	{
	                    	int t=Integer.parseInt(chk.getId().toString());
		                    System.out.println(" Removed  "+name[t].getText().toString());
		                    Iterator itr= tb_list.iterator();
		                    int ind=0;
		                    while(itr.hasNext())
		                    {
		                    	Product i1=(Product)itr.next();
		                    	if(i1.getName().equals(name[t].getText())) {
									//System.out.println("Table "+i1.getName()+" List "+name[t].getText()+" t: "+t);
		                    		break;
								}
								//System.out.println("Table "+i1.getName()+" List "+name[t].getText()+" t: "+t);
		                    	ind++;

	                    	}
		                    Product ps=bought.getItems().get(ind);
		                    bought.getItems().remove(ind);
		                    Main.u1.setBill_Amount(Main.u1.getBill_Amount()-(Float.parseFloat(price[t].getText().substring(3))*ps.getQty()));
			                bill.setText("Total cost : "+Main.u1.getBill_Amount());
			                items.remove(name[t].getText());
	                    	}
	                    	}

	            }
	            
	        };

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
					txt[i]=new TextField();
					Iterator itr1= tb_list.iterator();
					int ind=0;

					while(itr1.hasNext())
					{
						Product i1=(Product)itr1.next();
						if(i1.getName().equals(s1)) {
							//System.out.println("Table "+i1.getName()+" List "+name[t].getText()+" t: "+t);
							break;
						}
						//System.out.println("Table "+i1.getName()+" List "+name[t].getText()+" t: "+t);
						ind++;

					}
					if((Integer)bought.getItems().size()>ind)
						txt[i].setText(Float.toString(bought.getItems().get(ind).getQty()));
					else
					txt[i].setText("1.00");
					txt[i].setOnKeyPressed(ke);
					txt[i].setOnKeyReleased(k1);
					price[i] = new Label("Rs " + Integer.toString(res[k].getInt("price")));

					c[i] = new CheckBox();
					c[i].setId(Integer.toString(i));
					Region region1 = new Region();
					HBox.setHgrow(region1, Priority.ALWAYS);

					Region region2 = new Region();
					HBox.setHgrow(region2, Priority.ALWAYS);
					Region region3 = new Region();
					HBox.setHgrow(region3, Priority.ALWAYS);
					Region region4 = new Region();
					HBox.setHgrow(region4, Priority.ALWAYS);
					hbox[i] = new HBox(img[i], region1, name[i], region2, price[i], region3, c[i],region4,txt[i]);

					hbox[i].getStylesheets().add("/User/application.css");
					hbox[i].getStyleClass().add("hbox");
					c[i].setOnAction(eh);
					//hbox[i].setPadding(new Insets(20, 20, 20, 20));
					list.getItems().add(hbox[i]);

					if (items.contains(name[i].getText())) {
						c[i].selectedProperty().setValue(true);
					}
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
			c[0] = Main.u1.getCategory();

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
		product.setCellValueFactory(new PropertyValueFactory<>("name"));
		cost.setCellValueFactory(new PropertyValueFactory<>("price"));
		qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
		bought.setItems(tb_list);
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
	public void buy()throws SQLException {
		Float f1=Main.u1.getBill_Amount();
		Float f2=Main.u1.getBalance();
		if(f1>f2) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Look, Insuffient Funds");
			alert.setContentText("Add Balance before you continue ");

			alert.showAndWait();}
		else
		{

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Conformation You Want to Buy");
			alert.setContentText("Current Balance is "+Main.u1.getBalance()+" Total cost is  "+Main.u1.getBill_Amount());
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
		f2=f2-f1;
		Main.u1.setBalance(f2);
		Main.u1.setBill_Amount(0.0f);
		System.out.println("Nil");
			PreparedStatement s1=Main.conn.prepareStatement("update user set balance=? where email=?");
			s1.setString(1,Float.toString(Main.u1.getBalance()));
			s1.setString(2,Main.u1.getEmail());
			s1.executeUpdate();
			bought();}
		}
			
	}
	public void bought() {
		try {
		Product p1=bought.getSelectionModel().getSelectedItem();
		Connection conn=Main.conn;
		System.out.println("hi");
		LocalDate d=LocalDate.now();
		LocalTime t=LocalTime.now();
		String date=d.toString();
		String time=t.toString();
		for(Product p:bought.getItems())
		{
			// Setting up the connection.
			System.out.println("h1"+date+" "+time);
			String q1="insert into bought values(?,?,?,?,?,?,?);";
			
            PreparedStatement s1=conn.prepareStatement(q1);
            s1.setString(1, Main.u1.getUser_name());
			s1.setString(2, p.getName());
            s1.setString(3, String.valueOf(p.getPrice()));
			s1.setString(4,date);
			s1.setString(5,time);
			s1.setString(6,String.valueOf(p.getQty()));
			s1.setString(7,Float.toString(p.getPrice()*p.getQty()));
			s1.executeUpdate();
			s1=conn.prepareStatement("select * from stat where pname=? and category=?");
			s1.setString(1, p.getName());
			s1.setString(2, Main.u1.getCategory());
			ResultSet res=s1.executeQuery();
			if(res.next()) {
				s1=conn.prepareStatement("update stat set qty=qty+? where pname=? and category=?");
				s1.setString(1,String.valueOf(p.getQty()));
				s1.setString(2, p.getName());
				s1.setString(3, Main.u1.getCategory());
				s1.executeUpdate();
			}
			else
			{
				s1=conn.prepareStatement("select target from product where pname=?;");
				s1.setString(1, p.getName());
				res=s1.executeQuery();
				if(res.next())
					p.setCategory(res.getString("target"));
				
				s1=conn.prepareStatement("insert into stat values(?,?,?,?);");
			
				s1.setString(1, p.getName());
				s1.setString(2, p.getCategory());
				s1.setString(3, Main.u1.getCategory());
				s1.setString(4,String.valueOf(p.getQty()));
				s1.executeUpdate();
			}
		}}catch(Exception e) {
			e.getStackTrace();
		}
		Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
		alert1.setTitle("Transaction Succesful");
		alert1.setHeaderText(null);
		alert1.setContentText("Thank You for shopping with us!Your Balance is "+Main.u1.getBalance());
		alert1.showAndWait();
            


	}
	}
	