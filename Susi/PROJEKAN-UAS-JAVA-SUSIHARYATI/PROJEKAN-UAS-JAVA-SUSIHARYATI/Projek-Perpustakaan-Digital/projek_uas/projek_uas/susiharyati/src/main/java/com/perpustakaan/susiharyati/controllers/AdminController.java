package com.perpustakaan.susiharyati.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.perpustakaan.susiharyati.models.Admin;

import com.perpustakaan.susiharyati.repositories.AdminRepository;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/admin")
    public String loginAdmin(Model model) {
        model.addAttribute("admins", adminRepository.findAll());
        return "show-admin";
    }

    @GetMapping("add-admin")
    public String addAdmin(Model model) {
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "save-admin";
    }

    @PostMapping("admin-save")
    public String saveAdmin(Admin admin) {
        adminRepository.save(admin);
        return "login-admin";
    }

    @GetMapping("/admin-update/{id}")
    public String updateAdmin(@PathVariable(value = "id") Integer id, Model model) {
        Admin admin = adminRepository.findById(id).orElse(null);
        model.addAttribute("admin", admin);
        return "update-admin";
    }

    @GetMapping("/admin-delete/{id}")
    public String deleteAdmin(@PathVariable(value = "id") Integer id) {
        adminRepository.deleteById(id);
        return "redirect:/admin";
    }

}
