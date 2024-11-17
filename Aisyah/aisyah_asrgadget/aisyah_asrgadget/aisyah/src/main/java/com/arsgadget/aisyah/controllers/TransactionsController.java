package com.arsgadget.aisyah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.arsgadget.aisyah.models.Payment;
import com.arsgadget.aisyah.models.Product;
import com.arsgadget.aisyah.models.Transactions;
import com.arsgadget.aisyah.repositories.PaymentRepository;
import com.arsgadget.aisyah.repositories.ProductRepository;
import com.arsgadget.aisyah.repositories.TransactionsRepository;

@Controller
public class TransactionsController {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("list-transaction")
    public String listTransaction(Model model) {
        model.addAttribute("transaction", transactionsRepository.findAll());
        return "show-transaction";
    }

    @GetMapping("transaction")
    public String buyProduct(Model model) {
        Transactions transactions = new Transactions();
        model.addAttribute("transaction", transactions);
        return "buy-product";
    }

    @GetMapping("buy-now/{id}")
    public String buyProduct(@PathVariable(value = "id") Integer id, Model model) {
        Product product = productRepository.getReferenceById(id);
        System.out.println("Data produk terpilih : " + product);
        Transactions transactions = new Transactions();
        Payment payment = new Payment();
        model.addAttribute("product", product);
        model.addAttribute("transaction", transactions);
        model.addAttribute("payment", payment);
        return "buy-product";
    }

    @GetMapping("buy")
    public String buyyingProduct(Model model) {
        Transactions transactions = new Transactions();
        Payment payment = new Payment();
        model.addAttribute("transaction", transactions);
        model.addAttribute("payment", payment);
        System.out.println(productRepository.findAll());
        model.addAttribute("prodcut", productRepository.findAll());
        return "buy-product";
    }

    @PostMapping("check-out")
    public String checkOut(Transactions transactions, Payment payment, Model model) {
        Product productList = productRepository.findByid(transactions.getIdProduct().getId());
        payment.setTotalPrice(productList.getPrice() * transactions.getAmountProduct());

        if (productList.getStock() < transactions.getAmountProduct()) {
            model.addAttribute("message",
                    "Mohon maaf stock nya lagi kurang " + (transactions.getAmountProduct() - productList.getStock())
                            + " item");
            return "information-transaction";
        } else {
            if (payment.getTotalPayment() < payment.getTotalPrice()) {
                model.addAttribute("message",
                        "Total Bayar Masih Kurang Rp. " + (payment.getTotalPrice() - payment.getTotalPayment()));
                return "information-transaction";
            }
            
            productList.setStock(productList.getStock() - transactions.getAmountProduct());
            productRepository.save(productList);
            transactionsRepository.save(transactions);
            payment.setIdTransaction(transactions);
            paymentRepository.save(payment);
            model.addAttribute("message", "Selamat Pembelian " + productList.getName() + " dengan Harga Rp. "
                    + productList.getPrice() + " telah berhasil!!!");
            return "information-transaction";
        }
    }
}
