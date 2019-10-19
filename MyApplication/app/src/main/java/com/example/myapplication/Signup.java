package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {
    User_data data;
    int count;
    EditText email,pass,re_pass,name,address,marks1,marks2,marks3;
    Button Signup;
    private FirebaseAuth mAuth;
    Spinner gender;
    DatabaseReference ref,refcou;
    FirebaseDatabase fdb;
    int f=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email=findViewById(R.id.salary);
        re_pass=findViewById(R.id.repassword);
        pass=findViewById(R.id.password);
        name=findViewById(R.id.description);
        address=findViewById(R.id.address);
        marks1=findViewById(R.id.marks1);
        marks2=findViewById(R.id.marks2);
        marks3=findViewById(R.id.marks3);
        Signup=findViewById(R.id.signup);
        gender=findViewById(R.id.spinner);
        mAuth=FirebaseAuth.getInstance();

    }
    public void signup(View v){
createfirebaseuser();
    }
    private void createfirebaseuser()
    {
        String email_s=email.getText().toString();
        String pass_s=pass.getText().toString();
        mAuth.createUserWithEmailAndPassword(email_s,pass_s).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("Firebase","creation complete"+task.isSuccessful());

                uploaddata();
                //saveDisplayneame();
                if(!task.isSuccessful()) {
                    Log.d("Firebase", "user creation failed");

                }
                else
                {

                    Intent inte=new Intent(Signup.this,MainActivity.class);
                    finish();
                    startActivity(inte);
                }
            }
        });
    }
    private void uploaddata() {
        String name_s, address_s, gender_s;
        Float m1, m2, m3;
        int i = gender.getSelectedItemPosition();
        if (i == 0)
            gender_s = "Male";
        else
            gender_s = "Female";
        name_s = name.getText().toString();
        address_s = address.getText().toString();
        m1 = Float.parseFloat(marks1.getText().toString());
        m2 = Float.parseFloat(marks2.getText().toString());
        m3 = Float.parseFloat(marks3.getText().toString());
        data = new User_data(name_s, email.getText().toString(), gender_s, address_s, m1, m2, m3, false,null,false);
        fdb = FirebaseDatabase.getInstance();
        ref = fdb.getReference("user");
        refcou=fdb.getReference("count/total");
        refcou.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(f==0) {
                    count = dataSnapshot.getValue(Integer.TYPE);
                    refcou.setValue(count + 1);
                    f=1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        String tmp = email.getText().toString();
        String t1 = "";
        int k = 0;
        while (tmp.charAt(k) != '@') {
            t1 += tmp.charAt(k++);
        }
        ref.child(t1).setValue(data);
        ref=fdb.getReference("count");

        Toast.makeText(this, "Data Uploaded", Toast.LENGTH_LONG).show();
    }
}
