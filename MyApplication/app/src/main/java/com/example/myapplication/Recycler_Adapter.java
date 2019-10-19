package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.ViewHolder>{
   private  boolean status;
 //   int co=0;
    DatabaseReference ref;
    FirebaseDatabase fdb;
    Context context;
    private List<DataSnapshot> data;
    private ChildEventListener mListner=new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            UserData u1=dataSnapshot.getValue(UserData.class);
            if(u1.getVerify()==status) {

                data.add(dataSnapshot);
                notifyDataSetChanged();
            }
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            UserData u1=dataSnapshot.getValue(UserData.class);

            if(status==false&&u1.getVerify()==true){
                data.remove(dataSnapshot);
                notifyDataSetChanged();
            }
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
    public Recycler_Adapter(Context c1,boolean status)
    {
        this.status=status;
        context=c1;
        data=new ArrayList<>();
        fdb=FirebaseDatabase.getInstance();
        ref=fdb.getReference("user");
        ref.addChildEventListener(mListner);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inf=LayoutInflater.from(parent.getContext());
        View view=inf.inflate(R.layout.row_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserData u1=data.get(position).getValue(UserData.class);
        holder.mTextView.setText(u1.getName());
        holder.rowcount.setText(u1.getEmail());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img;
        TextView mTextView,rowcount;
        public ViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imageView);
            mTextView=itemView.findViewById(R.id.textView);
            rowcount=itemView.findViewById(R.id.rowcount);
            itemView.setOnClickListener(this);
            img.setImageResource(R.drawable.user);


        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(v.getContext(),student_profile_verification.class);
            UserData u1=new UserData();

            for (DataSnapshot d:data) {
                u1=d.getValue(UserData.class);
                if(u1.getName()==mTextView.getText().toString()){

                    break;
                }
            }
            intent.putExtra("status",String.valueOf(status));
            Bundle bundle = new Bundle();
            bundle.putParcelable("com.example.myapplication.UserData",u1);
    intent.putExtras(bundle);
    context.startActivity(intent);
            Toast.makeText(v.getContext(),mTextView.getText().toString(),Toast.LENGTH_SHORT).show();
        }
    }

}
