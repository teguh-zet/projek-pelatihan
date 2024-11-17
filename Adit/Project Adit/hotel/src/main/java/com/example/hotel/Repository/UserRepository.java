package com.example.hotel.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.Model.Admin;
import com.example.hotel.Model.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {
    Admin findByUsername(String username);
}
