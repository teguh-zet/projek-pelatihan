package com.example.fajri_roxilius_store.controllers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fajri_roxilius_store.models.Product;
import com.example.fajri_roxilius_store.models.User;
import com.example.fajri_roxilius_store.models.UserLogin;
import com.example.fajri_roxilius_store.repositorys.LoginRepository;
import com.example.fajri_roxilius_store.repositorys.ProductRepository;
import com.example.fajri_roxilius_store.repositorys.UserRepository;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/home")
    public String home(Model x) {
        List<Product> products = productRepository.findAll();
        x.addAttribute("product", products);
        return "home/index";
    }

    @GetMapping("/login")
    public String login(Model x) {
        loginRepository.deleteAll();
        User user = new User();
        x.addAttribute("user", user);
        return "sign-in/login";
    }

    @PostMapping("/login")
    public String login(UserLogin user) {
        boolean isLogin = false, isAdmin = false;
        if (userRepository.findAll().isEmpty()) {
            if (user.getUsername().equals("admin") && user.getPassword().equals("admin")) {
                isAdmin = true;
            }
        } else {
            List<User> users = userRepository.findAll();
            for (User a : users) {
                if (user.getUsername().equals("admin") && user.getPassword().equals("admin")) {
                    isAdmin = true;
                    break;
                } else if (a.getUsername().equals(user.getUsername()) && a.getPassword().equals(user.getPassword())) {
                    isLogin = true;
                    user.setId(a.getId());
                    user.setName(a.getName());
                    user.setEmail(a.getEmail());
                    user.setContact(a.getContact());
                    user.setSaldo(a.getSaldo());
                    break;
                }
            }
        }
        if (isAdmin) {
            return "redirect:/Admin-Products";
        } else if (isLogin) {
            loginRepository.deleteAll();
            loginRepository.save(user);
            return "redirect:/home";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("register")
    public String register(Model x) {
        loginRepository.deleteAll();
        User user = new User();
        x.addAttribute("user", user);
        return "sign-in/register";
    }

    @PostMapping("register")
    public String register(User user) {
        user.setSaldo("Rp. 0");
        boolean same = false;
        List<User> users = userRepository.findAll();
        if (user.getUsername().equals("admin")) {
            same = true;
        } else {
            for (User a : users) {
                if (a.getUsername().equals(user.getUsername()) || user.getEmail().equals(a.getEmail())) {
                    same = true;
                    break;
                }
            }
        }
        if (same) {
            return "redirect:/register";
        } else {
            userRepository.save(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/profile")
    public String profile(Model x) {
        if (loginRepository.findAll().isEmpty()) {
            return "redirect:/login";
        } else {
            List<UserLogin> users = loginRepository.findAll();
            User user = userRepository.getReferenceById(users.get(0).getId());
            x.addAttribute("user", user);
            return "home/profile";
        }
    }

    @PostMapping("/tambah-saldo")
    public String tambahSaldo(@RequestParam(name = "saldo") String saldo) {
        String saldoString = saldo.replaceAll("[^\\d]", "");
        Integer saldoInteger = Integer.parseInt(saldoString);

        List<UserLogin> userLogins = loginRepository.findAll();
        String userSaldoString = userLogins.get(0).getSaldo().replaceAll("[^\\d]", "");
        Integer userSaldoInteger = Integer.parseInt(userSaldoString);

        Integer total = saldoInteger + userSaldoInteger;

        NumberFormat format = new DecimalFormat("#,###");
        String finalSaldo = format.format(total);
        
        userLogins.get(0).setSaldo("Rp. " + finalSaldo);
        UserLogin userLogin = userLogins.get(0);
        loginRepository.save(userLogin);
        User user = userRepository.getReferenceById(userLogins.get(0).getId());
        user.setSaldo(userLogin.getSaldo());
        userRepository.save(user);
        return "redirect:/profile";
    }
}