package com.perpustakaan.susiharyati.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.perpustakaan.susiharyati.models.Back;
import com.perpustakaan.susiharyati.models.Book;
import com.perpustakaan.susiharyati.repositories.BackRepository;
import com.perpustakaan.susiharyati.repositories.BookRepository;
import com.perpustakaan.susiharyati.repositories.MemberRepository;

@Controller
public class BackController {

    @Autowired
    private BackRepository backRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/back")
    public String allBack(Model model) {
        model.addAttribute("backs", backRepository.findAll());
        return "show-back";
    }

    @GetMapping("/add-back")
    public String addPengembalian(Model model) {
        Back back = new Back();
        model.addAttribute("back", back);
        model.addAttribute("book", bookRepository.findAll());
        model.addAttribute("member", memberRepository.findAll());
        return "save-back";
    }

    @PostMapping("/back-save")
    public String saveBack(@ModelAttribute("member") Back back, Model model) {
        Book bookList = bookRepository.findByid(back.getIdBook().getId());

        bookList.setAmountBook(bookList.getAmountBook() + back.getAmountBack());
        backRepository.save(back);
        return "redirect:/back";
    }

    @GetMapping("/back-delete/{id}")
    public String deleteBorrow(@PathVariable(value = "id") Integer id, Model model) {
        backRepository.deleteById(id);
        return "redirect:/back";
    }
}
