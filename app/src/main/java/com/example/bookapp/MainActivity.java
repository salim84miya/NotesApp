package com.example.bookapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BookAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton add_btn;

    DatabaseHelper db;
    ArrayList<Library> library;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Base_Theme_BookApp);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent,1);
            }
        });
        db = new DatabaseHelper(MainActivity.this);
        library = new ArrayList<>();

        addDataAndRead();



        adapter = new BookAdapter(MainActivity.this,MainActivity.this,library);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    void addDataAndRead(){
        Cursor cursor =db.readData();

        if(cursor.getCount()==0){
            Toast.makeText(MainActivity.this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                library.add(new Library(cursor.getString(0) ,cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }
        }
    }

}