package com.example.fajri_roxilius_store.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.fajri_roxilius_store.models.Keranjang;
import com.example.fajri_roxilius_store.models.Product;
import com.example.fajri_roxilius_store.repositorys.KeranjangRepository;
import com.example.fajri_roxilius_store.repositorys.ProductRepository;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private KeranjangRepository keranjangRepository;

    @GetMapping("/Add-Product")
    public String addProduct(Model x) {
        Product product = new Product();
        x.addAttribute("product", product);
        return "admin/admin_add_product";
    }

    @PostMapping("/Add-Product")
    public String addProduct(Product product, @RequestPart("file") MultipartFile file) {
        try {
            Path targetPath = Paths.get(System.getProperty("user.dir"),
             "src", "main", "resources", "static", file.getOriginalFilename());
            file.transferTo(targetPath.toFile());
            String url = "http://localhost:8080/" + file.getOriginalFilename();
            product.setImage(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Product> products = productRepository.findAll();
        Product buffProduct = new Product();
        boolean same = false;
        for (Product product2 : products) {
            if (product2.getMerk().equals(product.getMerk())) {
                buffProduct = product2;
                buffProduct.setStock(product2.getStock() + product.getStock());
                same = true;
                break;
            }
        }
        if (same) {
            productRepository.save(buffProduct);
        } else {
            productRepository.save(product);
        }
        return "redirect:/Admin-Products";

    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable(value = "id") Integer id) {
        List<Keranjang> keranjangs = keranjangRepository.findAll();
        for (Keranjang keranjang : keranjangs) {
            if (keranjang.getProduct().getId().equals(id)) {
                keranjangRepository.delete(keranjang);
            }
        }
        productRepository.deleteById(id);
        return "redirect:/Admin-Products";
    }

    @GetMapping("/update-product/{id}")
    public String updateProduct(@PathVariable(value = "id") Integer id, Model x) {
        Product product = productRepository.getReferenceById(id);
        x.addAttribute("product", product);
        return "admin/admin_update_product";
    }

    @PostMapping("/update-product")
    public String updateProduct(Product product, @RequestPart("file") MultipartFile file) {
        try {
            Path targetPath = Paths.get(System.getProperty("user.dir"),
             "src", "main", "resources", "static", file.getOriginalFilename());
            file.transferTo(targetPath.toFile());
            String url = "http://localhost:8080/" + file.getOriginalFilename();
            product.setImage(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        productRepository.save(product);
        return "redirect:/Admin-Products";
    }

    @GetMapping("/detail-product/{merk}")
    public String detailProduct(@PathVariable(value = "merk") String merk, Model x) {
        Product product1 = productRepository.findProductByMerk(merk);
        List<Product> product2 = productRepository.findAll();
        Keranjang keranjang = new Keranjang();
        x.addAttribute("product1", product1);
        x.addAttribute("product2", product2);
        x.addAttribute("keranjang", keranjang);
        return "home/detail_product";
    }
}
