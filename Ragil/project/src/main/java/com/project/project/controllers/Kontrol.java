package com.project.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.project.model.Song;
import com.project.project.model.User;
import com.project.project.repository.SongRepository;
import com.project.project.services.UserService;

import org.springframework.ui.Model;

@Controller
public class Kontrol {

    @Autowired
    private UserService userService;
    @Autowired
    private SongRepository songRepository;

    public static String tampUser="";

    @GetMapping("/")
    public String Home(Model model) {
        if (songRepository.findAll().isEmpty()) {
            for (Song lagu : SongFromJS.panggilMusic()) {
                songRepository.save(lagu);
            }
        }
        model.addAttribute("music", SongFromJS.panggilMusic());
        return "home2";
    }
    @GetMapping("/rekomendasi")
    public String rekomended(Model model){
        if (songRepository.findAll().isEmpty()) {
                for (Song lagu : SongFromJS.panggilMusic()) {
                    songRepository.save(lagu);
                }
            }
            model.addAttribute("music", SongFromJS.panggilMusic());
        return "Tampilan";
    }

    @GetMapping("/sign-in")
    public String signInForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute("user") User user, Model model) {
        boolean pengecekan = userService.pengecekanuser(user.getUsername(), user.getPassword());

        if (pengecekan) {
            tampUser=user.getUsername();
            // Tambahkan informasi pengguna ke model
            model.addAttribute("username", user.getUsername());
            if (songRepository.findAll().isEmpty()) {
                for (Song lagu : SongFromJS.panggilMusic()) {
                    songRepository.save(lagu);
                }
            }
            model.addAttribute("music", SongFromJS.panggilMusic());
            return "Tampilan";
        } else {
            model.addAttribute("error", "Username atau password salah");
            return "redirect:/sign-in";
        }
    }

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("user", new User());
        return "daftar";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute("user") User user, Model model) {
        if (!userService.isUsernameAvailable(user.getUsername())) {
            model.addAttribute("error", "Username sudah digunakan");
            return "redirect:/sign-up";
        }
        userService.registerUser(user);
        return "redirect:/sign-in";
    }
}
