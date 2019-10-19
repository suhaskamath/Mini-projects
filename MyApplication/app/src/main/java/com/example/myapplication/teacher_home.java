package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class teacher_home extends AppCompatActivity {
TextView result;
FirebaseDatabase fdb;
DatabaseReference ref;
int placed,total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);
        result=findViewById(R.id.total);
        fdb=FirebaseDatabase.getInstance();
        ref=fdb.getReference("count");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              total=dataSnapshot.child("total").getValue(Integer.TYPE);
              placed=dataSnapshot.child("placed").getValue(Integer.TYPE);
              result.setText("Total Placed: "+placed+"/"+total);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void view_company(View view) {
        Intent i1=new Intent(getApplicationContext(),company_list.class);
        startActivity(i1);
    }

    public void verifyprofile(View view) {
        Intent i1=new Intent(getApplicationContext(),Verify_Student.class);
        i1.putExtra("status","false");
        startActivity(i1);
    }

    public void addcompany(View view) {
        Intent i1=new Intent(getApplicationContext(),AddCompany.class);
        startActivity(i1);
    }

    public void verified(View view) {
        Intent i1=new Intent(getApplicationContext(),Verify_Student.class);
        i1.putExtra("status","true");
        startActivity(i1);
    }

    public void placement_details(View view) {
        Intent i1=new Intent(getApplicationContext(),CompanyStatus.class);
        startActivity(i1);
    }
}
