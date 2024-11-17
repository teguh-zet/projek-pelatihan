package com.perpustakaan.susiharyati.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.perpustakaan.susiharyati.models.Book;
import com.perpustakaan.susiharyati.models.Borrow;
import com.perpustakaan.susiharyati.repositories.BookRepository;
import com.perpustakaan.susiharyati.repositories.BorrowRepository;
import com.perpustakaan.susiharyati.repositories.MemberRepository;

@Controller
public class BorrowController {
    
    @Autowired
    private BorrowRepository borrowRepository;


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/borrow")
    public String allBorrow(Model model){

        model.addAttribute("borrows", borrowRepository.findAll());
        return "show-borrow";
    }

    @GetMapping("/add-borrow")
    public String addBorrow(Model model){
        Borrow borrow = new Borrow();
        model.addAttribute("book", bookRepository.findAll());
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("borrow", borrow);
        // model.addAttribute("back", backRepository.findAll());

        return "save-borrow";
    }

    @PostMapping("/borrow-save")
    public String saveBorrow(@ModelAttribute("borrow")Borrow borrow,Model model){
        Book bookList = bookRepository.findByid(borrow.getIdBook().getId());

        if(bookList.getAmountBook() < borrow.getAmount()){
            model.addAttribute("message","Mohon Maaf Buku Habis Terpinjam" + (bookList.getAmountBook() - borrow.getAmount()) + "item");
            return "redirect:/add-borrow";

        }

        bookList.setAmountBook(bookList.getAmountBook() - borrow.getAmount());
        borrowRepository.save(borrow);
        return "redirect:/borrow";
    }

    @GetMapping("/borrow-update/{id}")
    public String updateBorrow(@PathVariable(value = "id") Integer id, Model model){
        Borrow borrow = borrowRepository.findById(id).orElse(null);
        model.addAttribute("borrow", borrow);
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("book", bookRepository.findAll());
        return "update-borrow";
    }

    // @

}
