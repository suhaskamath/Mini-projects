package User;

import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class TransactionController implements Initializable {
	@FXML
	private TableView<Prod_bo> t1;
	@FXML
	private TableColumn<Prod_bo,String> pname;
	@FXML
	private TableColumn<Prod_bo,Float> cost;
	@FXML
	private TableColumn<Prod_bo,Float> qty;
	@FXML
	private TableColumn<Prod_bo,Float> tcost;
	@FXML
	private Label ltcost;
	private float totalcost=0;


	ObservableList<Prod_bo> lis= FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pname.setCellValueFactory(new PropertyValueFactory<>("pname") );
		cost.setCellValueFactory(new PropertyValueFactory<>("price") );
		qty.setCellValueFactory(new PropertyValueFactory<>("qty") );
		tcost.setCellValueFactory(new PropertyValueFactory<>("tprice") );
		Connection conn= Main.conn;
		try {
			PreparedStatement pre = conn.prepareStatement("select * from bought where name=? and dop=? and top=?;");
			pre.setString(1,Main.u1.getUser_name());
			pre.setString(2,HistoryController.data[0]);
			pre.setString(3,HistoryController.data[1]);
			ResultSet res=pre.executeQuery();
				while(res.next())
				{
					lis.add(new Prod_bo(res.getString("pname"),res.getFloat("price"),res.getFloat("qty"),res.getFloat("tprice")));
					totalcost=totalcost+res.getFloat("tprice");
				}

				t1.setItems(lis);
				ltcost.setText("Total Cost: "+Float.toString(totalcost));


		}catch (Exception e){
			e.printStackTrace();
		}
		}
}
