package com.example.bookapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

        EditText input_name,input_author,input_pages;
        Button  update,delete;

        String id,title,author,pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        input_name = findViewById(R.id.book_name2);
        input_author = findViewById(R.id.author_name2);
        input_pages = findViewById(R.id.book_pages2);
        update = findViewById(R.id.update_btn);
        delete = findViewById(R.id.delete_btn);

     //get and set data
        getAndAddData();

        ActionBar ab = getSupportActionBar();
        if(ab!=null){
            ab.setTitle(title);
        }

        DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = input_name.getText().toString().trim();
                author = input_author.getText().toString().trim();
                pages = input_pages.getText().toString().trim();


                db.updateData(id,title,author,pages);

                Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteData(id);

                Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    void getAndAddData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name")
                && getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {

            //getting data from intent
            id =getIntent().getStringExtra("id");
            title =getIntent().getStringExtra("name");
            author =getIntent().getStringExtra("author");
            pages =getIntent().getStringExtra("pages");

            //setting data from intent
            input_name.setText(title);
            input_author.setText(author);
            input_pages.setText(pages);
        }else{
            Toast.makeText(UpdateActivity.this,"No data",Toast.LENGTH_SHORT).show();
        }
    }
}