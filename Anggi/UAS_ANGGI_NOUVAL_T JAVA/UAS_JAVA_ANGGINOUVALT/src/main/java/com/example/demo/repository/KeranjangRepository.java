package com.example.demo.repository;





import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Keranjang;

public interface KeranjangRepository extends JpaRepository<Keranjang, Integer> {
   
}
