package com.project.haiportal.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Matkul {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String name;
    private int sks;
    private int semester;
    
    @ManyToOne
    @JoinColumn(name = "id_lecturer", referencedColumnName = "id")
    private Lecturer idLecturer;

    @ManyToOne
    @JoinColumn(name = "id_major", referencedColumnName = "id")
    private Major idMajor;


}
