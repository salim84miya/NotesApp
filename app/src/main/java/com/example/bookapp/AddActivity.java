package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText input_name;
    EditText input_author;
    EditText input_pages;

    Button submit_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        input_name = findViewById(R.id.book_name);
        input_author = findViewById(R.id.author_name);
        input_pages =findViewById(R.id.book_pages);

        submit_btn = findViewById(R.id.submit_btn);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper mydb = new DatabaseHelper(AddActivity.this);

                mydb.addBook(input_name.getText().toString().trim(),
                             input_author.getText().toString().trim(),
                             Integer.valueOf(input_pages.getText().toString().trim()));

                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}