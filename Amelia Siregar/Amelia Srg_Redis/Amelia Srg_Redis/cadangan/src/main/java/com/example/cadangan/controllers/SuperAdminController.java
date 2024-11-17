package com.example.cadangan.controllers;

import com.example.cadangan.models.Admin;
import com.example.cadangan.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SuperAdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/show-superAdmin")
    public String showSuperAdmin(Model model) {
        model.addAttribute("adminList", adminRepository.findAll());
        return "show-superAdmin";
    }

    @GetMapping("/save-admin")
    public String addAdmin(Model model) {
        model.addAttribute("admin", new Admin());
        return "save-admin";
    }

    @PostMapping("/save-admin")
    public String saveAdmin(@ModelAttribute("admin") Admin admin, Model model) {
        Admin checkUsernameAdmin = adminRepository.findByUsername(admin.getUsername());
        if (checkUsernameAdmin != null) {
            model.addAttribute("message", "Username sudah digunakan, ganti dengan username lain !!!");
            return "save-admin";
        } else {
            adminRepository.save(admin);
            return "redirect:/show-superAdmin";
        }
    }

    @GetMapping("/update-admin/{id}")
    public String editAdmin(@PathVariable(value = "id") Integer id, Model model) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid admin ID: " + id));
        model.addAttribute("admin", admin);
        return "update-admin";
    }

    @PostMapping("/update-admin/{id}")
    public String processUpdateAdmin(@PathVariable Integer id, @ModelAttribute("admin") Admin updatedAdmin, Model model) {
    Admin admin = adminRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid admin ID: " + id));
    admin.setName(updatedAdmin.getName());
    admin.setUsername(updatedAdmin.getUsername());
    admin.setPassword(updatedAdmin.getPassword());
    admin.setTelp(updatedAdmin.getTelp());
        adminRepository.save(admin);
        return "redirect:/show-superAdmin";
    }

    @GetMapping("/delete-superAdmin/{id}")
    public String deleteAdmin(@PathVariable(value = "id") Integer id) {
        adminRepository.deleteById(id);
        return "redirect:/show-superAdmin";
    }
}
