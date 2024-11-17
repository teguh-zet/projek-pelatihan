package com.arsgadget.aisyah.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arsgadget.aisyah.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByid(Integer id);
}
