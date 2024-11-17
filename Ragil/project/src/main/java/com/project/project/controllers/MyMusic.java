package com.project.project.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.project.project.model.Mymusic;
import com.project.project.model.Song;
import com.project.project.repository.MymusicRepository;
import com.project.project.repository.SongRepository;

@Controller
public class MyMusic {
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private MymusicRepository mymusicRepository;

    @GetMapping("/My-Music")
    public String Musiksaya(Model model) {
        model.addAttribute("username", Kontrol.tampUser);

        if (songRepository.findAll().isEmpty()) {
            for (Song lagu : SongFromJS.panggilMusic()) {
                songRepository.save(lagu);
            }
        }
        model.addAttribute("music", SongFromJS.panggilMusic());
        model.addAttribute("songs", mymusicRepository.findAll());
        return "MyMusic";
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

    @PostMapping("/upload")
    public String handleFileUpload(@RequestPart("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Pilih berkas untuk diunggah");
            return "redirect:/My-Music";
        }

        try {
            // Set tempat menyimpan file dalam proyek
            Path targetPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static", "audio",
                    file.getOriginalFilename());

            // Simpan file di dalam proyek
            file.transferTo(targetPath.toFile());

            // Simpan URL atau path di dalam database
            String url = "http://localhost:8080/audio/" + file.getOriginalFilename();

            Mymusic mymusic = new Mymusic();
            mymusic.setSongpath(url);
            mymusic.setNamefile(file.getOriginalFilename());

            mymusicRepository.save(mymusic);
            System.out.println("tes");

        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/My-Music";
        }

        return "redirect:/My-Music";
    }

    @GetMapping("/deletesong/{id}")
    public String deleteSong(@PathVariable("id") Integer id) {
        mymusicRepository.deleteById(id);
        return "redirect:/My-Music";
    }
}