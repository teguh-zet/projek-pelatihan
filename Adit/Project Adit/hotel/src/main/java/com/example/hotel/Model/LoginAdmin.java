package com.example.hotel.Model;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LoginAdmin {
    @Id
    private Integer id;

    String username;

    String password;
}