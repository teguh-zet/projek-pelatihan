package com.project.haiportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.haiportal.models.Matkul;
import com.project.haiportal.repositories.LecturerRepository;
import com.project.haiportal.repositories.MajorRepository;
import com.project.haiportal.repositories.MatkulRepository;

@Controller
public class MatkulController {
    @Autowired
    private MatkulRepository matkulRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private MajorRepository majorRepository;

    @GetMapping("list-matkul")
    public String listMatkul(Model model) {
        Matkul matkul = new Matkul();
        model.addAttribute("add-matkuls", matkul);
        model.addAttribute("matkuls", matkulRepository.findAll());
        model.addAttribute("major", majorRepository.findAll());
        model.addAttribute("lecturer", lecturerRepository.findAll());
        return "show-matkul";
    }

    // @GetMapping("add-matkul")
    // public String addmatkul(Model model){
    // Matkul matkul = new Matkul();
    // model.addAttribute("matkuls", matkul);
    // model.addAttribute("lecturer", lecturerRepository.findAll());
    // return "save-matkul";
    // }

    @PostMapping("save-matkul")
    public String saveMatkul(@ModelAttribute("matkul") Matkul matkul) {
        matkulRepository.save(matkul);
        return "redirect:/list-matkul";
    }

    @GetMapping("update-matkul/{id}")
    public String updateMatkul(@PathVariable(value = "id") Integer id, Model model) {
        Matkul matkul = matkulRepository.getReferenceById(id);
        model.addAttribute("matkul", matkul);
        model.addAttribute("lecturers", lecturerRepository.findAll());
        return "update-matkul";
    }

    @GetMapping("delete-matkul/{id}")
    public String deleteMatkul(@PathVariable(value = "id") Integer id, Model model) {
        matkulRepository.deleteById(id);
        return "redirect:/list-matkul";
    }
}
