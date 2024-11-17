package com.moslemwear.bismillahproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moslemwear.bismillahproject.models.UserLogin;
import java.util.List;


public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {
    List<UserLogin> findByUsername(String username);
}
