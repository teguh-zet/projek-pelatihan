package com.project.haiportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.haiportal.models.Major;
import com.project.haiportal.repositories.MajorRepository;

@Controller
public class MajorController {
    @Autowired
    private MajorRepository majorRepository;

    @GetMapping("list-major")
    public String listMajor(Model model) {
        Major major = new Major();
        model.addAttribute("add-majors", major);
        model.addAttribute("majors", majorRepository.findAll());
        return "show-major";
    }

    // @GetMapping("add-major")
    // public String addMajor(Model model){
    // Major major = new Major();
    // model.addAttribute("majors", major);
    // return "save-major";
    // }

    @PostMapping("save-major")
    public String saveMajor(@ModelAttribute("major") Major major) {
        majorRepository.save(major);
        return "redirect:/list-major";
    }

    @GetMapping("update-major/{id}")
    public String updateMajor(@PathVariable(value = "id") Integer id, Model model) {
        Major major = majorRepository.getReferenceById(id);
        model.addAttribute("major", major);
        return "update-major";
    }

    @GetMapping("delete-major/{id}")
    public String deleteMajor(@PathVariable(value = "id") Integer id, Model model) {
        majorRepository.deleteById(id);
        return "redirect:/list-major";
    }

}
