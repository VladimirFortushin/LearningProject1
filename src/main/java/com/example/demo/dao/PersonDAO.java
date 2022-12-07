package com.example.demo.dao;


import com.example.demo.map.PersonMapper;
import com.example.demo.people.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> showListOfPeople() {

        return jdbcTemplate.query("select * from person",
                new PersonMapper<Person>());
    }

    public Person showPerson(int person_id){
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id =?",
                        new Object[]{person_id}, new PersonMapper<Person>())
                .stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person (name, year_of_birth) VALUES (?, ?)",
                person.getName(), person.getYearOfBirth());
    }

    public void edit(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE person SET name=?, year_of_birth=? WHERE person_id =?",
                updatedPerson.getName(), updatedPerson.getYearOfBirth(), id);
    }


    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?",id);
    }
}
