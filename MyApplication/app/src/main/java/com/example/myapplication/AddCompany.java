package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class AddCompany extends AppCompatActivity {

    private EditText companyname;
    private EditText salary;
    private EditText tenmarks;
    private EditText twelevemarks;
    private EditText bemarks;
    private EditText job;
    DatabaseReference ref,compref;
    FirebaseDatabase fdb;
    Company mcompany;
    private EditText description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);
        companyname = findViewById( R.id.companyname );
        salary = findViewById( R.id.salary );
        tenmarks = findViewById( R.id.tenmarks );
        twelevemarks = findViewById( R.id.twelevemarks );
        bemarks = findViewById( R.id.bemarks );
        job = findViewById( R.id.job );
        fdb=FirebaseDatabase.getInstance();
        ref=fdb.getReference("company");
        description=findViewById(R.id.description);
        compref=fdb.getReference("applied companies");
    }
    public class data{
        String name;
        Map<String,String>Student;
            int Count;
        public data(String name,int Count)
        {
            this.name=name;
            this.Count=Count;
            Student=null;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<String, String> getStudent() {
            return Student;
        }

        public void setStudent(Map<String, String> student) {
            Student = student;
        }

        public int getCount() {
            return Count;
        }

        public void setCount(int count) {
            Count = count;
        }
    }
    public void submit(View view) {
        mcompany=new Company(description.getText().toString(),companyname.getText().toString(),Float.parseFloat(salary.getText().toString()),Float.parseFloat(tenmarks.getText().toString()),Float.parseFloat(twelevemarks.getText().toString()),Float.parseFloat(bemarks.getText().toString()),job.getText().toString());
        ref.child(companyname.getText().toString()).setValue(mcompany);
        data d1=new data(companyname.getText().toString(),0);
        compref.child(companyname.getText().toString()).setValue(d1);
        Toast.makeText(this,"Data Uploaded",Toast.LENGTH_LONG).show();
    }
}
