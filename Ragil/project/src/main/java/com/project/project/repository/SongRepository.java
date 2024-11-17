package com.project.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.project.model.Song;

public interface SongRepository extends JpaRepository <Song,Integer>{
    
}
