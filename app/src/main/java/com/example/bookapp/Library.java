package com.example.bookapp;

public class Library {

    public Library(String column_id, String book_name, String book_author, String book_pages) {
        this.column_id = column_id;
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_pages = book_pages;
    }

    public String getColumn_id() {
        return column_id;
    }

    public void setColumn_id(String column_id) {
        this.column_id = column_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_pages() {
        return book_pages;
    }

    public void setBook_pages(String book_pages) {
        this.book_pages = book_pages;
    }

    @Override
    public String toString() {
        return "Library{" +
                "column_id='" + column_id + '\'' +
                ", book_name='" + book_name + '\'' +
                ", book_author='" + book_author + '\'' +
                ", book_pages='" + book_pages + '\'' +
                '}';
    }

    private String column_id;
    private String book_name;
    private String book_author;

    private String book_pages;




}
