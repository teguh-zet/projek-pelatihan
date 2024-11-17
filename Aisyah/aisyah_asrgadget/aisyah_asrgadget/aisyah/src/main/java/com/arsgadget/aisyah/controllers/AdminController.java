package com.arsgadget.aisyah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.arsgadget.aisyah.models.Customer;
import com.arsgadget.aisyah.models.Admin;
import com.arsgadget.aisyah.repositories.CustomerRepository;
import com.arsgadget.aisyah.repositories.AdminRepository;

@Controller
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("dashboard")
    public String dashboardAdmin() {
        return "dashboard";
    }

    @GetMapping("list-admin")
    public String listAdmin(Model model) {
        model.addAttribute("admin", adminRepository.findAll());
        return "show-admin";
    }

    @GetMapping("add-admin")
    public String addNewAdmin(Model model) {
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "save-admin";
    }

    @PostMapping("save-admin")
    public String saveAdmin(@ModelAttribute("admin") Admin admin, Model model) {
        Customer checkEmailCustomer = customerRepository.findCustomerByEmail(admin.getEmail());
        Admin checkEmailAdmin = adminRepository.findAdminByEmail(admin.getEmail());
        if (checkEmailAdmin != null && checkEmailAdmin.getId().equals(admin.getId())) {
            adminRepository.save(admin);
            return "redirect:/list-admin";
        } else if (checkEmailAdmin != null || checkEmailCustomer != null) {
            model.addAttribute("message", "Email sudah digunakan, ganti dengan email lain !!!");
            return "save-admin";
        } else {
            adminRepository.save(admin);
            return "redirect:/list-admin";
        }
    }

    @GetMapping("update-admin/{id}")
    public String updateAdmin(@PathVariable(value = "id") Integer id, Model model) {
        Admin admin = adminRepository.getReferenceById(id);
        model.addAttribute("admin", admin);
        return "update-admin";
    }

    @GetMapping("delete-admin/{id}")
    public String deleteAdmin(@PathVariable(value = "id") Integer id) {
        adminRepository.deleteById(id);
        return "redirect:/list-admin";
    }
}
