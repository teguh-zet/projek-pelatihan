package com.teazautogarage.teazauto.Model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String color;
    private String statue;
    private String km;
    private int year;
    private Long price;
    private String imagePath;
    @ManyToOne
    @JoinColumn(name = "id_brand", referencedColumnName = "id")
    private Brand brand;

    // @ManyToOne
    // @JoinColumn(name = "id_status",referencedColumnName = "id")
    // private Status status;

   
}
