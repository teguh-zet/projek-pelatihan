package com.penjualan.pasar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.penjualan.pasar.entity.Kategori;


public interface KategoriRepository extends JpaRepository<Kategori,Integer>{
    Kategori findByName(String name);
    List<Kategori> findAllByName(String name);
}