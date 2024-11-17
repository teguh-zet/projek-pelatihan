package com.penjualan.pasar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.penjualan.pasar.entity.Akun;
import com.penjualan.pasar.entity.Keranjang;

public interface KeranjangRepository extends JpaRepository<Keranjang,Integer>{
    List<Keranjang> findByAkun(Akun akun);
}