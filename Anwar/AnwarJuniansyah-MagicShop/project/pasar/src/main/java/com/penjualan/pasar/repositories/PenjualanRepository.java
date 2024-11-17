package com.penjualan.pasar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.penjualan.pasar.entity.Akun;
import com.penjualan.pasar.entity.Menu;
import com.penjualan.pasar.entity.Penjualan;

public interface PenjualanRepository extends JpaRepository<Penjualan,Integer>{
    List<Penjualan> findByMenu(Menu menu);
    List<Penjualan> findByAkun(Akun akun);
    List<Penjualan> findByJumlah(int jumlah);
}