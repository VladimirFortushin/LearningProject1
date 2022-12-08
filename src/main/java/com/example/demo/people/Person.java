package com.example.demo.people;

import com.example.demo.dao.BookDAO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

public class Person {

    BookDAO bookDAO;

    private int person_id;


    @NotEmpty(message = "Name of person is empty")
    @Size(min = 2, max = 100, message ="Person name should be between 2 and 100 characters")
    private String name;
    @NotNull(message = "Year of birth is empty")
    @Min(value = 1900, message = "Year of birth can't be below 1900")
    private int year_of_birth;

    public Person(){}
    public Person(int person_id, String name, int year_of_birth ) {
        this.person_id = person_id;
        this.name = name;
        this.year_of_birth = year_of_birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return person_id;
    }
    public void setId(int person_id) {
        this.person_id = person_id;
    }
    public int getYearOfBirth() {
        return year_of_birth;
    }

    public void setYearOfBirth(int year_of_birth ) {
        this.year_of_birth = year_of_birth;
    }
}
