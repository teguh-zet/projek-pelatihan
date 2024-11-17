package com.moslemwear.bismillahproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class AdminLogin {
    @Id
    private Integer id;
    String username;
    String password;
}