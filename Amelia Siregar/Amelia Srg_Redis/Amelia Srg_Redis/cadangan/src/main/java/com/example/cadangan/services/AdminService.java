package com.example.cadangan.services;

import com.example.cadangan.models.Admin;
import com.example.cadangan.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin findUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}
