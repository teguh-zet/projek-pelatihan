package com.perpustakaan.susiharyati.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.perpustakaan.susiharyati.models.Admin;
import com.perpustakaan.susiharyati.models.Member;
import com.perpustakaan.susiharyati.repositories.AdminRepository;
import com.perpustakaan.susiharyati.repositories.MemberRepository;

@Controller
public class MemberController {
    
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("member")
    public String loginMember(Model model){
        model.addAttribute("members", memberRepository.findAll());
        return "show-member";
    }

    @GetMapping("add-member")
    public String addMember(Model model){
        Member member = new Member();
        model.addAttribute("member", member);
        return "save-member";
    }

    @PostMapping("member-save")
    public String saveMember(@ModelAttribute("member") Member member){
        memberRepository.save(member);
        return "redirect:/login";
    }

    @GetMapping("member-update/{id}")
    public String updateMember(@PathVariable(value = "id") Integer id, Model model){
        // Member member = memberRepository.getReferenceById(id);
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", member);
        return "update-member";
    }

    @GetMapping("member-delete/{id}")
    public String deleteMember(@PathVariable(value = "id") Integer id){
        memberRepository.deleteById(id);
        return "redirect:/member";
    }

    @GetMapping("/login")
    public String loginAdm(Model model){
        Member member = new Member();
        model.addAttribute("members", member);
        return "login";
    }
   
    @PostMapping("/cek-login")
    public String ceklogin(@ModelAttribute("members") Member member){
        Admin admin = adminRepository.getReferenceById(1);
        List<Member> members= memberRepository.findAll();
        if(admin.getUsername().equals(member.getUsername()) && member.getPassword().equals(admin.getPassword())){

            return "redirect:/home-admin";
        }

        for (Member x : members) {
            if(x.getUsername().equals(member.getUsername()) && x.getPassword().equals(member.getPassword())){
                return "redirect:/member-home?" + "name=" + member.getUsername() + "&password=" + member.getPassword();
            }
        }
        return "redirect:/login";

    }
    


    
}
