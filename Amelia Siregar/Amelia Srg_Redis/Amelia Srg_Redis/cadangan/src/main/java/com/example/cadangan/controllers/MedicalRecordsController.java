package com.example.cadangan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.transaction.annotation.Transactional;

import com.example.cadangan.models.Doctor;
import com.example.cadangan.models.MedicalRecords;
import com.example.cadangan.repositories.DoctorRepository;
import com.example.cadangan.repositories.MedicalRecordsRepository;
import com.example.cadangan.repositories.MedicineRepository;
import com.example.cadangan.repositories.PatientRepository;
import com.example.cadangan.repositories.PolyclinicRepository;

@Controller
public class MedicalRecordsController {

    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PolyclinicRepository polyclinicRepository;

    @GetMapping("/show-mr")
    public String showMedicalRecords(Model model) {
        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.findAll();
        model.addAttribute("medicalRecords", medicalRecordsList);
        return "show-mr";
    }

    @GetMapping("/add-mr")
    public String addMedicalRecords(Model model) {
        model.addAttribute("medicalRecords", new MedicalRecords());
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("medicines", medicineRepository.findAll());
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("polyclinics", polyclinicRepository.findAll());
        return "save-mr";
    }

    @Transactional
    @PostMapping("/save-mr")
    public String saveMedicalRecords(@ModelAttribute("medicalRecords") MedicalRecords medicalRecords) {
        if (medicalRecords != null && medicalRecords.getPatient() != null
                && medicalRecords.getDoctor() != null && medicalRecords.getMedicine() != null
                && medicalRecords.getPolyclinic() != null) {
            saveOrUpdateDoctor(medicalRecords.getDoctor());
            patientRepository.save(medicalRecords.getPatient());
            medicineRepository.save(medicalRecords.getMedicine());
            polyclinicRepository.save(medicalRecords.getPolyclinic());
            medicalRecordsRepository.save(medicalRecords);
        }
        return "redirect:/show-mr";
    }

    @Transactional
    private void saveOrUpdateDoctor(Doctor doctor) {
        if (doctor != null && doctor.getId() == null) {
            doctorRepository.save(doctor);
        }
    }

    @GetMapping("/update-mr/{id}")
    public String updateMedicalRecords(@PathVariable(value = "id") Integer id, Model model) {
        MedicalRecords medicalRecords = medicalRecordsRepository.findById(id).orElse(null);
        model.addAttribute("medicalRecords", medicalRecords);
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("medicines", medicineRepository.findAll());
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("polyclinics", polyclinicRepository.findAll());
        return "update-mr";
    }

    @Transactional
    @PostMapping("/update-mr/{id}")
    public String processUpdateMedicalRecords(@PathVariable Integer id,
                                              @ModelAttribute("medicalRecords") MedicalRecords updatedMedicalRecords) {
        saveOrUpdateDoctor(updatedMedicalRecords.getDoctor());
        medicalRecordsRepository.save(updatedMedicalRecords);
        return "redirect:/show-mr";
    }

    @GetMapping("/delete-mr/{id}")
    public String deleteMedicalRecords(@PathVariable(value = "id") Integer id) {
        medicalRecordsRepository.deleteById(id);
        return "redirect:/show-mr";
    }
}