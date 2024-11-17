package com.example.cadangan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.cadangan.models.Doctor;
import com.example.cadangan.repositories.DoctorRepository;

@Controller
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/show-doctor")
    public String showAllDoctors(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        return "show-doctor";
    }

    @GetMapping("/add-doctor")
    public String addDoctor(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "save-doctor";
    }

    @PostMapping("/save-doctor")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorRepository.save(doctor);
        return "redirect:/show-doctor";
    }

    @GetMapping("/update-doctor/{id}")
    public String updateDoctor(@PathVariable(value = "id") Integer id, Model model) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        model.addAttribute("doctor", doctor);
        return "update-doctor";
    }

    @PostMapping("/update-doctor/{id}")
    public String updateDoctor(@PathVariable(value = "id") Integer id, @ModelAttribute("doctor") Doctor updatedDoctor) {
        // Update the existing doctor with the new information
        Doctor existingDoctor = doctorRepository.findById(id).orElse(null);
        if (existingDoctor != null) {
            existingDoctor.setName(updatedDoctor.getName());
            existingDoctor.setAge(updatedDoctor.getAge());
            existingDoctor.setSpecialist(updatedDoctor.getSpecialist());
            existingDoctor.setAddress(updatedDoctor.getAddress());
            existingDoctor.setTelp(updatedDoctor.getTelp());
            doctorRepository.save(existingDoctor);
        }

        return "redirect:/show-doctor";
    }

    @GetMapping("/delete-doctor/{id}")
    public String deleteDoctor(@PathVariable(value = "id") Integer id) {
        doctorRepository.deleteById(id);
        return "redirect:/show-doctor";
    }
}
