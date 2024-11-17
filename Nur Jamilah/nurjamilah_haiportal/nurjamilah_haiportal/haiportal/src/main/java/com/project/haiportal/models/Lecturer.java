package com.project.haiportal.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String name;
    private String pass;
    private String email;
    private String phone;
    private String address;
    private String image;

    @OneToMany(mappedBy = "idLecturer")
    private List<Matkul> matkuls;
}
