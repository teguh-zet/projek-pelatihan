package com.example.cadangan.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cadangan.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Integer>{
 List<Doctor> findByName(String name);
}
