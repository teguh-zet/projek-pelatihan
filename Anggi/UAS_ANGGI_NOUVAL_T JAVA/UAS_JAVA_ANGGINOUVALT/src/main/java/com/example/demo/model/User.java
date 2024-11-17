package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
@Getter
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nama;
    private Date lahir;
    private String username;
    private String password;
    private String nohp;
    private String peringatan;
    private String status;
    private String email;
    private String alamat;
    private Integer utang;
    private Integer uang;
    public User(String peringatan , String status){
        this.peringatan = peringatan;
        this.status = status;
    }
    public User(){

    }
}
