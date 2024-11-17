package com.example.cadangan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.cadangan.models.Polyclinic;
import com.example.cadangan.repositories.PolyclinicRepository;

@Controller
public class PolyclinicController {
    @Autowired
    private PolyclinicRepository polyclinicRepository;

    @GetMapping("/show-polyclinic")
    public String showAllPolyclinic(Model model) {
        model.addAttribute("polyclinics", polyclinicRepository.findAll());
        return "show-polyclinic";
    }

    @GetMapping("/add-polyclinic")
    public String addPolyclinic(Model model) {
        model.addAttribute("polyclinic", new Polyclinic());
        return "save-polyclinic";
    }

    @PostMapping("/save-polyclinic")
    public String savePolyclinic(Polyclinic polyclinic) {
        polyclinicRepository.save(polyclinic);
        return "redirect:/show-polyclinic";
    }

    @GetMapping("/update-polyclinic/{id}")
    public String updatePolyclinic(@PathVariable(value = "id") Integer id, Model model) {
        Polyclinic polyclinic = polyclinicRepository.findById(id).orElse(null);
        model.addAttribute("polyclinic", polyclinic);
        return "update-polyclinic";
    }

    @PostMapping("/update-polyclinic/{id}")
    public String processUpdatePolyclinic(@PathVariable Integer id, @ModelAttribute("polyclinic") Polyclinic updatedPolyclinic) {
        polyclinicRepository.save(updatedPolyclinic);
        return "redirect:/show-polyclinic";
    }

    @GetMapping("/delete-polyclinic/{id}")
    public String deletePolyclinic(@PathVariable(value = "id") Integer id) {
        polyclinicRepository.deleteById(id);
        return "redirect:/show-polyclinic";
    }
}
