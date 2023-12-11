package com.example.bookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME ="Book.db";
        private static final int DATABASE_VERSION =1;

        private static final String TABLE_NAME ="library";
        private static final String COLUMN_ID ="column_Id";
        private static final String BOOK_NAME ="book_name";
        private static final String AUTHOR_NAME ="author_name";
        private static final String PAGES ="pages";

        private Context context;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_NAME+
                        "("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                         BOOK_NAME+" TEXT,"+
                         AUTHOR_NAME+" TEXT,"+
                         PAGES+" INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    void addBook(String title,String author,int pages ){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues  cv = new ContentValues();

            cv.put(BOOK_NAME,title);
            cv.put(AUTHOR_NAME,author);
            cv.put(PAGES,pages);

            long result = db.insert(TABLE_NAME,null,cv);
            if(result==-1){
                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "successfully", Toast.LENGTH_SHORT).show();
            }
    }

    Cursor readData(){

        String query = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if(db!=null){
            cursor = db.rawQuery(query,null);
        }
        return  cursor;
    }

    void updateData(String id,String name,String author,String pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID,id);
        cv.put(BOOK_NAME,name);
        cv.put(AUTHOR_NAME,author);
        cv.put(PAGES,pages);

       long result = db.update(TABLE_NAME,cv,"column_Id=?",new String[]{id});
       if(result==-1){
           Toast.makeText(context,"Not Successful",Toast.LENGTH_SHORT).show();
       }else{
           Toast.makeText(context,"Successfull",Toast.LENGTH_SHORT).show();
       }
    }

    void deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();

       long result = db.delete(TABLE_NAME,"column_Id=?",new String[]{id});
        if(result==-1){
            Toast.makeText(context,"Not Successful",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Successfull",Toast.LENGTH_SHORT).show();
        }
    }
}
