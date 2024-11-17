package com.perpustakaan.susiharyati.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.perpustakaan.susiharyati.models.Message;
import com.perpustakaan.susiharyati.repositories.MemberRepository;
import com.perpustakaan.susiharyati.repositories.MessageRepository;

@Controller
public class MessageController {
    
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/message")
    public String allMessage(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "show-message";
    }

    @GetMapping("/message-admin")
    public String allMessageAdmin(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "show-message-admin";
    }

    @GetMapping("/add-message")
    public String addMessage(Model model){
        Message message = new Message();
        model.addAttribute("message", message);
        model.addAttribute("member", memberRepository.findAll());
        return "save-message";
    }

    @PostMapping("/message-save")
    public String saveMessage(Message message){
        messageRepository.save(message);
        return "redirect:/message";
    }

    @GetMapping("/message-update/{id}")
    public String updateMessage(@PathVariable(value = "id") Integer id, Model model){
        Message message = messageRepository.findById(id).orElse(null);
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("message", message);
        return "update-message";
    }

    @GetMapping("/message-delete/{id}")
    public String deleteMessage(@PathVariable(value = "id") Integer id, Model model){
        messageRepository.deleteById(id);
        return "redirect:/message";
    }

    @GetMapping("/message-admin-delete/{id}")
    public String deleteMessageAdmin(@PathVariable(value = "id") Integer id, Model model){
        messageRepository.deleteById(id);
        return "redirect:/message-admin";
    }
}
