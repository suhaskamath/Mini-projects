package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
public class Verify_Student extends AppCompatActivity {
    RecyclerView r1;
    Recycler_Adapter recycleadapter;
    TextView text;
boolean b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify__student);
        r1=findViewById(R.id.recycle1);
        String s1=getIntent().getExtras().getString("status");
        text=findViewById(R.id.status);
        b1=Boolean.parseBoolean(s1);
        if(b1)
            text.setText("Verified Profiles");
        else
            text.setText("Verify Profiles");
        recycleadapter=new Recycler_Adapter(this,b1);
        r1.setAdapter(recycleadapter);

    }

}

