package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Keranjang{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  Integer totalHarga;
    private Integer banyak;

    // private String gambar;
    // private String nama;
    // private String deskripsi;
    // private String tipe;
    // private Integer harga;
    // private Integer stok;
      @ManyToOne
    @JoinColumn(name = "id_barang", referencedColumnName = "id")
    private Barang id_Barang;

      @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User id_user;

    
   
}
