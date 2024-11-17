package com.salim.systempub.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nim;
    private String name;
    private String gender;
    private String address;
    private String description;
    private int ryear;
    @ManyToOne
    @JoinColumn(name = "id_generation",referencedColumnName = "id")
    private Generation idgen;

}
