package com.project.haiportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.haiportal.models.Schedule;
import com.project.haiportal.repositories.MatkulRepository;
import com.project.haiportal.repositories.RoomRepository;
import com.project.haiportal.repositories.ScheduleRepository;

@Controller
public class ScheduleController {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private MatkulRepository matkulRepository;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("list-schedule")
    public String listSchedule(Model model) {
        Schedule schedule = new Schedule();
        model.addAttribute("add-schedule", schedule);
        model.addAttribute("matkul", matkulRepository.findAll());
        model.addAttribute("room", roomRepository.findAll());
        model.addAttribute("schedule", scheduleRepository.findAll());
        return "schedule";
    }

    @PostMapping("save-schedule")
    public String saveSchedule(@ModelAttribute("schedule") Schedule schedule) {
        scheduleRepository.save(schedule);
        return "redirect:/list-schedule";
    }

    @GetMapping("update-schedule/{id}")
    public String updateSchedule(@PathVariable(value = "id") Integer id, Model model) {
        Schedule schedule = scheduleRepository.getReferenceById(id);
        model.addAttribute("schedule", schedule);
        return "update-schedule";
    }

    @GetMapping("delete-schedule/{id}")
    public String deleteSChedule(@PathVariable(value = "id") Integer id, Model model) {
        scheduleRepository.deleteById(id);
        return "redirect:/list-schedule";
    }

    @GetMapping("list-schedule2")
    public String listSchedule2(Model model) {
        Schedule schedule = new Schedule();
        model.addAttribute("add-schedule", schedule);
        model.addAttribute("matkul", matkulRepository.findAll());
        model.addAttribute("room", roomRepository.findAll());
        model.addAttribute("schedule", scheduleRepository.findAll());
        return "schedule-stud";
    }
    @GetMapping("list-schedule3")
    public String listSchedule3(Model model) {
        Schedule schedule = new Schedule();
        model.addAttribute("add-schedule", schedule);
        model.addAttribute("matkul", matkulRepository.findAll());
        model.addAttribute("room", roomRepository.findAll());
        model.addAttribute("schedule", scheduleRepository.findAll());
        return "schedule-lec";
    }
}
