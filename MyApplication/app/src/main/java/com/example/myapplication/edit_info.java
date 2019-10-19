package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class edit_info extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText repassword;
    private EditText marks1;
    private EditText marks2;
    private EditText marks3;
    private EditText address;
    private Spinner spinner;
    User_data user;
    DatabaseReference ref;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        name = findViewById( R.id.description);
        email = findViewById( R.id.salary);
        password = findViewById( R.id.password );
        repassword = findViewById( R.id.repassword );
        marks1 = findViewById( R.id.marks1 );
        marks2 = findViewById( R.id.marks2 );
        marks3 = findViewById( R.id.marks3 );
        address = findViewById( R.id.address );
        spinner = findViewById( R.id.spinner );
         s=getIntent().getExtras().getString("name");
        ref= FirebaseDatabase.getInstance().getReference("user");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               user=dataSnapshot.child(s).getValue(User_data.class);
                name.setText(user.getName());
                email.setText(user.getEmail());
                marks1.setText(user.getMarks1().toString());
                marks2.setText(user.getMarks2().toString());
                marks3.setText(user.getMarks3().toString());
                address.setText(user.getAddress());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void submit(View view) {
        user.setName(name.getText().toString());
        user.setEmail(email.getText().toString());
        user.setMarks1(Float.parseFloat(marks1.getText().toString()));
        user.setMarks2(Float.parseFloat(marks1.getText().toString()));
        user.setMarks3(Float.parseFloat(marks1.getText().toString()));
        user.setVerify(false);
        int loc=spinner.getSelectedItemPosition();
        if(loc==0)
        user.setGender("Male");
        else
        user.setGender("Female");
        ref.child(s).setValue(user);
        Toast.makeText(getApplication(),"Data Has Been Changed",Toast.LENGTH_LONG).show();
        onBackPressed();
    }
}
