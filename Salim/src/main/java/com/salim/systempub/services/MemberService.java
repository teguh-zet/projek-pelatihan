package com.salim.systempub.services;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.salim.systempub.config.ServiceConfig;
import com.salim.systempub.models.Member;

@Service
public class MemberService extends ServiceConfig {
    
    public String add(Model model) {
        Member member = new Member();
        model.addAttribute("add", member);
        return "/admin/add";
    }

    public String save(Member m, Model model) {
        boolean isThere = false;
        if (!memberRepository.findAll().isEmpty()){
            List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            if (member.getNim().equals(m.getNim())) {
                isThere = true;
                break;
            }
        }}
        if (isThere) {
            model.addAttribute("errormessage", "Data Telah Ada, Masukan Data Lain");
            return "error";
        } else {
            memberRepository.save(m);
            return "redirect:/admin/";
        }
    }

    public String delete(Long id, Model model) {
        List<Member> members = memberRepository.findAll();
        boolean isThere = false;
        for (Member m : members) {
            if (id == m.getId()) {
                isThere = true;
                break;
            }
        }
        if (isThere) {
            memberRepository.deleteById(id);
            return "redirect:/admin/";
        } else {
            model.addAttribute("errormessage", "Data Tidak Ada, Masukan Data Lain");
            return "redirect:/error";
        }
    }

    public String update(Long id, Model model) {
        List<Member> members = memberRepository.findAll();
        Member member = memberRepository.getReferenceById(id);
        boolean isThere = false;
        for (Member m:members) {
            if (m.getId().equals(id)) {
                isThere = true;
                break;
            }
        }
        if (isThere) {
            model.addAttribute("update", member);
            return "/admin/";
        } else {
            model.addAttribute("errormessage", "Data Tidak Ada, Masukan Data Lain");
            System.out.println("Error Mas");
            return "redirect:/error";
        }
    }

    public String saveUpdate(Member member) {
        try {
            memberRepository.save(member);
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return "redirect:/admin/";
    }
    public String find(String nama, Model model) {
        model.addAttribute("all", memberRepository.findByNameContainingIgnoreCase(nama,Sort.by(Sort.Direction.ASC, "name")));
        return "find";
    }
}
