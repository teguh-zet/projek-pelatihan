package com.example.fajri_roxilius_store.repositorys;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.fajri_roxilius_store.models.Purchase;

public interface PurchaseRepository extends JpaRepositoryImplementation<Purchase,Integer>{
    
}
