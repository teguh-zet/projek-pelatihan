package com.example.cadangan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.cadangan.models.Medicine;
import com.example.cadangan.repositories.MedicineRepository;

@Controller
public class MedicineController {

    @Autowired
    private MedicineRepository medicineRepository;

    @GetMapping("/show-medicine")
    public String showAllMedicine(Model model) {
        model.addAttribute("medicines", medicineRepository.findAll());
        return "show-medicine";
    }

    @GetMapping("/add-medicine")
    public String addMedicine(Model model) {
        model.addAttribute("medicine", new Medicine());
        return "save-medicine";
    }

    @PostMapping("/save-medicine")
    public String saveMedicine(Medicine medicine) {
        medicineRepository.save(medicine);
        return "redirect:/show-medicine";
    }

    @GetMapping("/update-medicine/{id}")
    public String updateMedicine(@PathVariable(value = "id") Integer id, Model model) {
        Medicine medicine = medicineRepository.findById(id).orElse(null);
        model.addAttribute("medicine", medicine);
        return "update-medicine";
    }

    @PostMapping("/update-medicine/{id}")
    public String processUpdateMedicine(@PathVariable Integer id, @ModelAttribute("medicine") Medicine updatedMedicine) {
        medicineRepository.save(updatedMedicine);
        return "redirect:/show-medicine";
    }

    @GetMapping("/delete-medicine/{id}")
    public String deleteMedicine(@PathVariable(value = "id") Integer id) {
        medicineRepository.deleteById(id);
        return "redirect:/show-medicine";
    }
}
