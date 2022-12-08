package com.example.demo.map;

import com.example.demo.book.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookMapper<B> implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(
                rs.getInt("book_id"),
                rs.getString("name"),
                rs.getString("author"),
                rs.getInt("year"),
                rs.getInt("person_id")
        );
    }
}