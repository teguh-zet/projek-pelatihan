package com.example.cadangan.repositories;

import com.example.cadangan.models.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Integer> {
    SuperAdmin findByUsername(String username);
}
