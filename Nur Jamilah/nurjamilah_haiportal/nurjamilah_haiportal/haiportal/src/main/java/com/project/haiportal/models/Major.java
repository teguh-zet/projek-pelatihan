package com.project.haiportal.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;

    @OneToMany(mappedBy = "idMajor")
    private List<Matkul> matkuls;

    // @OneToMany(mappedBy = "idStudent")
    // private List<Student> idStudents;
}
