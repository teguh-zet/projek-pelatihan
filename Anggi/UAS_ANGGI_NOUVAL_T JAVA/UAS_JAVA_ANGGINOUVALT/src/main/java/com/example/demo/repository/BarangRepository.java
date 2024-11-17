package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Barang;


public interface BarangRepository extends JpaRepository<Barang, Integer> {
     Barang findByTipe(String kategori);
     List<Barang> findByNamaContainingIgnoreCase(String nama);
     List<Barang> findByTipeContainingIgnoreCase(String name);
     List<Barang> findByDeskripsiContainingIgnoreCase(String name);
     // List<Barang>findAllHarga();

   
}

