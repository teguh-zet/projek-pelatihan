package com.project.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LauvController {
    
    @GetMapping("/lauv")
    public String lauv(){
        return "lauv";
    }
}
