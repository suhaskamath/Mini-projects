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

import java.util.Map;

public class Student_Home extends AppCompatActivity {
TextView name,total_placed,total_applied;
    DatabaseReference ref,refco;
    FirebaseDatabase fdb;
    int total_co=10,total_app=0,total_plac=0;
    String t="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__home);
        total_applied=findViewById(R.id.total_applied);
        total_placed=findViewById(R.id.total_placed);
       final String email_s=getIntent().getExtras().getString("email");

        int k=0;
        while(email_s.charAt(k)!='@')
            t+=email_s.charAt(k++);
        name=findViewById(R.id.description);
        fdb= FirebaseDatabase.getInstance();
        ref=fdb.getReference("user");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                User_data post = dataSnapshot.child(t).getValue(User_data.class);
                    //dataSnapshot.child("user")
                name.setText(post.getName());
                total_plac=0;
                total_app=0;
                if(post.getCompany().size()>0)
                for (Map.Entry<String,String> entry : post.getCompany().entrySet()){
                    if(entry.getValue().equals("Placed")){
                        total_plac++;
                        total_app++;
                    }
                    else if(entry.getValue().equals("Applied")){
                        total_app++;
                    }
            }
                total_applied.setText("Total Applied: "+total_app+"/9");
            total_placed.setText("Total Placed:"+total_plac+"/9");
        }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("hj", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        ref.addValueEventListener(postListener);

    }

    public void view_company(View view) {
        Intent myint=new Intent(Student_Home.this,CompanyListStudent.class);
        Log.d("Click","Clicked");
        // String s=name.getText().toString();
        myint.putExtra("name",t);
        startActivity(myint);
    }

    public void profile_edit(View view) {
        Intent myint=new Intent(Student_Home.this,edit_info.class);
        Log.d("Click","Clicked");
        // String s=name.getText().toString();
        myint.putExtra("name",t);
        startActivity(myint);
    }

    public void view_details(View view) {
        Intent myint=new Intent(Student_Home.this,Profile.class);
        Log.d("Click","Clicked");
       // String s=name.getText().toString();
        myint.putExtra("name",t);
        startActivity(myint);
    }
}
