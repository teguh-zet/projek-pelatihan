package com.perpustakaan.susiharyati.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.perpustakaan.susiharyati.repositories.BackRepository;
import com.perpustakaan.susiharyati.repositories.BookRepository;
import com.perpustakaan.susiharyati.repositories.BorrowRepository;
import com.perpustakaan.susiharyati.models.HistoryBorrow;
import com.perpustakaan.susiharyati.repositories.HistoryBorrowRepository;
import com.perpustakaan.susiharyati.repositories.MemberRepository;

@Controller
public class HistoryBorrowController {
    
    @Autowired
    private HistoryBorrowRepository historyBorrowRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BackRepository backRepository ;

    // @GetMapping("/history")
    // public String allHistory(Model model){
    //     model.addAttribute("historys", historyBorrowRepository.findAll());
    //     return "show-history-borrow";
    // }

    @GetMapping("/history-admin")
    public String allHistoryAdmin(Model model){
        model.addAttribute("historys", historyBorrowRepository.findAll());
        return "show-history-borrow-admin";
    }

    @GetMapping("/add-history")
    public String addHistory(Model model){
        HistoryBorrow historyBorrow = new HistoryBorrow();

        model.addAttribute("history", historyBorrow);
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("book", bookRepository.findAll());
        model.addAttribute("borrow", borrowRepository.findAll());
        model.addAttribute("back", backRepository.findAll());
        return "save-history-borrow";
    }

    @PostMapping("/history-save")
    public String saveHistory(@ModelAttribute("historyBorrow") HistoryBorrow historyBorrow){
        historyBorrowRepository.save(historyBorrow);
        return "redirect:/history";
    }

    @GetMapping("/history-delete/{id}")
    public String deleteBorrow(@PathVariable(value = "id") Integer id, Model model){
        historyBorrowRepository.deleteById(id);
        return "redirect:/history";
    }
}
