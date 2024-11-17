package com.example.cadangan.controllers;

import com.example.cadangan.models.Admin;
import com.example.cadangan.models.SuperAdmin;
import com.example.cadangan.repositories.AdminRepository;
import com.example.cadangan.repositories.SuperAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SuperAdminRepository superAdminRepository;

    @GetMapping("/show-admin")
    public String showAdminById(@RequestParam("id") Integer id, Model model) {
        Admin admin = adminRepository.findById(id).orElse(null);
        model.addAttribute("admin", admin);
        return "show-admin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-check")
    public String loginCheck(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {
        Admin checkUsernameAdmin = adminRepository.findByUsername(username);
        SuperAdmin checkUsernameSuperAdmin = superAdminRepository.findByUsername(username);

        if (checkUsernameAdmin != null && checkUsernameAdmin.getPassword().equals(password)) {
            return "redirect:/show-admin?id=" + checkUsernameAdmin.getId();
        } else if (checkUsernameSuperAdmin != null && checkUsernameSuperAdmin.getPassword().equals(password)) {
            return "redirect:/show-superAdmin";
        }

        model.addAttribute("message", "Username atau Password Salah");
        return "login";
    }

}
