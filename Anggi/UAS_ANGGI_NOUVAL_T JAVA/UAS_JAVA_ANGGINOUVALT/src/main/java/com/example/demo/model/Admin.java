package com.example.demo.model;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class Admin {

    private Integer id;
    private String username;
    private String password;

    public Admin(String username ,String password){
        this.username = username;
        this.password = password;
    }
}
