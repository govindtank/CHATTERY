package com.saumya.chattery;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    public List<Contacts> cont;
    Contacts list;
    private ArrayList<Contacts> arraylist;
    boolean checked = false;
    View vv;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;



    public RecyclerAdapter(LayoutInflater inflater, List<Contacts> items) {
        this.layoutInflater = inflater;
        this.cont = items;
        this.arraylist = new ArrayList<Contacts>();
        this.arraylist.addAll(cont);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = layoutInflater.inflate(R.layout.contactlist_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        list = cont.get(position);
        final String name = (list.getName());


        firebaseDatabase = FirebaseDatabase.getInstance("https://chattery-23cb9.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference("Chatter/" );

        holder.title.setText(name);
        holder.phone.setText(list.getPhone());


        holder.contactCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), HomeChattingActivity.class) ;

                Bundle bundle = new Bundle();
                bundle.putString("FriendName", name);
                bundle.putString("FriendPhone",list.getPhone());
                intent.putExtras(bundle);
                Log.e("Friend", name + list.getPhone());
                view.getContext().startActivity(intent);

                //intent.putExtra("Name",name);
                //intent.putExtra("Phone",list.getPhone());

            }
        });

        holder.contactCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                //code to show multi option on long click
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return cont.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView phone;
        CardView contactCard;
        public LinearLayout contact_select_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.setIsRecyclable(false);
            title = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView) itemView.findViewById(R.id.no);
            contactCard = itemView.findViewById(R.id.contactCard);
            contact_select_layout = (LinearLayout) itemView.findViewById(R.id.contact_select_layout);


        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
