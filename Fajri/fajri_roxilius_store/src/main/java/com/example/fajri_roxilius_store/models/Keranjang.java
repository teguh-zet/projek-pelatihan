package com.example.fajri_roxilius_store.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Keranjang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer qty;
    private String total;

    @ManyToOne
    @JoinColumn(name = "prduct_id")
    private Product product;

    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}