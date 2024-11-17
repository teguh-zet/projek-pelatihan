package com.project.haiportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.haiportal.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
    
}
