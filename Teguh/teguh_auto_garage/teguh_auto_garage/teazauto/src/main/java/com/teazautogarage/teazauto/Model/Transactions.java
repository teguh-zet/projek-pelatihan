package com.teazautogarage.teazauto.Model;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateOfTransaction;
    private String fullName;
    private String addres;
    private long price;
    @ManyToOne
    @JoinColumn(name = "nama_costumer", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_car", referencedColumnName = "id")
    private Car car;




}
