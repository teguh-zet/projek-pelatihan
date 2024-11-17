package com.project.haiportal.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;
    private String nim;
    private String pass;
    private String place;
    private LocalDate birth;
    private String address;
    private int semester;
    private String gender;
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "id_schedule", referencedColumnName = "id")
    private Schedule idSchedule;

    @ManyToOne
    @JoinColumn(name = "id_major", referencedColumnName = "id")
    private Major idMajor;

}
