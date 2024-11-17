package com.perpustakaan.susiharyati.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.perpustakaan.susiharyati.models.Book;
import com.perpustakaan.susiharyati.repositories.BookRepository;

@Controller
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("book")
    public String allBook(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "show-book";
    }

    @GetMapping("book-member")
    public String allBookMember(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "show-book-member";
    }

    @GetMapping("/add-book")
    public String addBook(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        return "save-book";
    }

    @PostMapping("/book-save")
    public String saveBook(@ModelAttribute("book") Book book){
        bookRepository.save(book);
        return "redirect:/book";
    }

    @GetMapping("/book-update/{id}")
    public String updateBook(@PathVariable(value = "id")Integer id, Model model){
        Book book = bookRepository.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "update-book";
    }

    @GetMapping("/book-delete/{id}")
    public String deleteBook(@PathVariable(value = "id") Integer id, Model model){
        bookRepository.deleteById(id);
        return "redirect:/book";
    }

}
