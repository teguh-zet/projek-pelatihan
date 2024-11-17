package com.projecthotel.anisaalawiyah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projecthotel.anisaalawiyah.models.Customer;
import com.projecthotel.anisaalawiyah.reporitories.CustomerRepository;

import org.springframework.ui.Model;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

      @GetMapping("/login")
    public String login() {
        return "login-user";
    }

    @PostMapping("/login-check")
    public String loginCheck(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {
        // Jika di checkEmailCustomer ketemu email yang sama berari tandanya yang login
        // customer bukan admin
        Customer checkEmailCustomer = customerRepository.findCustomerByEmail(email);
        // Cek email di dalam table user sudah pernah digunakan atau belum
        
        if (checkEmailCustomer != null && checkEmailCustomer.getPassword().equals(password)) {
            return "redirect:/home?"
                    + "id=" + checkEmailCustomer.getIdCustomer()
                    + "&name=" + checkEmailCustomer.getName()
                    + "&email=" + checkEmailCustomer.getEmail();
        }
        // dan else if ini buat ngecek kalo misal bukan customer siapa tau admin jadi
        // dicek lagi
        // kalo tetep bukan berarti masuk else
        model.addAttribute("message", "Email atau Password Salah");
        return "add-Booking";
    }

    @GetMapping("login-user")
    public String loginUser(){
        return "login-user";
    }
    
    @GetMapping("/customer")
    public String allCustomer(Model model) {
        model.addAttribute("customer", customerRepository.findAll());
        return "show-customer";
    }
    
    @GetMapping("/add-customer")
    public String addCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "add-customer";
    }
 
    // @PostMapping("/save-customer")
    // public String saveCustomers(Customer customer, Model model){
    // customerRepository.save(customer);
    // return "redirect:/add-booking?customer=" + customerRepository.getReferenceById(customer.getIdCustomer());
    // }
    @PostMapping("/save-customer")
    public String saveCustomers(Customer customer, Model model){
    customerRepository.save(customer);
    return"login-user";
    }


    
    @PostMapping("/save-admin")
    public String saveCustomerAdmin(Customer customer){
    customerRepository.save(customer);
    return "redirect:/customer";
    }
        
    @GetMapping("/update/{idCustomer}")
    public String updateCustomer(Model model,@PathVariable(value="idCustomer")  Integer idCustomer) {
        Customer customer = customerRepository.findById(idCustomer).orElse(null);
        model.addAttribute("customer", customer);
        return "update-customer";
    }

    @GetMapping("/delete/{id}")
    public String deletecustomer(@PathVariable(value = "id") Integer id) {
        customerRepository.deleteById(id);;
        return "redirect:/customer";
    }
}
