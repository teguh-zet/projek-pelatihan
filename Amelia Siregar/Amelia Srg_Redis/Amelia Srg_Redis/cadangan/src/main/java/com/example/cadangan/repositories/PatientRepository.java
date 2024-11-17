package com.example.cadangan.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cadangan.models.Patient;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
    List<Patient> findByName(String name);
}
