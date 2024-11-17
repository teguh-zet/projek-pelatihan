package com.example.fajri_roxilius_store.repositorys;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.fajri_roxilius_store.models.UserLogin;

public interface LoginRepository extends JpaRepositoryImplementation<UserLogin, Integer>{
    
}