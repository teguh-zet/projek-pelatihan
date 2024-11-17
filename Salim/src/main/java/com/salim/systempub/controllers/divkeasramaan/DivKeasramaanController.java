package com.salim.systempub.controllers.divkeasramaan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salim.systempub.models.divkeasramaan.MemberDivKeasramaan;
import com.salim.systempub.models.divkeasramaan.MemberDormitory;
import com.salim.systempub.repository.MemberRepository;
import com.salim.systempub.repository.divitionrepository.divkeasramaan.MemberDivKeasramaanRepository;
import com.salim.systempub.repository.divitionrepository.divkeasramaan.MemberDormitoryRepository;

@Controller
@RequestMapping("/divisi/keasramaan")
public class DivKeasramaanController {
    @Autowired
    private MemberDivKeasramaanRepository memberDivKeasramaanRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberDormitoryRepository memberDormitoryRepository;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("addMember", new MemberDivKeasramaan());
        model.addAttribute("memberDiv", memberDivKeasramaanRepository.findAll());
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("dweller", memberDormitoryRepository.findAll());
        model.addAttribute("addDweller", new MemberDormitory());
        return "divkeasramaan/index";
    }
    // @GetMapping("/list/")
    // public String list(Model model){
    //     model.addAttribute("memberDiv", memberDivKeasramaanRepository.findAll());
    //     return "divkeasramaan/list";
    // }
    // @GetMapping("/add/")
    // public String addMember(Model model){
    //     MemberDivKeasramaan member=;
    //     model.addAttribute("add", new MemberDivKeasramaan());
    //     model.addAttribute("member", memberRepository.findAll());
    //     return "divkeasramaan/add";
    // }
    @PostMapping("/save")
    public String saveMember(MemberDivKeasramaan member){
        memberDivKeasramaanRepository.save(member);
        return "redirect:/divisi/keasramaan/";
    }
    @GetMapping("/delete/{id}")
    public String deleteString(@PathVariable(value = "id")Long id,Model model){
        memberDivKeasramaanRepository.deleteById(id);
        return "redirect:/divisi/keasramaan/";
    }
    @GetMapping("/update/{id}")
    public String updateString(@PathVariable(value = "id")Long id,Model model){
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("update", memberDivKeasramaanRepository.getReferenceById(id));
        return "divkeasramaan/update";
    }

    // anggota asrama
    // @GetMapping("/list-penghuni/")
    // public String listPenghuni(Model model){
       
    //     return "divkeasramaan/list-penghuni";
    // }
    // @GetMapping("/add-penghuni/")
    // public String addPenghuni(Model model){
    //     MemberDormitory member=;
    //     model.addAttribute("member", memberRepository.findAll());
    //     return "divkeasramaan/add-penghuni";
    // }
    @PostMapping("/save-penghuni")
    public String savePenghuni(MemberDormitory member){
        memberDormitoryRepository.save(member);
        return "redirect:/divisi/keasramaan/";
    }
    @GetMapping("/delete-penghuni/{id}")
    public String deletePenghuni(@PathVariable(value = "id")Long id,Model model){
        memberDormitoryRepository.deleteById(id);
        return "redirect:/divisi/keasramaan/";
    }
    @GetMapping("/update-penghuni/{id}")
    public String updatePenghuni(@PathVariable(value = "id")Long id,Model model){
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("update", memberDormitoryRepository.getReferenceById(id));
        return "divkeasramaan/update-penghuni";
    }
}
