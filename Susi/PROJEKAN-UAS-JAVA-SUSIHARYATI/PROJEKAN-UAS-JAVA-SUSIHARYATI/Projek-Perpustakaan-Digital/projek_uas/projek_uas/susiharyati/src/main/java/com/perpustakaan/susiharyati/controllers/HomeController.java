package com.perpustakaan.susiharyati.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/home-admin")
    public String homeAdmin(Model model){
        return "dashboard";
    }

    @GetMapping("/member-home")
    public String homeMember(Model model){
        return "home-member";
    }
    
}
