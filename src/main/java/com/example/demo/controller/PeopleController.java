package com.example.demo.controller;


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
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO){
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String listOfPeople(Model model){
        model.addAttribute("peopleList", personDAO.showListOfPeople());

        return "/people/peopleList";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, @NotNull Model model){
        model.addAttribute("person", personDAO.showPerson(id));
        model.addAttribute("book", bookDAO.showBook(id));
        model.addAttribute("personBookList", bookDAO.showListOfPersonBooks(id));
        return "/people/show-person";
    }


    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){

        return "/people/add-person";
    }

    @PostMapping
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){return "/people/add-person";}
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String changePerson(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.showPerson(id));
        return "/people/edit-person";
    }

    @PostMapping("/{id}/change")
    public String editPerson(@ModelAttribute("person") @Valid Person person,
                           BindingResult bindingResult,
                           @PathVariable int id){

        if(bindingResult.hasErrors()){return "/people/edit-person";}
        personDAO.edit(id, person);
        return "redirect:/people";
    }

    @PostMapping("/{id}/remove")
    public String deletePerson(@PathVariable int id){
        personDAO.deletePerson(id);

        return "redirect:/people";
    }

    @PostMapping("/{id}/assign")
    public String assignBook(@PathVariable int id, @ModelAttribute("person") Person person){

        personDAO.assignBook(person.getId(), id);
        return "redirect:/books/{id}";
    }


}
