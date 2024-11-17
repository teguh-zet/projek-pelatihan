package com.example.cadangan.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cadangan.models.Polyclinic;

public interface PolyclinicRepository extends JpaRepository<Polyclinic,Integer> {
 List<Polyclinic> findByName(String name);   
}
