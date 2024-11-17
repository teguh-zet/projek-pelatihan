package com.moslemwear.bismillahproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moslemwear.bismillahproject.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
    
}
