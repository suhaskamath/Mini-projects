package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UpdatePlaced extends AppCompatActivity {
    RecyclerView recycle;
    UpdatePlacedAdapter mAdapter;
    String company="Cerner",deleted;
    FirebaseDatabase fdb;
    Boolean placed,update;
    DatabaseReference refusr,refupd,refco;
    Button submit;
    int count;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Company c1 =(Company)getIntent().getSerializableExtra("com.example.myapplication.Company");
        company=c1.getCompanyname();
        super.onCreate(savedInstanceState);
        update=Boolean.parseBoolean(getIntent().getExtras().getString("update"));
        placed=Boolean.parseBoolean(getIntent().getExtras().getString("placed"));
        setContentView(R.layout.activity_update_placed);
        txt=findViewById(R.id.txt);
        recycle=findViewById(R.id.recycle1);
        mAdapter=new UpdatePlacedAdapter(UpdatePlaced.this,company,placed);
        recycle.setAdapter(mAdapter);
        submit=findViewById(R.id.submit);
        if(placed==true)
            txt.setText("PLACED STUDENTS");

        if(update==true){
            ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {

                    final int position = viewHolder.getAdapterPosition();

                    switch (direction) {
                        case ItemTouchHelper.LEFT:
                            deleted = mAdapter.data.get(position);
                            mAdapter.data.remove(position);
                            mAdapter.notifyItemRemoved(position);
                            Snackbar.make(recycle,deleted ,Snackbar.LENGTH_LONG)
                                    .setAction("Undo", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mAdapter.data.add(position, deleted);
                                            mAdapter.notifyItemInserted(position);
                                        }
                                    }).show();
                            break;
                        case ItemTouchHelper.RIGHT:
                            final String Name = mAdapter.data.get(position);
                            deleted = mAdapter.data.get(position);

                            mAdapter.data.remove(position);
                            mAdapter.notifyItemRemoved(position);

                            Snackbar.make(recycle, Name + ", Archived.", Snackbar.LENGTH_LONG)
                                    .setAction("Undo", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mAdapter.data.add(position, Name);
                                            mAdapter.notifyItemInserted(position);
                                        }
                                    }).show();

                            break;
                    }
                }};
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
            itemTouchHelper.attachToRecyclerView(recycle);

        }
        else
        {
           submit.setVisibility(Button.INVISIBLE);
        }

    }


    public void submit(View view) {
        fdb=FirebaseDatabase.getInstance();
        refupd=fdb.getReference("placed");
        refupd.child(company).setValue(mAdapter.data);
        refusr=fdb.getReference("user");
        for(String s1:mAdapter.data){
            refusr.child(s1).child("company").child(company).setValue("Placed");
            refusr.child(s1).child("placed").setValue(true);
        }
        refco=fdb.getReference("count");
        refco.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count=dataSnapshot.child("placed").getValue(Integer.TYPE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        refusr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count1=0;
                for(String s1:mAdapter.data){
                    User_data u1=dataSnapshot.child(s1).getValue(User_data.class);
                    if(u1.getPlaced()==false)
                        count1++;

                }
                refco.child("placed").setValue(count+count1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        Toast.makeText(this,"Data Uploaded",Toast.LENGTH_LONG).show();
        onBackPressed();
    }
}
