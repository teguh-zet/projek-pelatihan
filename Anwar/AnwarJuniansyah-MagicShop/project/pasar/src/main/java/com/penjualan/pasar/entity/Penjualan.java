package com.penjualan.pasar.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Penjualan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user",referencedColumnName = "id")
    private Akun akun;

    @ManyToOne
    @JoinColumn(name = "id_menu",referencedColumnName = "id")
    private Menu menu;

    private int jumlah;
}