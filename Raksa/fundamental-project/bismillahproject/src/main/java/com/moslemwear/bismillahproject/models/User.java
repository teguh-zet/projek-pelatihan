package com.moslemwear.bismillahproject.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data    
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String email;
    private Integer money = 0;
}






