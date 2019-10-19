package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class company_list extends AppCompatActivity {
RecyclerView recycle;
CompanyAdapter companyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);
        recycle=findViewById(R.id.recycle1);
        companyAdapter=new CompanyAdapter(this);
        recycle.setAdapter(companyAdapter);
//       Toast.makeText(this,companyAdapter.getItemCount(),Toast.LENGTH_SHORT).show();
    }
}
