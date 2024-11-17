package com.example.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.Model.LoginAdmin;

public interface LoginAdminRepository extends JpaRepository<LoginAdmin,Integer> {
    
}
