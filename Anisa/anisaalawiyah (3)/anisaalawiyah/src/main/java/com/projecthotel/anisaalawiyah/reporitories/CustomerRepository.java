package com.projecthotel.anisaalawiyah.reporitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projecthotel.anisaalawiyah.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findAll();

    Customer findByUsername(String username);

    Customer findCustomerByEmail(String email);
    
}
