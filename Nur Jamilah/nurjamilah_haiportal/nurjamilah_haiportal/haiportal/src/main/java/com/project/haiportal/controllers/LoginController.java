package com.project.haiportal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.haiportal.models.Admin;
import com.project.haiportal.models.Lecturer;
import com.project.haiportal.models.Student;
import com.project.haiportal.repositories.AdminRepository;
import com.project.haiportal.repositories.LecturerRepository;
import com.project.haiportal.repositories.StudentRepository;


@Controller
public class LoginController {
    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdminRepository adminRepository;

    public int idl;
    public int ids;
    public int ida;

    
    @GetMapping("login")
    public String loginAdmin() {
        return "login";
    }

    @PostMapping("cek-login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
            Model model) {
        List<Lecturer> lecturers = lecturerRepository.findAll();
        List<Student> students = studentRepository.findAll();
        List<Admin> admins = adminRepository.findAll();
        // if (username.equals("admin") && password.equals("1234")) {
        //     return "redirect:/home";
        // }

        for (Admin a : admins) {
            ida = a.getId();
            if (username.equals(a.getUsername()) && password.equals(a.getPass())) {
                return "redirect:/home";
            }

        }
        for (Lecturer lecturer : lecturers) {
            idl = lecturer.getId();
            if (username.equals(lecturer.getEmail()) && password.equals(lecturer.getPass())) {
                return "redirect:/profil-lectur";
            }

        }

        for (Student student : students) {
            ids = student.getId();
            if (username.equals(student.getEmail()) && password.equals(student.getPass())) {
                return "redirect:/profil-student";
            }
        }

        return "redirect:/login";
    }

    @GetMapping("/profil-student")
    public String prof(Model model) {
        Student student = studentRepository.getReferenceById(ids);
        model.addAttribute("student", student);
        return "profil-student";
    }

    @GetMapping("profil-lectur")
    public String profilLec(Model model) {
        Lecturer lecturer = lecturerRepository.getReferenceById(idl);
        model.addAttribute("dosen", lecturer);

        return "profil-lectur";
    }

}
