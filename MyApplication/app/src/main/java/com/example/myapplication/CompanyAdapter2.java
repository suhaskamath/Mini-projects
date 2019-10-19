package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CompanyAdapter2 extends RecyclerView.Adapter<CompanyAdapter2.ViewHolder> {
    DatabaseReference ref;
    FirebaseDatabase fdb;
    Context context;
    String user="";
    private List<DataSnapshot> data;
    private ChildEventListener mListner=new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            data.add(dataSnapshot);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            data.remove(dataSnapshot);
            notifyDataSetChanged();
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    @NonNull
    @Override
    public CompanyAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf=LayoutInflater.from(parent.getContext());
        View view=inf.inflate(R.layout.row_item,parent,false);
        CompanyAdapter2.ViewHolder viewHolder=new CompanyAdapter2.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyAdapter2.ViewHolder holder, int position) {
        Company u1=data.get(position).getValue(Company.class);
        holder.mTextView.setText(u1.getCompanyname());
        holder.rowcount.setText("Salary: "+u1.getSalary());
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView mTextView, rowcount;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.textView);
            rowcount = itemView.findViewById(R.id.rowcount);
            itemView.setOnClickListener(this);
            img.setImageResource(R.drawable.enterprise);


        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context,Progress.class);
            Company u1=new Company();
            for (DataSnapshot d:data) {
                u1=d.getValue(Company.class);
                if(u1.getCompanyname()==mTextView.getText().toString()){

                    break;
                }
            }

            Bundle bundle = new Bundle();
            bundle.putSerializable("com.example.myapplication.Company", u1);
            intent.putExtra("user",user);
            intent.putExtras(bundle);
            context.startActivity(intent);
            Toast.makeText(context,mTextView.getText().toString(),Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public int getItemCount() {
        Log.d("Size",String.valueOf(data.size())) ;
        return data.size();
    }
    public CompanyAdapter2(Context c1,String user)
    {
        super();
        this.user=user;
        data=new ArrayList<>();
        fdb= FirebaseDatabase.getInstance();
        ref=fdb.getReference("company");
        ref.addChildEventListener(mListner);
        this.context=c1;
    }


}
