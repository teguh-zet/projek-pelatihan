package com.teazautogarage.teazauto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teazautogarage.teazauto.Model.User;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
