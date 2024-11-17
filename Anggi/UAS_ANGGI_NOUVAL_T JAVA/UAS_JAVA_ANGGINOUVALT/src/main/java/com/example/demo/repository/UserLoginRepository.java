package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin,Integer> {
    
    
}
