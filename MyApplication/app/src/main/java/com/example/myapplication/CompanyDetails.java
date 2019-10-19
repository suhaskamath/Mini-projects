package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CompanyDetails extends AppCompatActivity {
    private TextView profile;
    private TextView salary;
    private TextView description;
    private TextView marks1;
    private TextView marks2;
    private TextView marks3;
    private TextView company;

private  Company post;
    private void findViews() {
        profile = findViewById( R.id.profile );
        salary = findViewById( R.id.salary );
        description = findViewById( R.id.description );
        marks1 = findViewById( R.id.marks1 );
        marks2 = findViewById( R.id.marks2 );
        marks3 = findViewById( R.id.marks3 );
        company = findViewById( R.id.company );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);
        findViews();
        post=(Company) getIntent().getSerializableExtra("com.example.myapplication.Company");
        company.setText(post.getCompanyname().toUpperCase());
        profile.setText("Profile: "+post.getJob());
        salary.setText("Salary :"+post.getSalary()+" LPA");
        marks1.setText("10th Marks:"+post.getTenmarks()+" or more");
        marks2.setText("12th Marks:"+post.getTwelevemarks()+ " or more");
        marks3.setText("BE Marks:"+post.getBemarks()+ " or more");
        description.setText("Description: "+post.getDescription());


    }

}
