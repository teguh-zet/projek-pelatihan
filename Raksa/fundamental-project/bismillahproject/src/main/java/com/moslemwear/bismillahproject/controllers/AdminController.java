package com.moslemwear.bismillahproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.moslemwear.bismillahproject.models.Admin;
import com.moslemwear.bismillahproject.models.AdminLogin;
import com.moslemwear.bismillahproject.models.UserLogin;
import com.moslemwear.bismillahproject.repositories.AdminLoginRepository;
import com.moslemwear.bismillahproject.repositories.AdminRepository;
import com.moslemwear.bismillahproject.repositories.ItemRepository;
import com.moslemwear.bismillahproject.repositories.UserLoginRepository;

@Controller
public class AdminController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private AdminLoginRepository adminLoginRepository;

    @Autowired
    private AdminRepository adminRepository;



    @GetMapping("/login-admin")
    public String login(Model model) {
        if (adminRepository.findAll().isEmpty()) {
            Admin adminElse = new Admin();
            adminElse.setUsername("raksa");
            adminElse.setPassword("raksa123");
            adminRepository.save(adminElse);
        }
        AdminLogin adminLogin = new AdminLogin();
        model.addAttribute("adminLogin", adminLogin);
        return "logining";
    }

    @PostMapping("/login")
    public String isLogin(AdminLogin adminLogin, Model model) {
        List<Admin> admins = adminRepository.findAll();
        boolean isLogin = false;
        for (Admin admin : admins) {
            if (admin.getUsername().equals(adminLogin.getUsername())
                    && admin.getPassword().equals(adminLogin.getPassword())) {
                isLogin = true;
                // Set supaya id nya sama
                adminLogin.setId(admin.getId());
                break;
            }
        }

        if (isLogin) {
            adminLoginRepository.deleteAll();
            adminLoginRepository.save(adminLogin);
            return "redirect:/home-admin";
        } else {
            return "redirect:/login-admin";
        }
    }

    @GetMapping("/home-admin")
    public String showAdmin(Model model) {
        if (adminLoginRepository.findAll().isEmpty()) {
            AdminLogin adminLogin = new AdminLogin();
            model.addAttribute("adminLogin", adminLogin);
            return "redirect:/login-admin";
        }
        model.addAttribute("item", itemRepository.findAll());
        return "home-admin";
    }

    @GetMapping("logout-admin")
    public String logoutAdmin(Model model) {
        if (adminLoginRepository.findAll().isEmpty()) {
            UserLogin userLogin = new UserLogin();
            model.addAttribute("userLogin", userLogin);
            return "redirect:/login-admin";
        }
        adminLoginRepository.deleteAll();
        return "moslemwear.com";
    }

}
