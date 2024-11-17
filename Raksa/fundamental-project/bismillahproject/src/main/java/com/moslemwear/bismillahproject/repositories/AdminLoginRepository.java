package com.moslemwear.bismillahproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moslemwear.bismillahproject.models.AdminLogin;

public interface AdminLoginRepository extends JpaRepository<AdminLogin, Integer>{
    
}
