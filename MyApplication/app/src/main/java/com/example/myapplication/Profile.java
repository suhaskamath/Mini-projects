package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    TextView email,gender,name,marks1,marks2,marks3,status,address;
    DatabaseReference ref,refcomp;
    FirebaseDatabase fdb;
    String send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final String email_s=getIntent().getExtras().getString("name");
        email=findViewById(R.id.salary);
        gender=findViewById(R.id.gender);
        name=findViewById(R.id.description);
        marks1=findViewById(R.id.marks1);
        marks2=findViewById(R.id.marks2);
        marks3=findViewById(R.id.marks3);
        status=findViewById(R.id.status);
        address=findViewById(R.id.address);
        fdb= FirebaseDatabase.getInstance();
        ref=fdb.getReference("user");


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                String t="";
                int k=0;
                while(k!=email_s.length()&&email_s.charAt(k)!='@')
                    t+=email_s.charAt(k++);
                send=t;
                User_data post = dataSnapshot.child(t).getValue(User_data.class);
                //dataSnapshot.child("user")
                gender.setText("Gender: " + post.getGender());
                name.setText("Name: " + post.getName());
                marks1.setText("10th Marks: " + post.getMarks1());
                marks2.setText("10th Marks: " + post.getMarks2());
                marks3.setText("10th Marks: " + post.getMarks3());
                address.setText("Address: "+post.getAddress());
                status.setText("Status: "+post.getVerify());
                email.setText("Email: " + post.getEmail());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("hj", "loadPost:onCancelled", databaseError.toException());

            }
        };
        ref.addValueEventListener(postListener);

    }

    public void back(View view) {
onBackPressed();
/*
Intent myint=new Intent(Profile.this,Student_Home.class);
Log.d("click1","Profile Home");
myint.putExtra("email",email.getText().toString());
startActivity(myint);
        */
    }

    public void edit(View view) {
        Intent myint=new Intent(Profile.this,edit_info.class);
        Log.d("click1","Profile Home");
        myint.putExtra("name",send);
        startActivity(myint);

    }
}
