package com.project.haiportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.haiportal.models.Grade;
import com.project.haiportal.models.Matkul;
import com.project.haiportal.models.Student;
import com.project.haiportal.repositories.GradeRepository;
import com.project.haiportal.repositories.MatkulRepository;
import com.project.haiportal.repositories.StudentRepository;

@Controller
public class GradeController {
    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private MatkulRepository matkulRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("input-grade")
    public String inputGrade(Model model) {
        model.addAttribute("grades", gradeRepository.findAll());
        model.addAttribute("matkuls", matkulRepository.findAll());
        model.addAttribute("students", studentRepository.findAll());
        
        return "input-grade";
    }

    @PostMapping("save-grade")
    public String saveGrade(@RequestParam("matkulId") Integer matkulId,
                            @RequestParam("studentId") Integer studentId,
                            @RequestParam("value") int value) {
        Matkul matkul = matkulRepository.findById(matkulId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);

        if (matkul != null && student != null) {
            Grade grade = new Grade();
            grade.setMatkul(matkul);
            grade.setStudent(student);
            grade.setValue(value);
            gradeRepository.save(grade);
        }

        return "redirect:/input-grade";
    }

    @GetMapping("edit-grade/{id}")
    public String editGrade(@PathVariable(value = "id") Integer id, Model model) {
        Grade grade = gradeRepository.findById(id).orElse(null);

        if (grade != null) {
            model.addAttribute("grade", grade);
            model.addAttribute("matkuls", matkulRepository.findAll());
            model.addAttribute("students", studentRepository.findAll());
            return "update-grade";
        } else {
            // Handle the case where the grade with the specified ID is not found
            return "redirect:/input-grade";
        }
    }

    @PostMapping("update-grade/{id}")
    public String updateGrade(@PathVariable(value = "id") Integer id,
                              @RequestParam("matkulId") Integer matkulId,
                              @RequestParam("studentId") Integer studentId,
                              @RequestParam("value") int value) {
        Grade grade = gradeRepository.findById(id).orElse(null);
        Matkul matkul = matkulRepository.findById(matkulId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);

        if (grade != null && matkul != null && student != null) {
            grade.setMatkul(matkul);
            grade.setStudent(student);
            grade.setValue(value);
            gradeRepository.save(grade);
        }

        return "redirect:/input-grade";
    }

    @GetMapping("delete-grade/{id}")
    public String deleteGrade(@PathVariable(value = "id") Integer id) {
        gradeRepository.deleteById(id);
        return "redirect:/input-grade";
    }

    @GetMapping("show-grade")
    public String showGrade(Model model) {
        model.addAttribute("grades", gradeRepository.findAll());
        model.addAttribute("matkuls", matkulRepository.findAll());
        model.addAttribute("students", studentRepository.findAll());
        
        return "show-grade";
    }
    @GetMapping("show-grade2")
    public String showGrade2(Model model) {
        model.addAttribute("grades", gradeRepository.findAll());
        model.addAttribute("matkuls", matkulRepository.findAll());
        model.addAttribute("students", studentRepository.findAll());
        
        return "show-grade2";
    }
}
