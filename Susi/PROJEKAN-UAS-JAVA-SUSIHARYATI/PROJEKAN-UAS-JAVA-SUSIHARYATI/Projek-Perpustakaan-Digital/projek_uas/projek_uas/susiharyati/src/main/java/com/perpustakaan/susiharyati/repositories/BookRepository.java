package com.perpustakaan.susiharyati.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perpustakaan.susiharyati.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
    Book findByid(Integer id);
}
