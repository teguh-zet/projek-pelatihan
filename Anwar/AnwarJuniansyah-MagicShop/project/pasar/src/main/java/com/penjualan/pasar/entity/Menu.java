package com.penjualan.pasar.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String keterangan;
    private int harga;
    private int stok;
    @ManyToOne
    @JoinColumn(name = "id_jenis",referencedColumnName = "id")
    private Kategori kategori;
    private String gambar;
}
