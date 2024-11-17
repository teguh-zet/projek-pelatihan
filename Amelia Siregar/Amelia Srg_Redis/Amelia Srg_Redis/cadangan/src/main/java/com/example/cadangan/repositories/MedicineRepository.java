package com.example.cadangan.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cadangan.models.Medicine;


public interface MedicineRepository extends JpaRepository<Medicine,Integer> {
}
