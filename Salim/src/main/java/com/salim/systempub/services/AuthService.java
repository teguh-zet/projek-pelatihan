package com.salim.systempub.services;

import org.springframework.ui.Model;

import com.salim.systempub.config.ServiceConfig;
import com.salim.systempub.models.Auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends ServiceConfig{

    public String add(Model model) {
        model.addAttribute("member",memberRepository.findAll());
        model.addAttribute("add",new Auth());
        return "admin/add-auth";
    }
    public String save(Auth a, Model model) {
        boolean isThere = false;
        for (Auth auth : authRepository.findAll()) {
            if (auth.getUsername().equals(a.getUsername())) {
                isThere = true;
                break;
            }
        }
        System.out.println(a.getUsername());
        if (isThere) {
            model.addAttribute("errormessage", "Data Telah Ada, Masukan Data Lain");
            return "error";
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Auth auth = a;
            auth.setPassword(passwordEncoder.encode(a.getPassword()));
            auth.setRole(a.getRole().toUpperCase());
            authRepository.save(auth);
            return "redirect:/admin/";
        }
    }
}
