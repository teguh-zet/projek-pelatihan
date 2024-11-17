package com.project.haiportal.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_matkul", referencedColumnName = "id")
    private Matkul matkul;

    @ManyToOne
    @JoinColumn(name = "id_student", referencedColumnName = "id")
    private Student student;

    private int value; // Nilai matakuliah
}
