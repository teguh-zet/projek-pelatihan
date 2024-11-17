package com.project.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.project.model.Song;
import com.project.project.repository.SongRepository;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public List<Song> getAllMusic() {
        return songRepository.findAll();
    }
}
