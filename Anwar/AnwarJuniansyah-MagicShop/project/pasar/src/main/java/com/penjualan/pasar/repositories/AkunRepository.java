package com.penjualan.pasar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.penjualan.pasar.entity.Akun;


public interface AkunRepository extends JpaRepository<Akun,Integer>{
    Akun findByName(String name);
    Akun findByGmail(String gmail);
    List<Akun> findGmailByGmail(String gmail);
}