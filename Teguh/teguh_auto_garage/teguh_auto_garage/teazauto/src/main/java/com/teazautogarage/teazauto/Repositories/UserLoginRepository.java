package com.teazautogarage.teazauto.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teazautogarage.teazauto.Model.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
    List<UserLogin> findByUsername(String username);
}
