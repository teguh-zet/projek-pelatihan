package com.salim.systempub.controllers;

import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salim.systempub.config.AuthConfig;
import com.salim.systempub.models.Auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController extends AuthConfig {

    @GetMapping("/")
    public String home(Model model) {
        if(authRepository.findAll().isEmpty()){
            BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
            Auth auth=new Auth();
            auth.setUsername("admin");
            auth.setPassword(passwordEncoder.encode("123"));
            auth.setRole("ADMIN");
            authRepository.save(auth);
        }
        return "index";
    }
    @GetMapping("/divisi/")
    public String divitionList(){
        return "list-divisi";
    }
    @GetMapping("/find")
    public String find(@RequestParam(value = "nama") String name, Model model) {
        
        return memberService.find(name, model);
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/logout")
    public String customLogout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null){
        new SecurityContextLogoutHandler().logout(request, response, authentication);
    }
    return "redirect:/login";
    }
    @GetMapping("/denied")
    public String costumDenied(){
        return "page-error";
    }
    @GetMapping("/calladmin")
    public String callAdmin(Model model){
        model.addAttribute("response","");
        return "calladmin";
    }
    @PostMapping("/calladmin")
    public String sendToAdmin(@RequestParam("name")String name,@RequestParam("number")String number,@RequestParam("message")String message,Model model){
        AuthConfig.contact=name;
        AuthConfig.number=number;
        AuthConfig.messageAdmin=message;
        model.addAttribute("response","Pesan Terkirim, Tunggu Infomasi Lebih Lanjut Lewat Data Yang Anda Kirim");
        return "calladmin";
    }
    @GetMapping("/about/")
    public String aboutPage(){
        return "about";
    }
}
