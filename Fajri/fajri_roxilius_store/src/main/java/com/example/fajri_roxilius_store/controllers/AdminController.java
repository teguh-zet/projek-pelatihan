package com.example.fajri_roxilius_store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fajri_roxilius_store.models.Product;
import com.example.fajri_roxilius_store.models.User;
import com.example.fajri_roxilius_store.repositorys.ProductRepository;
import com.example.fajri_roxilius_store.repositorys.UserRepository;

@Controller
public class AdminController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/Admin-Products")
    public String admin(Model x){
        List<Product> products = productRepository.findAll();
        x.addAttribute("product", products);
        return "admin/admin_products";
    }
    @GetMapping("/Admin-Products/")
    public String productSearch(@RequestParam(name = "merk") String merk, Model x){
        List<Product> keywords = productRepository.findByMerkContainingIgnoreCase(merk);
        x.addAttribute("product", keywords);
        return "admin/admin_search_products";
    }

    @GetMapping("/Admin-Users")
    public String users(Model x){
        List<User> users = userRepository.findAll();
        x.addAttribute("users", users);
        return "admin/admin_users";
    }
}
