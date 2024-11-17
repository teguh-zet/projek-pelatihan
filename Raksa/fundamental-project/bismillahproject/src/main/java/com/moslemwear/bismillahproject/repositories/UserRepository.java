package com.moslemwear.bismillahproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moslemwear.bismillahproject.models.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByUsername(String username);
}
