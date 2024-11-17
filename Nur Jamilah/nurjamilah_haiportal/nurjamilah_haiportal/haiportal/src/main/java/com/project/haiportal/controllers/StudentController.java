package com.project.haiportal.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.haiportal.models.Student;
import com.project.haiportal.repositories.MajorRepository;
import com.project.haiportal.repositories.StudentRepository;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private MajorRepository majorRepository;


    @GetMapping("list-student")
    public String listStudent(Model model) {
        Student student = new Student();
        model.addAttribute("add-students", student);
        model.addAttribute("major", majorRepository.findAll());
        model.addAttribute("students", studentRepository.findAll());
        return "student";
    }


    @PostMapping("save-student")
    public String saveStudent(@ModelAttribute("student") Student student) {
        if(student.getBirth().getYear() <2007){
            studentRepository.save(student); 
        }
        return "redirect:/list-student";

    }

    @GetMapping("update-student/{id}")
    public String updateStudent(@PathVariable(value = "id") Integer id, Model model) {
        Student student = studentRepository.getReferenceById(id);
        model.addAttribute("student", student);
        model.addAttribute("major", majorRepository.findAll());
        return "update-student";
    }

    @GetMapping("delete-student/{id}")
    public String deleteStudent(@PathVariable(value = "id") Integer id, Model model) {
        studentRepository.deleteById(id);
        return "redirect:/list-student";
    }

    
}
