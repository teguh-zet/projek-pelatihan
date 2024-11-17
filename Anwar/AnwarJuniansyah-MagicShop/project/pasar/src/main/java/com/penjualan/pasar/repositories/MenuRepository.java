package com.penjualan.pasar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.penjualan.pasar.entity.Kategori;
import com.penjualan.pasar.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu,Integer>{
    List<Menu> findByKategori(Kategori kategori);;
    List<Menu> findAllByName(String name);
    Menu findByName(String name);
    List<Menu> findByStok(int stok);
    List<Menu> findByHarga(int harga);
    List<Menu> findByKeterangan(String keterangan);
}