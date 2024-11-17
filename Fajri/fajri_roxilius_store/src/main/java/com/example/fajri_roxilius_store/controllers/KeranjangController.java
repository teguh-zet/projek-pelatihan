package com.example.fajri_roxilius_store.controllers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fajri_roxilius_store.models.Keranjang;
import com.example.fajri_roxilius_store.models.Product;
import com.example.fajri_roxilius_store.models.Purchase;
import com.example.fajri_roxilius_store.models.User;
import com.example.fajri_roxilius_store.models.UserLogin;
import com.example.fajri_roxilius_store.repositorys.KeranjangRepository;
import com.example.fajri_roxilius_store.repositorys.LoginRepository;
import com.example.fajri_roxilius_store.repositorys.ProductRepository;
import com.example.fajri_roxilius_store.repositorys.PurchaseRepository;
import com.example.fajri_roxilius_store.repositorys.UserRepository;

@Controller
public class KeranjangController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private KeranjangRepository keranjangRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PurchaseRepository purchaseRepository;

    @GetMapping("show-card")
    public String showCard(Model x) {
        Purchase purchase = new Purchase();
        if (loginRepository.findAll().isEmpty()) {
            return "redirect:/login";
        } else {
            List<UserLogin> userLogins = loginRepository.findAll();
            User user = userRepository.getReferenceById(userLogins.get(0).getId());

            int items = 0;
            Integer total = 0;
            List<Keranjang> keranjangUser = new ArrayList<>();
            List<Keranjang> keranjangs = keranjangRepository.findAll();
            for (Keranjang keranjang : keranjangs) {
                if (keranjang.getUser().getId().equals(user.getId())) {
                    String totalInt = keranjang.getTotal().replaceAll("[^\\d]", "");
                    Integer totalInteger = Integer.parseInt(totalInt);
                    total += totalInteger;
                    keranjangUser.add(keranjang);
                    items++;
                }
            }
            NumberFormat format = new DecimalFormat("#,###");
            String totals = format.format(total);
            x.addAttribute("totals", "Rp. " + totals);
            x.addAttribute("items", items + " Items");
            x.addAttribute("card", keranjangUser);
            x.addAttribute("user", user);
            x.addAttribute("purchase", purchase);
            return "/home/card";
        }
    }

    @GetMapping("add-to-card/{id}")
    public String addToCard(@RequestParam(name = "qty") Integer qty, @PathVariable(value = "id") Integer id,
            Model x) {
        Product product = productRepository.getReferenceById(id);
        if (loginRepository.findAll().isEmpty()) {
            return "redirect:/login";
        } else {
            Keranjang keranjang = new Keranjang();
            keranjang.setQty(qty);
            if (product.getStock() == 0) {
                Product product1 = productRepository.findProductByMerk(product.getMerk());
                List<Product> product2 = productRepository.findAll();
                Keranjang keranjang2 = new Keranjang();
                x.addAttribute("product1", product1);
                x.addAttribute("product2", product2);
                x.addAttribute("keranjang", keranjang2);
                return "home/detail_product";
            } else {
                keranjang.setProduct(product);
                List<UserLogin> userLogins = loginRepository.findAll();
                User user = userRepository.getReferenceById(userLogins.get(0).getId());

                keranjang.setUser(user);

                String totalInt = keranjang.getProduct().getPrice().replaceAll("[^\\d]", "");
                NumberFormat format = new DecimalFormat("#,###");

                boolean same = false;
                Keranjang buffKeranjang = new Keranjang();
                List<Keranjang> keranjangs = keranjangRepository.findAll();
                for (Keranjang keranjang2 : keranjangs) {
                    if (keranjang2.getProduct().equals(keranjang.getProduct())
                            && keranjang.getUser().equals(keranjang2.getUser())) {
                        buffKeranjang = keranjang2;

                        if (keranjang.getQty() > product.getStock()) {
                            buffKeranjang.setQty(product.getStock());
                        } else {
                            buffKeranjang.setQty(keranjang.getQty());
                        }

                        Integer totalDigit = buffKeranjang.getQty() * Integer.parseInt(totalInt);
                        String total = format.format(totalDigit);
                        buffKeranjang.setTotal("Rp. " + total);
                        same = true;
                        break;
                    }
                }
                if (same) {
                    keranjangRepository.save(buffKeranjang);
                } else {
                    Integer totalDigit = keranjang.getQty() * Integer.parseInt(totalInt);
                    String total = format.format(totalDigit);
                    keranjang.setTotal("Rp. " + total);
                    keranjangRepository.save(keranjang);
                    x.addAttribute("buff", keranjang);
                }
            }
        }
        Product product1 = productRepository.findProductByMerk(product.getMerk());
        List<Product> product2 = productRepository.findAll();
        Keranjang keranjang2 = new Keranjang();
        x.addAttribute("product1", product1);
        x.addAttribute("product2", product2);
        x.addAttribute("keranjang", keranjang2);
        return "home/detail_product";
    }

    @GetMapping("delete-card/{id}")
    public String deleteCard(@PathVariable(value = "id") Integer id) {
        keranjangRepository.deleteById(id);
        return "redirect:/show-card";
    }

    @PostMapping("/buy")
    public String buy(Purchase purchase) {
        List<UserLogin> userLogins = loginRepository.findAll();
        User user = userRepository.getReferenceById(userLogins.get(0).getId());
        NumberFormat format = new DecimalFormat("#,###");

        Integer total = 0;
        // List<Purchase> purchases = new ArrayList<>();
        List<Keranjang> keranjangs = keranjangRepository.findAll();
        for (Keranjang keranjang : keranjangs) {
            if (keranjang.getUser().getId().equals(user.getId())) {
                String totalInt = keranjang.getTotal().replaceAll("[^\\d]", "");
                Integer totalInteger = Integer.parseInt(totalInt);
                total += totalInteger;

                String totalString = format.format(totalInteger);
                purchase.setProduct(keranjang.getProduct());
                purchase.setUser(user);
                purchase.setQty(keranjang.getQty());
                purchase.setTotal(totalInt);
                purchase.setTotal("Rp. " + totalString);
                purchaseRepository.save(purchase);
            }
        }

        String saldoString = user.getSaldo().replaceAll("[^\\d]", "");
        Integer saldoInteger = Integer.parseInt(saldoString);
        Integer buffSaldo = saldoInteger - total;

        String finalSaldo = format.format(buffSaldo);

        userLogins.get(0).setSaldo("Rp. " + finalSaldo);
        loginRepository.save(userLogins.get(0));

        user.setSaldo(userLogins.get(0).getSaldo());
        userRepository.save(user);

        return "redirect:/invoice";
    }

    @GetMapping("/invoice")
    public String invoice(Model x) {
        if (loginRepository.findAll().isEmpty()) {
            return "redirect:/login";
        } else {

            LocalDate currentTime = LocalDate.now();
            x.addAttribute("time", currentTime);

            List<UserLogin> userLogins = loginRepository.findAll();
            User user = userRepository.getReferenceById(userLogins.get(0).getId());

            int items = 0;
            Integer total = 0;
            List<Keranjang> invoice = new ArrayList<>();
            List<Keranjang> keranjangs = keranjangRepository.findAll();
            for (Keranjang keranjang : keranjangs) {
                if (keranjang.getUser().getId().equals(user.getId())) {
                    String totalInt = keranjang.getTotal().replaceAll("[^\\d]", "");
                    Integer totalInteger = Integer.parseInt(totalInt);
                    total += totalInteger;
                    invoice.add(keranjang);
                    items++;
                }
            }
            NumberFormat format = new DecimalFormat("#,###");
            String totals = format.format(total);
            x.addAttribute("totals", "Rp. " + totals);
            x.addAttribute("items", items + " Items");
            x.addAttribute("user", user);
            Random random = new Random();
            int id = random.nextInt(10000000);
            x.addAttribute("id", id);
            List<Keranjang> delete = keranjangRepository.findAll();
            for (Keranjang keranjang : delete) {
                if (keranjang.getUser().getId().equals(user.getId())) {
                    Product product = productRepository.getReferenceById(keranjang.getProduct().getId());
                    product.setStock(product.getStock() - keranjang.getQty());
                    productRepository.save(product);
                    keranjangRepository.delete(keranjang);
                }
            }
            x.addAttribute("invoice", invoice);
            return "home/invoice";
        }
    }

}