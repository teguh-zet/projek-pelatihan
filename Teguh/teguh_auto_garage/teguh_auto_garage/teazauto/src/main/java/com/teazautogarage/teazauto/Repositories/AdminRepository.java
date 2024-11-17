package com.teazautogarage.teazauto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teazautogarage.teazauto.Model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    
}
