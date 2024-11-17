package com.example.hotel.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.Model.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {
    // Add custom queries if needed
}

