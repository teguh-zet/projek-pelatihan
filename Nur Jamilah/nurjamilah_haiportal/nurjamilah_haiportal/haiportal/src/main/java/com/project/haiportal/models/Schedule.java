package com.project.haiportal.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String hari;

    @Column(columnDefinition = "TIME")
    private LocalTime start;

    @Column(columnDefinition = "TIME")
    private LocalTime end;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_matkul", referencedColumnName = "id")
    private Matkul idMatkul;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_room", referencedColumnName = "id")
    private Room idRoom;
}
