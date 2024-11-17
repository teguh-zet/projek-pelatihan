package com.penjualan.pasar.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AkunAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String pin;
    private String keterangan;
}
