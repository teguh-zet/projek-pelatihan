package com.arsgadget.aisyah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arsgadget.aisyah.models.Customer;
import com.arsgadget.aisyah.models.Admin;
import com.arsgadget.aisyah.repositories.CustomerRepository;
import com.arsgadget.aisyah.repositories.AdminRepository;

@Controller

public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("list-customer")
    public String allCustomer(Model model) {
        model.addAttribute("customer", customerRepository.findAll());
        return "show-customer";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-check")
    public String loginCheck(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {
        Customer checkEmailCustomer = customerRepository.findCustomerByEmail(email);
        Admin checkEmailAdmin = adminRepository.findAdminByEmail(email);
        if (checkEmailCustomer != null && checkEmailCustomer.getPassword().equals(password)) {
            return "redirect:/home?"
                    + "id=" + checkEmailCustomer.getId()
                    + "&name=" + checkEmailCustomer.getName()
                    + "&email=" + checkEmailCustomer.getEmail();
        }
        else if (checkEmailAdmin != null && checkEmailAdmin.getPassword().equals(password)) {
            return "redirect:/home?"
                    + "id=" + checkEmailAdmin.getId()
                    + "&name=" + checkEmailAdmin.getName()
                    + "&email=" + checkEmailAdmin.getEmail()
                    + "&role=" + checkEmailAdmin.getRole();
        }
        model.addAttribute("message", "Email atau Password Salah");
        return "login";
    }

    @GetMapping("/regist")
    public String RegistrasiCostomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "register";
    }

    @PostMapping("/save-customer")
    public String saveCustomer(Customer customer, Model model) {
        Customer checkEmailCustomer = customerRepository.findCustomerByEmail(customer.getEmail());
        Admin checkEmailAdmin = adminRepository.findAdminByEmail(customer.getEmail());
        if (checkEmailCustomer != null || checkEmailAdmin != null) {
            model.addAttribute("message", "Email sudah digunakan, ganti dengan email lain !!!");
            return "register";
        } else {
            customerRepository.save(customer);
            return "login";
        }
    }

    @GetMapping("/delete-customer/{id}")
    public String deleteCustomer(@PathVariable(value = "id") Integer id) {
        customerRepository.deleteById(id);
        return "redirect:/list-customer";
    }

}
