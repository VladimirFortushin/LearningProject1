package com.example.demo.controller;

import com.example.demo.book.Book;
import com.example.demo.dao.BookDAO;
import com.example.demo.dao.PersonDAO;
import com.example.demo.people.Person;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    @Autowired
    public BookController(PersonDAO personDAO, BookDAO bookDAO){
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String listOfBook(Model model){
        model.addAttribute("bookList", bookDAO.showListOfBooks());
        return "/book/bookList";
    }

    @GetMapping("/{id}")
        public String showBook(@PathVariable("id") int id, @NotNull Model model, @ModelAttribute("person") Person person){

        model.addAttribute("book", bookDAO.showBook(id));
        model.addAttribute("bookOwner", personDAO.showBookOwner(id));
        model.addAttribute("peopleList", personDAO.showListOfPeople());


        return "/book/show-book";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){

        return "/book/add-book";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult){
        if(bindingResult.hasErrors()){return "/book/add-book";}
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String changeBook(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.showBook(id));
        return "/book/edit-book";
    }

    @PostMapping("/{id}/change")
    public String editBook(@ModelAttribute("book") @Valid Book book,
                           BindingResult bindingResult,
                           @PathVariable int id){

        if(bindingResult.hasErrors()){return "/book/edit-book";}
        bookDAO.edit(id, book);
        return "redirect:/books";
    }

    @GetMapping("non-occupied")
    public String listOfNonOccupiedBooks(Model model){
        model.addAttribute("nonOccupiedBooks", bookDAO.showListOfNonOccupiedBooks());
        return "/book/bookList";
    }

    @PostMapping("/{id}/release")
    public String releaseBook(@PathVariable int id){

        bookDAO.release(id);
        return "redirect:/books/{id}";
    }



  @PostMapping("/{id}/remove")
  public String deleteBook(@PathVariable int id){
      bookDAO.deleteBook(id);

        return "redirect:/books";
  }


}
