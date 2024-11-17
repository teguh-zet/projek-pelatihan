package com.arsgadget.aisyah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arsgadget.aisyah.models.Customer;
import com.arsgadget.aisyah.repositories.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer findEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
}
