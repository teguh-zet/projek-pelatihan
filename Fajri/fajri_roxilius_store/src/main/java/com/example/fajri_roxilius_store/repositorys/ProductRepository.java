package com.example.fajri_roxilius_store.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.fajri_roxilius_store.models.Product;

public interface ProductRepository extends JpaRepositoryImplementation<Product, Integer>{
    List<Product> findByMerkContainingIgnoreCase(String merk);
    Product findProductByMerk(String merk);
}
