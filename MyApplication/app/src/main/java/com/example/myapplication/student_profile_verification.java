package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class student_profile_verification extends AppCompatActivity {
   private TextView email,gender,name,marks1,marks2,marks3,status,address;
    UserData post;
    private Button accept,reject;
    private boolean verify=false;
    FirebaseDatabase fdb;
    DatabaseReference ref;
    boolean b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile_verification);
        email = findViewById(R.id.salary);
        gender = findViewById(R.id.gender);
        name = findViewById(R.id.description);
        marks1 = findViewById(R.id.marks1);
        marks2 = findViewById(R.id.marks2);
        marks3 = findViewById(R.id.marks3);
        status = findViewById(R.id.status);
        address = findViewById(R.id.address);
        post = (UserData) getIntent().getParcelableExtra("com.example.myapplication.UserData");
        gender.setText("Gender: " + post.getGender());
        name.setText("Name: " + post.getName());
        marks1.setText("10th Marks: " + post.getMarks1());
        marks2.setText("10th Marks: " + post.getMarks2());
        marks3.setText("10th Marks: " + post.getMarks3());
        address.setText("Address: " + post.getAddress());
        status.setText("Status: " + post.getVerify());
        email.setText("Email: " + post.getEmail());
        accept = findViewById(R.id.accept);
        reject = findViewById(R.id.reject);
        b1=Boolean.parseBoolean(getIntent().getExtras().getString("status"));
        if (b1 == true) {
            accept.setVisibility(View.INVISIBLE);
            reject.setVisibility(View.INVISIBLE);
        }
    }



    public void verify(View view) {
        if(view==accept){
            verify=true;
        }
        else
            verify=false;
        fdb= FirebaseDatabase.getInstance();
        ref=fdb.getReference("user");
        String t1="",tmp=post.getEmail();
        int k=0;
        while(tmp.charAt(k)!='@'){
            t1+=tmp.charAt(k++);
        }
        ref.child(t1).child("verify").setValue(verify);
        super.onBackPressed();
    }

}
