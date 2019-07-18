package Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CateController implements Initializable {

	@FXML
	private  TableView<Product_det> table;
	@FXML
	private  TableColumn<Product_det,String> product_name;
	@FXML
	private  TableColumn<Product_det,String> target_cat;
	@FXML
	private  TableColumn<Product_det,String> cate;
	
	@FXML
	private  TableColumn<Product_det,Integer> qty;
	
	public ObservableList<Product_det> list=FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)  {
			try {
	            Class.forName("org.sqlite.JDBC");  // Loading and registering the driver.

	            Connection conn = null;
	            conn = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite"); // Setting up the connection.
	     
	    
	            ResultSet res= conn.createStatement().executeQuery("select * from stat;");
	            while(res.next()) {
	                list.add(new Product_det(res.getString("pname"),res.getString("tar_cate"),res.getString("category"),res.getInt("qty")));

	            }
				Product_det p1=new Product_det("he","po","oju",7);
	            int n=p1.port;

		list.add(new Product_det("he","po","oju",7));
	        PreparedStatement preparedStatement = null ;
	       // preparedStatement= main.conn.prepareStatement(cust);
	        //preparedStatement.setString(1,"suhas56");
	        //preparedStatement.setString(2,"suhas123");
	      
	       /* ResultSet res=preparedStatement.executeQuery() ;
	        String cat="";
	        if(res.next()) {
	        	System.out.print(res);
	        	cat=res.getString("category");
	        }
	        
	        String prod="select * from product where pname=?;";
	        preparedStatement= main.conn.prepareStatement(prod);
	        preparedStatement.setString(1,"Onion");
	        ResultSet res1=preparedStatement.executeQuery();
	        String prod_cat="",prod_name="";
	      if(res1.next()) {
	        prod_cat=res1.getString("target");
	       prod_name=res1.getString("pname");
	       System.out.println("Product category"+prod_cat+ " product_name"+prod_name);
	      }
	       String check="select * from stat where pname=? and category=?;";
	       preparedStatement= main.conn.prepareStatement(check);
	       preparedStatement.setString(1,prod_name);
	       preparedStatement.setString(2, cat);
	       ResultSet res2=preparedStatement.executeQuery();
	       String ins="";
	       if(res2.next()) {
	    	   ins="update stat set qty=qty+1 where pname=? and category=?;";
		       preparedStatement= main.conn.prepareStatement(ins);
		       preparedStatement.setString(1,prod_name);
		       preparedStatement.setString(2, cat);
		       preparedStatement.executeUpdate();
	       }
	       else
	       {
	    	   ins="insert into  stat values(?,?,?,?);";
		       preparedStatement= main.conn.prepareStatement(ins);
		       preparedStatement.setString(1,prod_name);
		       preparedStatement.setString(2, prod_cat);
		       preparedStatement.setString(3, cat);
		       preparedStatement.setString(4, "1");
		       preparedStatement.executeUpdate();  
	       }
	        
	        
*/

         System.out.println(list.get(0).cat);
          
		 product_name.setCellValueFactory(new PropertyValueFactory<>("pname"));
		 target_cat.setCellValueFactory(new PropertyValueFactory<>("tcate"));
		
		 cate.setCellValueFactory(new PropertyValueFactory<>("cat"));
		 
		 qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
		
		
			}catch (Exception e) { System.out.println("Ex:"+e);}
			table.setItems(list);
	}


}
