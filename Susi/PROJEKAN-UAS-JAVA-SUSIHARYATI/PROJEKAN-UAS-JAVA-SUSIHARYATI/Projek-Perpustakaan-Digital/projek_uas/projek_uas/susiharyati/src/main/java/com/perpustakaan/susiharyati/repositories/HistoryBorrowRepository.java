package com.perpustakaan.susiharyati.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perpustakaan.susiharyati.models.HistoryBorrow;

public interface HistoryBorrowRepository extends JpaRepository<HistoryBorrow, Integer>{
    
}
