package com.project.project.repository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.project.model.FavSong;
// import com.project.project.model.Song;

public interface FavSongRepository extends JpaRepository<FavSong,Long>{
    
}
