package com.perpustakaan.susiharyati.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perpustakaan.susiharyati.models.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Integer>{

    Borrow findByid(Integer id);
}
