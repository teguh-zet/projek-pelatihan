package com.salim.systempub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salim.systempub.models.PPMB;
import com.salim.systempub.repository.MemberRepository;
import com.salim.systempub.repository.ppmb.PPMBRepository;

@Controller
@RequestMapping("/ppmb")
public class PPMBController {

    @Autowired
    private PPMBRepository ppmbRepository;
    @Autowired
    private MemberRepository memberRepository;
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("data", ppmbRepository.findAll());
        model.addAttribute("member", memberRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        model.addAttribute("add", new PPMB());
        return "ppmb/index";
    }
    @PostMapping("/post")
    public String save(PPMB ppmb){
        ppmbRepository.save(ppmb);
        return "redirect:/ppmb/";
    }
    @GetMapping("/delete/{id}")
    public String deleteString(@PathVariable(value = "id")Long id,Model model){
        ppmbRepository.deleteById(id);
        return "redirect:/ppmb/";
    }
}