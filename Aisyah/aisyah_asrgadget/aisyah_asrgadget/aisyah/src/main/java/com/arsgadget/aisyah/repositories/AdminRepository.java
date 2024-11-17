package com.arsgadget.aisyah.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arsgadget.aisyah.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findAdminByEmail(String email);
}
