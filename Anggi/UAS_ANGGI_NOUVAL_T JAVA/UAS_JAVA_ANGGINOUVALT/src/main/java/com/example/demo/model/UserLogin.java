package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserLogin {
    @Id
    // @GeneratedValue(strategy = GenerationType.class)
    private Integer id;

    String username;

    String password;
}
