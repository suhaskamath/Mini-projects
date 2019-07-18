package Main;

import java.util.HashMap;

public class User {
	private String user_name;
	private float balance;
	private float bill_amt;
	private String Category;
	private String password;
	private String email;
	private String phone;
	private String gender;
	private float Salary;
	private float score;
	private int age;
	public float getBill_amt() {
		return bill_amt;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public String getGender() {
		return gender;
	}
	public float getSalary() {
		return Salary;
	}
	public float getScore() {
		return score;
	}
	public int getAge() {
		return age;
	}
	public User(String user_name, float balance,  String category, String password, String email
			, String gender, float salary, float score, int age) {
		super();
		this.user_name = user_name;
		this.balance = balance;
		Category = category;
		this.password = password;
		this.email = email;
		this.gender = gender;
		Salary = salary;
		this.score = score;
		this.age = age;
	}


	//Creation of new user
	User(String user_name,Float balance,String Cate){
		this.user_name=user_name;
		this.balance=balance;
		bill_amt=0f;
		this.Category=Cate;
		
	}
 User() {
		this.user_name = null;
		
		this.balance=0.0f;
		bill_amt=0.0f;
	}
 //To login and set the values
	public String getUser_name() {
		return user_name;
	}
	public String getCategory() {
		return Category;
	}
	public Float getBalance() {
		return this.balance;
	}
	public Float getBill_Amount() {
		return bill_amt;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public void setBill_Amount(float bill_amt) {
		this.bill_amt = bill_amt;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
