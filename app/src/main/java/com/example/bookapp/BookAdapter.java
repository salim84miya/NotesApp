package com.example.bookapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList<Library> library;
    int position;
    public BookAdapter (Activity activity,Context context,ArrayList<Library> library){
        this.context = context;
        this.library = library;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position = position;

        holder.text_id.setText(String.valueOf(library.get(position).getColumn_id()));
        holder.text_title.setText(String.valueOf(library.get(position).getBook_name()));
        holder.text_author.setText(String.valueOf(library.get(position).getBook_author()));
        holder.text_pages.setText(String.valueOf(library.get(position).getBook_pages()));
        holder.row_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(library.get(position).getColumn_id()));
                intent.putExtra("name",String.valueOf(library.get(position).getBook_name()));
                intent.putExtra("author",String.valueOf(library.get(position).getBook_author()));
                intent.putExtra("pages",String.valueOf(library.get(position).getBook_pages()));
                activity.startActivityForResult(intent,1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return library.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView text_id,text_title,text_author,text_pages;
        ConstraintLayout row_layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            text_id= itemView.findViewById(R.id.sr_no);
            text_title = itemView.findViewById(R.id.title);
            text_author =itemView.findViewById(R.id.author);
            text_pages = itemView.findViewById(R.id.pages);
            row_layout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
