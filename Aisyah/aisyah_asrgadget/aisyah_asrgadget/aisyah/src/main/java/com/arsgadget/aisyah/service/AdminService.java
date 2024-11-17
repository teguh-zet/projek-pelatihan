package com.arsgadget.aisyah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arsgadget.aisyah.models.Admin;
import com.arsgadget.aisyah.repositories.AdminRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin findEmail(String email) {
        return adminRepository.findAdminByEmail(email);
    }
    
}
