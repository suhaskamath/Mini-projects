package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CompanyStatus extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_status);
    }

    public void update(View view) {
        Intent i1=new Intent(getApplicationContext(),UpdateHome.class);
        i1.putExtra("update","true");
        i1.putExtra("placed","false");
        startActivity(i1);
    }

    public void place(View view) {
        Intent i1=new Intent(getApplicationContext(),UpdateHome.class);
        i1.putExtra("update","false");
        i1.putExtra("placed","true");
        startActivity(i1);
    }

    public void applystu(View view) {
        Intent i1=new Intent(getApplicationContext(),UpdateHome.class);
        i1.putExtra("update","false");
        i1.putExtra("placed","false");
        startActivity(i1);
    }
}
