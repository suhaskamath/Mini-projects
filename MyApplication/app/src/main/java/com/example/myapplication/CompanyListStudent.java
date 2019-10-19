package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class CompanyListStudent extends AppCompatActivity {
    RecyclerView recycle;
    CompanyAdapter2 companyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list_student);
        recycle=findViewById(R.id.recycle1);
       String user= getIntent().getExtras().getString("name");
        companyAdapter=new CompanyAdapter2(this,user);
        recycle.setAdapter(companyAdapter);
    }
}
