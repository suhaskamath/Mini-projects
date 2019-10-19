package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Progress extends AppCompatActivity {
    private TextView company;
    private TextView status;
    private TextView info;
    private String user;
    Map<String,String>m1;
private Company post;
long count;
    DatabaseReference ref,refcomp;
    FirebaseDatabase fdb;
    private User_data user_data;
    private void findViews() {
        company = findViewById( R.id.company );

        status = findViewById( R.id.status );
        info = findViewById( R.id.info );
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
findViews();
        post=(Company) getIntent().getSerializableExtra("com.example.myapplication.Company");
        company.setText(post.getCompanyname().toUpperCase());
        user=getIntent().getExtras().getString("user");
        fdb= FirebaseDatabase.getInstance();
        ref=fdb.getReference("user");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                user_data = dataSnapshot.child(user).getValue(User_data.class);
                if(isEligible()) {
                    m1 = user_data.getCompany();
                    boolean flag = false;
                    if (m1.size() > 0)
                        for (Map.Entry<String, String> entry : m1.entrySet()) {
                            if (entry.getKey().equals(post.getCompanyname())) {
                                flag = true;
                                status.setText(entry.getValue());
                                break;
                            }
                        }
                    if (user_data.getVerify() == true) {
                        if (!flag)
                            status.setText("Eligible but not applied");
                    } else {
                        status.setText("Not Eligible For this Company!");
                    }
                }
                else
                {
                    status.setText("Profile Not Yet Verified");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("hj", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        ValueEventListener companylistener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                count=(Long)dataSnapshot.child(post.getCompanyname()).child("count").getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        ref.addValueEventListener(postListener);
        refcomp=fdb.getReference("applied companies");
        refcomp.addValueEventListener(companylistener);
    }

    public void moreinfo(View view) {
        Intent i1=new Intent(getApplicationContext(),CompanyDetails.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("com.example.myapplication.Company", post);
        i1.putExtras(bundle);
        startActivity(i1);

    }
private boolean find_company(){
        m1=user_data.getCompany();
        if(m1.size()>0)
        for (Map.Entry<String,String> entry : m1.entrySet()) {
            if(entry.getKey().equals(post.getCompanyname()))
                return false;
        }
        return true;

}
private boolean isEligible(){
        if(post.getTenmarks()>user_data.getMarks1())
            return false;
        if(post.getTwelevemarks()>user_data.getMarks2())
            return false;
        if(post.getBemarks()>user_data.getMarks3())
            return false;
        return true;
}
    public void apply(View view) {
        if(user_data.getVerify()==true){
        if (isEligible()) {
            if (find_company()) {
                ref.child(user).child("company").child(post.getCompanyname()).setValue("Applied");
                refcomp.child(post.getCompanyname()).child("Student").child(user).setValue(user);
                refcomp.child(post.getCompanyname()).child("Count").setValue(count + 1);
                status.setText("Applied");
            } else {
                Toast.makeText(getApplicationContext(), "Already applied", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Not Eligible", Toast.LENGTH_SHORT).show();
        }}
        else
        {
            Toast.makeText(getApplicationContext(), "Profile not yet Verified", Toast.LENGTH_SHORT).show();
        }
    }

}
