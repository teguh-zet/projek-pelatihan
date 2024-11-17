package com.salim.systempub.controllers.divkerohanian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salim.systempub.models.divkerohanian.DataPUB;
import com.salim.systempub.models.divkerohanian.MemberDivKerohanian;
import com.salim.systempub.repository.MemberRepository;
import com.salim.systempub.repository.divitionrepository.divkerohanian.DataPUBRepository;
import com.salim.systempub.repository.divitionrepository.divkerohanian.MemberDivkerohanianRepository;

@Controller
@RequestMapping("/divisi/kerohanian")
public class DivKerohanianController {
    @Autowired
    private MemberDivkerohanianRepository memberDivkerohanianRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DataPUBRepository dataPUBRepository;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("memberDiv", memberDivkerohanianRepository.findAll());
        model.addAttribute("addMember", new MemberDivKerohanian());
        model.addAttribute("addReligion", new DataPUB());
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("data", dataPUBRepository.findAll());
        return "divkerohanian/index";
    }
    // @GetMapping("/list/")
    // public String list(Model model){
    //     return "divkerohanian/list";
    // }
    // @GetMapping("/add/")
    // public String addMember(Model model){
    //     MemberDivKerohanian member=new MemberDivKerohanian();
    //     model.addAttribute("add", member);
    //     model.addAttribute("member", memberRepository.findAll());
    //     return "divkerohanian/add";
    // }
    @PostMapping("/save")
    public String saveMember(MemberDivKerohanian member){
        memberDivkerohanianRepository.save(member);
        return "redirect:/divisi/kerohanian/";
    }
    @GetMapping("/delete/{id}")
    public String deleteString(@PathVariable(value = "id")Long id,Model model){
        memberDivkerohanianRepository.deleteById(id);
        return "redirect:/divisi/kerohanian/";
    }
    @GetMapping("/update/{id}")
    public String update(Long id,Model model){
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("update", memberDivkerohanianRepository.getReferenceById(id));
        return "divkerohanian/update";
    }

    // Sistem Poin dan Hafalan Di PUB
    // @GetMapping("/list-data/")
    // public String listData(Model model){
    //     model.addAttribute("data", dataPUBRepository.findAll());
    //     return "divkerohanian/list-data";
    // }
    // @GetMapping("/add-data/")
    // public String addData(Model model){
    //     DataPUB member=new DataPUB();
    //     model.addAttribute("add", member);
    //     model.addAttribute("member", memberRepository.findAll());
    //     return "divkerohanian/add-data";
    // }
    @PostMapping("/save-data")
    public String saveData(DataPUB member){
        dataPUBRepository.save(member);
        return "redirect:/divisi/kerohanian/";
    }
    @GetMapping("/update-data/{id}")
    public String updateData(Long id,Model model){
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("update", dataPUBRepository.getReferenceById(id));
        return "divkerohanian/update-data";
    }
    @GetMapping("/delete-data/{id}")
    public String deleteData(@PathVariable(value = "id")Long id,Model model){
        dataPUBRepository.deleteById(id);
        return "redirect:/divisi/kerohanian/";
    }
}
