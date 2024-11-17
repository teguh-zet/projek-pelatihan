package com.project.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.project.model.Mymusic;

@Repository
public interface MymusicRepository extends JpaRepository<Mymusic,Integer>{
    
}
