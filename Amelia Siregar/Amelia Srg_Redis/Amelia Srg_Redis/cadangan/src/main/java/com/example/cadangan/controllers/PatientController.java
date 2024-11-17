package com.example.cadangan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.cadangan.models.Patient;
import com.example.cadangan.repositories.PatientRepository;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/show-patient")
    public String showAllPatients(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "show-patient";
    }

    @GetMapping("/add-patient")
    public String addPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "save-patient";
    }

    @PostMapping("/save-patient")
    public String savePatient(@ModelAttribute("patient") Patient patient) {
        patientRepository.save(patient);
        return "redirect:/show-patient";
    }

    @GetMapping("/update-patient/{id}")
    public String updatePatient(@PathVariable(value = "id") Integer id, Model model) {
        Patient patient = patientRepository.findById(id).orElse(null);
        model.addAttribute("patient", patient);
        return "update-patient";
    }

    @PostMapping("/update-patient/{id}")
    public String processUpdatePatient(@PathVariable Integer id, @ModelAttribute("patient") Patient updatedPatient) {
        patientRepository.save(updatedPatient);
        return "redirect:/show-patient";
    }

    @GetMapping("/delete-patient/{id}")
    public String deletePatient(@PathVariable(value = "id") Integer id) {
        patientRepository.deleteById(id);
        return "redirect:/show-patient";
    }
}
