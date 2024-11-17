package com.example.fajri_roxilius_store.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.fajri_roxilius_store.models.Keranjang;
import com.example.fajri_roxilius_store.models.User;

public interface KeranjangRepository  extends JpaRepositoryImplementation<Keranjang, Integer>{
    List<Keranjang> findByProductId(Integer id);
    List<Keranjang> findByUser(User user);
    
}
