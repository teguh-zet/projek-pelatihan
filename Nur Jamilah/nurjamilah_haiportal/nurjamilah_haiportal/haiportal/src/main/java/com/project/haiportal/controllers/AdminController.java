package com.project.haiportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.haiportal.models.Admin;
import com.project.haiportal.repositories.AdminRepository;

@Controller
public class AdminController {
    @Autowired
    public AdminRepository adminRepository;

    @GetMapping("list-admin")
    public String listAdmin(Model model){
        model.addAttribute("admins", adminRepository.findAll());
        return "show-admin";
    }

    //REGISTER/ADD-ADMIN
    @GetMapping("/register")
    public String addAdmin(Model model){
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "register";
    }
    
    @PostMapping("/save-admin")
    public String saveAdmin(@ModelAttribute("admins") Admin admin){
        adminRepository.save(admin);
        return "redirect:/login";
    }

    @GetMapping("update-admin/{id}")
    public String updateAdmin(@PathVariable (value = "id") Integer id, Model model){
        Admin admin = adminRepository.getReferenceById(id);
        model.addAttribute("admin", admin);
        return "update-admin";
    }

    @GetMapping("delete-admin/{id}")
    public String deleteAdmin(@PathVariable(value = "id") Integer id, Model model){
        if (adminRepository.count() > 1) {
            adminRepository.deleteById(id);
        }
        return "redirect:/list-admin";
    }
}
