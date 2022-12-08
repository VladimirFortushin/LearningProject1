package com.example.demo.book;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Book {


    private int book_id;

    @NotEmpty(message = "Name of book is empty")
    @Size(min = 2, max = 100, message ="Book name should be between 2 and 100 characters")
    private String name;
    @NotEmpty(message = "Name of author is empty")
    @Size(min = 2, max = 100, message ="Author name should be between 2 and 100 characters")
    private String author;

    @NotNull(message = "Year is empty")
    @Min(value = 1000, message = "Year should be above 1000")
    private int year;

    private int person_id;

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public Book(){}

    public Book(int book_id, String name, String author, int year, int person_id) {
        this.book_id = book_id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.person_id = person_id;
    }

    public int getId(){
        return book_id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }
    public void setId(int book_id) {
        this.book_id = book_id;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
