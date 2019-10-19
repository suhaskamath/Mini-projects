package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class UpdateHome extends AppCompatActivity {


    RecyclerView recycle;
    UpdateHomeAdapter UpdateHomeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_home);
        recycle = findViewById(R.id.recycle1);
        UpdateHomeAdapter = new UpdateHomeAdapter(this,getIntent().getExtras().getString("update"),getIntent().getExtras().getString("placed"));
        recycle.setAdapter(UpdateHomeAdapter);
    }
}