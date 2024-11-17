package com.moslemwear.bismillahproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.moslemwear.bismillahproject.models.AdminLogin;
import com.moslemwear.bismillahproject.models.Variety;
import com.moslemwear.bismillahproject.repositories.AdminLoginRepository;
import com.moslemwear.bismillahproject.repositories.VarietyRepository;

@Controller
public class VarietyController {
    @Autowired
    private VarietyRepository varietyRepository;

    @Autowired
    private AdminLoginRepository adminLoginRepository;

    @GetMapping("/add-variety")
    public String addVariety(Model model){
        if (adminLoginRepository.findAll().isEmpty()) {
            AdminLogin adminLogin = new AdminLogin();
            model.addAttribute("adminLogin", adminLogin);
            return "redirect:/login-admin";
        }
        Variety variety = new Variety();
        model.addAttribute("variety", variety);
        return "add-variety";
    }

    @PostMapping("/save-variety")
    public String saveVariety(@ModelAttribute("variety") Variety variety){
        varietyRepository.save(variety);
        return "redirect:/home-admin";
    }   
}
