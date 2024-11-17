package com.project.haiportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.haiportal.models.Room;
import com.project.haiportal.repositories.RoomRepository;

@Controller
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    
    @GetMapping("list-room")
    public String listRoom(Model model){
        Room room = new Room();
        model.addAttribute("add-rooms", room);
        model.addAttribute("rooms", roomRepository.findAll());
        return "show-room";
    }

   
    
    @PostMapping("save-room")
    public String saveRoom(@ModelAttribute("add-rooms") Room room){
        roomRepository.save(room);
        return "redirect:/list-room";
    }

    @GetMapping("update-room/{id}")
    public String updateRoom(@PathVariable (value = "id") Integer id, Model model){
        Room room = roomRepository.getReferenceById(id);
        model.addAttribute("room", room);
        return "update-room";
    }

    @GetMapping("delete-room/{id}")
    public String deleteRoom(@PathVariable(value = "id") Integer id, Model model){
        roomRepository.deleteById(id);

        return "redirect:/list-room";
    }
}
