package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class UpdatePlacedAdapter extends RecyclerView.Adapter<UpdatePlacedAdapter.ViewHolder>{
    FirebaseDatabase fdb;
    Context context;
    DatabaseReference refcomp;
    public ArrayList<String> data;
    Boolean placed;
    String company;

    @NonNull
    @Override
    public UpdatePlacedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf=LayoutInflater.from(parent.getContext());
        View view=inf.inflate(R.layout.row_item,parent,false);
        UpdatePlacedAdapter.ViewHolder viewHolder=new UpdatePlacedAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UpdatePlacedAdapter.ViewHolder holder, int position) {
        String s1=data.get(position);
        holder.mTextView.setText(s1);
        holder.rowcount.setText("");
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView mTextView, rowcount;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.textView);
            rowcount = itemView.findViewById(R.id.rowcount);

            img.setImageResource(R.drawable.user);


        }


    }
    @Override
    public int getItemCount() {
        Log.d("Size",String.valueOf(data.size())) ;
        return data.size();
    }
    public UpdatePlacedAdapter(Context c1, String data1, final Boolean placed)
    {
        company=data1;
        fdb= FirebaseDatabase.getInstance();
        this.context=c1;
        this.placed=placed;
        data=new ArrayList<>();
        fdb= FirebaseDatabase.getInstance();
        if(placed==true)
            refcomp=fdb.getReference("placed/"+company);
        else
            refcomp=fdb.getReference("applied companies/"+company+"/Student");
        ChildEventListener mListner=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               if(placed==true)
                    data.add((String)dataSnapshot.getValue());
               else
                    data.add(dataSnapshot.getKey());
                notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                data.remove(dataSnapshot.getKey());
                notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        refcomp.addChildEventListener(mListner);
    }
}
