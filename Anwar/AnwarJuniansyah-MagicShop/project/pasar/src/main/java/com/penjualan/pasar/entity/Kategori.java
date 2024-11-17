package com.penjualan.pasar.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Kategori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
