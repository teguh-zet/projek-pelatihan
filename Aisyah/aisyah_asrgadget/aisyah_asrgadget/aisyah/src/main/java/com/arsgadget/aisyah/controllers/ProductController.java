package com.arsgadget.aisyah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.arsgadget.aisyah.models.Product;
import com.arsgadget.aisyah.repositories.ProductRepository;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/home")
    public String homeArs(Model model) {
        model.addAttribute("product", productRepository.findAll());
        return "home-ars";
    }

    @GetMapping("/info")
    public String about() {
        return "about";
    }

    @GetMapping("list-product")
    public String allProduct(Model model) {
        model.addAttribute("product", productRepository.findAll());
        return "show-product";
    }

    @GetMapping("add-product")
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "save-product";
    }

    @PostMapping("add-save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        product.setImageProduct("http://localhost:8080/" + product.getImageProduct());
        productRepository.save(product);
        return "redirect:/list-product";
    }

    @PostMapping("save-product")
    public String save(@ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "redirect:/list-product";
    }

    @GetMapping("update/{id}")
    public String updateProduct(@PathVariable(value = "id") Integer id, Model model) {
        Product product = productRepository.getReferenceById(id);
        model.addAttribute("product", product);
        return "update-product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Integer id) {
        productRepository.deleteById(id);
        return "redirect:/list-product";
    }
}