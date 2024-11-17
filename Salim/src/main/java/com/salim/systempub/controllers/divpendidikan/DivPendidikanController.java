package com.salim.systempub.controllers.divpendidikan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salim.systempub.models.divpendidikan.Kelompok;
import com.salim.systempub.models.divpendidikan.MemberDivPendidikan;
import com.salim.systempub.models.divpendidikan.MemberGroup;
import com.salim.systempub.repository.MemberRepository;
import com.salim.systempub.repository.divitionrepository.divpendidikan.KelompokRepository;
import com.salim.systempub.repository.divitionrepository.divpendidikan.MemberDivPendidikanRepository;
import com.salim.systempub.repository.divitionrepository.divpendidikan.MemberGroupRepository;

@Controller
@RequestMapping("/divisi/pendidikan")
public class DivPendidikanController {

    @Autowired
    private KelompokRepository kelompokRepository;
    @Autowired
    private MemberGroupRepository memberGroupRepository;
    @Autowired
    private MemberDivPendidikanRepository memberDivPendidikanRepository;
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("memberDiv", memberDivPendidikanRepository.findAll());
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("group", kelompokRepository.findAll());
        model.addAttribute("membergroup", memberGroupRepository.findAll());
        model.addAttribute("addgroup", new Kelompok());
        model.addAttribute("addmemberg", new MemberGroup());
        model.addAttribute("addMember", new MemberDivPendidikan());
        return "divpendidikan/index";
    }
    @PostMapping("/savem")
    public String saveM(MemberDivPendidikan memberDivPendidikan){
        memberDivPendidikanRepository.save(memberDivPendidikan);
        return "redirect:/divisi/pendidikan/";
    }
    @PostMapping("/saveg")
    public String saveG(Kelompok kelompok){
        kelompokRepository.save(kelompok);
        return "redirect:/divisi/pendidikan/";
    }
    @PostMapping("/savemg")
    public String saveM(MemberGroup memberGroup){
        memberGroupRepository.save(memberGroup);
        return "redirect:/divisi/pendidikan/";
    }
    @GetMapping("/delete-g/{id}")
    public String deleteGroup(@PathVariable(value = "id")Long id,Model model){
        kelompokRepository.deleteById(id);
        return "redirect:/divisi/pendidikan/";
    }
    @GetMapping("/delete-m/{id}")
    public String deleteMember(@PathVariable(value = "id")Long id,Model model){
        memberDivPendidikanRepository.deleteById(id);
        return "redirect:/divisi/pendidikan/";
    }
    @GetMapping("/delete-mg/{id}")
    public String deleteMemberGroup(@PathVariable(value = "id")Long id,Model model){
        memberGroupRepository.deleteById(id);
        return "redirect:/divisi/pendidikan/";
    }
    @GetMapping("/update-g/{id}")
    public String updateGroup(@PathVariable(value = "id")Long id,Model model){
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("update", kelompokRepository.getReferenceById(id));
        return "divpendidikan/update-group";
    }
    @GetMapping("/update-m/{id}")
    public String updateMember(@PathVariable(value = "id")Long id,Model model){
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("update", memberDivPendidikanRepository.getReferenceById(id));
        return "divpendidikan/update-member";
    }
    @GetMapping("/update-mg/{id}")
    public String updateMemberGroup(@PathVariable(value = "id")Long id,Model model){
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("group", kelompokRepository.findAll());
        model.addAttribute("update", memberGroupRepository.getReferenceById(id));
        return "divpendidikan/update-membergroup";
    }
}