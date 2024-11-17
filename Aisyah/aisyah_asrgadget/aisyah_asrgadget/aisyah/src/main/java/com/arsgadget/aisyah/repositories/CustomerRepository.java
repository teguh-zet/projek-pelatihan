package com.arsgadget.aisyah.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arsgadget.aisyah.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByEmail(String email);
}
