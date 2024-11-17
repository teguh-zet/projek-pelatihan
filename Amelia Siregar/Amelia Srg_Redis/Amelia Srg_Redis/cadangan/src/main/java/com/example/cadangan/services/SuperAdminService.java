package com.example.cadangan.services;

import com.example.cadangan.models.SuperAdmin;
import com.example.cadangan.repositories.SuperAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminService {

    @Autowired
    private SuperAdminRepository superAdminRepository;

    public SuperAdmin findUsername(String username) {
        return superAdminRepository.findByUsername(username);
    }
}
