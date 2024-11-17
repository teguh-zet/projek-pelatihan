package com.penjualan.pasar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.penjualan.pasar.entity.AkunAdmin;


public interface AkunAdminRepository extends JpaRepository<AkunAdmin,Integer>{
    AkunAdmin findAdminByPin(String pin);
}