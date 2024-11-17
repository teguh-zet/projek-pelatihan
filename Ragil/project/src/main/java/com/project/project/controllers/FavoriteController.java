package com.project.project.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.project.model.FavSong;
import com.project.project.model.Song;
import com.project.project.repository.FavSongRepository;
import com.project.project.repository.SongRepository;

@Controller
@RequestMapping("/fav")
public class FavoriteController {
    @Autowired
    private FavSongRepository favSongRepository;

    @Autowired
    private SongRepository songRepository;

    @GetMapping
    public String Favorite(Model model) {
        // favSongRepository.deleteAll();
        // ArrayList<FavSong> tampSong = new ArrayList<>();
        // for (Song song : songRepository.findAll()) {
        //     for (FavSong fav : favSongRepository.findAll()) {
        //         if (fav.getSongName().equals(song.getSongName())) {
        //             tampSong.add(song);
        //         }
        //     }
        // }
        model.addAttribute("favoriteSongs", favSongRepository.findAll());
        return "fav";
    }

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> receiveSongs(@RequestBody List<Song> songs) {
        Map<String, String> response = new HashMap<>();
        if (!favSongRepository.findAll().isEmpty()) {
            favSongRepository.deleteAll();
        }
        for (Song song : songs) {
            FavSong favSong = new FavSong();
            System.out.println("ini id musik:" + song.getId());
            favSong.setIdMusik(song.getId());
            favSong.setSongName(song.getSongName());
            favSong.setArtist(song.getArtist());
            favSong.setPoster(song.getPoster());
            favSongRepository.save(favSong);
        }
        System.out.println("Cek Data");
        response.put("message", "Data lagu berhasil disimpan ke database");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/send")
    public String sendData() {
        return "sendData";
    }

    @GetMapping("/discover")
    public String kover(Model model) {
        if (songRepository.findAll().isEmpty()) {
            for (Song lagu : SongFromJS.panggilMusic()) {
                songRepository.save(lagu);
            }
        }
        model.addAttribute("music", SongFromJS.panggilMusic());
        return "Tampilan";
    }
}