package com.project.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.project.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
