package com.example.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.Model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByUsername(String username);
}