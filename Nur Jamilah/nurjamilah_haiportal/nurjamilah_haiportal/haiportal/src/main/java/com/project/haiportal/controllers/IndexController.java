package com.project.haiportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.haiportal.repositories.LecturerRepository;
import com.project.haiportal.repositories.MatkulRepository;
import com.project.haiportal.repositories.StudentRepository;

@Controller
public class IndexController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private MatkulRepository matkulRepository;

    @GetMapping("home")
    public String home(Model model) {
        model.addAttribute("schedule", "list-schedule");
        model.addAttribute("regis", "register");
        model.addAttribute("matkul", "list-matkul");
        model.addAttribute("major", "list-major");
        model.addAttribute("lecturer", "list-lecturer");
        model.addAttribute("student", "list-student");
        model.addAttribute("index", "home");

        long totalStudent = studentRepository.count();
        model.addAttribute("totalStudent", totalStudent);

        long totalLectur = lecturerRepository.count();
        model.addAttribute("totalLectur", totalLectur);

        long totalMatkul = matkulRepository.count();
        model.addAttribute("totalMatkul", totalMatkul);

        return "index";
    }

}
