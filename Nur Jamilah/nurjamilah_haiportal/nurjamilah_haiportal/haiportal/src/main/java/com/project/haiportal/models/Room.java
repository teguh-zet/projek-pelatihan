package com.project.haiportal.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int kapasitas;

    @OneToMany(mappedBy = "idRoom")
    private List<Schedule> schedules;
}
