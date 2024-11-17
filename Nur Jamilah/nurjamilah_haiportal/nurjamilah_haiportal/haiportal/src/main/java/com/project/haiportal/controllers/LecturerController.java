package com.project.haiportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.haiportal.models.Lecturer;
import com.project.haiportal.repositories.LecturerRepository;

@Controller
public class LecturerController {
    @Autowired
    private LecturerRepository lecturerRepository;

    // @GetMapping("lecturer")
    // public String lecturerPage(){
    // return "lecturer";
    // }
    @GetMapping("list-lecturer")
    public String listlecturer(Model model) {
        Lecturer lecturer = new Lecturer();
        model.addAttribute("add-lecturers", lecturer);
        model.addAttribute("lecturers", lecturerRepository.findAll());
        return "lecturer";
    }

    // @GetMapping("add-lecturer")
    // public String listLecturer(Model model){
    // Lecturer lecturer = new Lecturer();
    // model.addAttribute("lecturers", lecturer);
    // return "save-lecturer";
    // }

    @PostMapping("save-lecturer")
    public String saveLecturer(@ModelAttribute("lecturer") Lecturer lecturer) {
        lecturer.setImage("/img/lecturer.png");
        lecturerRepository.save(lecturer);
        return "redirect:/list-lecturer";
    }

    @GetMapping("update-lecturer/{id}")
    public String updatelecturer(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("lecturer", lecturerRepository.getReferenceById(id));
        return "update-lecturer";
    }

    @GetMapping("delete-lecturer/{id}")
    public String deleteLecturer(@PathVariable(value = "id") Integer id, Model model) {
        lecturerRepository.deleteById(id);
        return "redirect:/list-lecturer";
    }
}
