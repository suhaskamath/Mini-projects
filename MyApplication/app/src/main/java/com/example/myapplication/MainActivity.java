package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
Switch mswitch;
EditText email,pass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    mswitch=findViewById(R.id.switch1);
    email=findViewById(R.id.salary);
    pass=findViewById(R.id.pass);
    mAuth=FirebaseAuth.getInstance();
    email.setText("adminnhce@gmail.com");
    pass.setText("admin123");
    }
    public void signup(View v)
    {
        Intent myint=new Intent(MainActivity.this,Signup.class);
        startActivity(myint);
    }
    public void login(View v){
        String email_s=email.getText().toString(),pass_s=pass.getText().toString();
        if(mswitch.isChecked()){

            mAuth.signInWithEmailAndPassword(email_s,pass_s).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful())
                    {

                        Log.d("Firebase","Problem in signin"+task.getException());
                        Toast.makeText(MainActivity.this,"Unable to login",Toast.LENGTH_LONG).show();



                    }
                    else
                    {
                        Intent myint = new Intent(MainActivity.this,Student_Home .class);
                        myint.putExtra("email",email.getText().toString());
                        startActivity(myint);
                        Log.d("Firebase","Login Successful");


                    }
                }
            });
        }
        else {
            if (email_s.equals("adminnhce@gmail.com")) {

                mAuth.signInWithEmailAndPassword(email_s,pass_s).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Log.d("Firebase","Problem in signin"+task.getException());
                            Toast.makeText(MainActivity.this,"Unable to login",Toast.LENGTH_LONG).show();



                        }
                        else
                        {
                            Log.d("Firebase","Login Successful");
                            Intent myint = new Intent(MainActivity.this, teacher_home.class);
                            startActivity(myint);
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Wrong Email Not Admin", Toast.LENGTH_LONG).show();
            }
        }
    }

}
