package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignUpController {
	@FXML
	private TextField name;
	@FXML
	private TextField password;
	@FXML
	private TextField email;
	@FXML
	private TextField gender;
	@FXML
	private TextField salary;
	@FXML
	private Label emailer;
	@FXML
	private Label passer;
	@FXML
	private Label gener;
	@FXML
	private TextField age;

	private String cat;
	private String score;
	// Event Listener on JFXButton[#SignUp1].onAction
	@FXML
	public void register(ActionEvent event) throws Exception{
		if(email.getText().contains("@")&&password.getText().length()>6) {
			get();
			//emailer.setText(cat+"hello");
			 Class.forName("org.sqlite.JDBC");  // Loading and registering the driver.

	            Connection conn = null;
	            conn = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
	            PreparedStatement prep=conn.prepareStatement("insert into user values(?,?,?,?,?,?,?,?,?);");
	            prep.setString(1,name.getText());
	            prep.setString(2,password.getText());
	            prep.setString(3,age.getText());
	            prep.setString(4,salary.getText());
	            prep.setString(5,gender.getText());
	            prep.setString(6,score);
	            prep.setString(7,cat);
	            prep.setString(8,email.getText());
	            prep.setString(9,Float.toString(1500));
			prep.executeUpdate();
	}
}
	public void get() throws IOException, InterruptedException {
		/* File file = new File("C:\\Users\\Suhas\\eclipse-workspace\\Mini_proj1\\inp.csv"); 
	        // create FileWriter object with file as parameter 
	        FileWriter outputfile = new FileWriter(file); 
	  
	        // create CSVWriter object filewriter object as parameter 
	        CSVWriter writer = new CSVWriter(outputfile); 
	  
	        // adding header to csv 
	        String[] header = { "Gender", "Age", "Salary" }; 
	        writer.writeNext(header); 
	  
	        // add data to csv 
	        String[] data1 = { gender.getText(),age.getText() , salary.getText() }; 
	        writer.writeNext(data1); 
	  
	        // closing writer connection 
	        writer.close(); 
	    
		String[] command =
	    {
	        "cmd",
	    };
	    Process p;
			p = Runtime.getRuntime().exec(command);
		
		
	    Thread t1=new Thread(new SyncPipe(p.getErrorStream(), System.err));
	    Thread t2=new Thread(new SyncPipe(p.getInputStream(), System.out));
	    PrintWriter stdin = new PrintWriter(p.getOutputStream());
	    stdin.println("python svm.py");
	    
	       t1.start();
	       t2.start();
	    stdin.close();
	    t1.join();
	    t2.join();
		file = new File("C:\\Users\\Suhas\\eclipse-workspace\\Mini_proj1\\op.csv"); 
     FileReader fr = null;

     fr = new FileReader(file);

     String res = null;
     BufferedReader br = new BufferedReader(fr);

     String line;
     int f=0;
     while((line = br.readLine()) != null) {
         res = line;
         String[] s=res.split(",");
         System.out.println(res);
         
         if(f==1)
         cat=s[1];
         else if(f==2)
         score=s[1];
         f++;
     }
     br.close();*/
		if(gender.getText().equalsIgnoreCase("Male")&&Integer.parseInt(age.getText())<45&&Integer.parseInt(salary.getText())<45) {
			cat="B";
			score="55";
		}
		if(gender.getText().equalsIgnoreCase("Male")&&Integer.parseInt(age.getText())<45&&Integer.parseInt(salary.getText())>45) {
			cat="A";
			score="80";
		}
		if(gender.getText().equalsIgnoreCase("Male")&&Integer.parseInt(age.getText())<45&&Integer.parseInt(salary.getText())>45) {
			cat="A";
			score="80";
		}
		if(gender.getText().equalsIgnoreCase("Male")&&Integer.parseInt(age.getText())>45&&Integer.parseInt(salary.getText())>45) {
			cat="B";
			score="60";
		}
		if(gender.getText().equalsIgnoreCase("Male")&&Integer.parseInt(age.getText())>45&&Integer.parseInt(salary.getText())<45) {
			cat="C";
			score="35";
		}
		if(gender.getText().equalsIgnoreCase("Female")&&Integer.parseInt(age.getText())<45&&Integer.parseInt(salary.getText())<45) {
			cat="A";
			score="75";
		}
		if(gender.getText().equalsIgnoreCase("Female")&&Integer.parseInt(age.getText())<45&&Integer.parseInt(salary.getText())>45) {
			cat="A";
			score="80";
		}
		if(gender.getText().equalsIgnoreCase("Female")&&Integer.parseInt(age.getText())<45&&Integer.parseInt(salary.getText())>45) {
			cat="A";
			score="80";
		}
		if(gender.getText().equalsIgnoreCase("Female")&&Integer.parseInt(age.getText())>45&&Integer.parseInt(salary.getText())>45) {
			cat="A";
			score="80";
		}
		if(gender.getText()=="Female"&&Integer.parseInt(age.getText())>45&&Integer.parseInt(salary.getText())<45) {
			cat="B";
			score="45";
		}
}
	}
	

