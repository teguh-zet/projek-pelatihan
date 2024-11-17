package com.example.fajri_roxilius_store.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserLogin {
    @Id
    private Integer id;

    private String name;
    private String contact;
    private String email;
    private String username;
    private String password;
    private String saldo;

}