package User;

import Main.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditerController implements Initializable {
    @FXML
    private JFXTextField usr;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField pass;
    @FXML
    private JFXTextField gender;
    @FXML
    private JFXTextField age;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    usr.setText(Main.u1.getUser_name());
    email.setText(Main.u1.getEmail());
    gender.setText(Main.u1.getGender());
    pass.setText(Main.u1.getPassword());
    age.setText(Integer.toString(Main.u1.getAge()));
    }
    @FXML
    public void submit()throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Conformation to Save Changes");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            PreparedStatement pre=Main.conn.prepareStatement("update bought set name=? where name =?");
            pre.setString(1,usr.getText());
            pre.setString(2,Main.u1.getUser_name());
            pre.executeUpdate();
            Main.u1.setPassword(pass.getText());
            Main.u1.setGender(gender.getText());
            Main.u1.setAge(Integer.parseInt(age.getText()));
            Main.u1.setUser_name(usr.getText());
            pre=Main.conn.prepareStatement("update user set email=?,gender=?,password=?,username=?,age=? where email=?;");

            //JFXButton b1=(JFXButton)ev.getSource();
            pre.setString(6,Main.u1.getEmail());
            pre.setString(1,email.getText());
            Main.u1.setEmail(email.getText());
            pre.setString(2,gender.getText());
            pre.setString(3,pass.getText());
            pre.setString(4,usr.getText());
            pre.setString(5,age.getText());
            pre.executeUpdate();

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Succesful");
            alert1.setHeaderText(null);
            alert1.setContentText("I have a great message for you !Your data is updated");
            alert1.showAndWait();
            Stage s1=(Stage)age.getScene().getWindow();
            s1.close();
        } else {
            alert.close();
        }


    }
}
