package com.penjualan.pasar.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Keranjang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_akun",referencedColumnName = "id")
    private Akun akun;

    @ManyToOne
    @JoinColumn(name = "id_barang",referencedColumnName = "id")
    private Menu menu;

    private int jumlah;
}
