package com.arsgadget.aisyah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.arsgadget.aisyah.models.Payment;
import com.arsgadget.aisyah.repositories.PaymentRepository;

@Controller
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("list-payment")
    public String allPayment(Model model) {
        model.addAttribute("payment", paymentRepository.findAll());
        return "show-payment";
    }

    @PostMapping("save-payment")
    public String savePayment(@ModelAttribute("payment") Payment payment) {
        paymentRepository.save(payment);
        return "redirect:/list-payment";
    }

    @GetMapping("update-payment/{id}")
    public String updatePayment(@PathVariable(value = "id") Integer id, Model model) {
        Payment payment = paymentRepository.getReferenceById(id);
        model.addAttribute("payment", payment);
        return "update-payment";
    }

    @GetMapping("/delete-payment/{id}")
    public String deletePayment(@PathVariable(value = "id") Integer id) {
        paymentRepository.deleteById(id);
        return "redirect:/list-payment";
    }
}
