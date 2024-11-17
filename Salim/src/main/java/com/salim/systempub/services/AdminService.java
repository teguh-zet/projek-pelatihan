package com.salim.systempub.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.salim.systempub.config.ServiceConfig;
import com.salim.systempub.models.Generation;
import com.salim.systempub.models.Member;

@Service
public class AdminService extends ServiceConfig {

    public String addMember(Model model) {
        model.addAttribute("add", new Member());
        model.addAttribute("generation", generationRepository.findAll());
        return "admin/add-member";
    }

    public String saveMember(Member m, Model model) {
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

    public String deleteMember(Long id, Model model) {
        List<Member> members = memberRepository.findAll();
        boolean isThere = false;
        for (Member m : members) {
            if (id.equals(m.getId())) {
                isThere = true;
                break;
            }
        }
        if (isThere) {
            memberRepository.deleteById(id);
            return "redirect:/admin/list-member/";
        } else {
            model.addAttribute("errormessage", "Data Tidak Ada, Masukan Data Lain");
            return "error";
        }
    }

    public String updateMember(Long id, Model model) {
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
            model.addAttribute("gen", generationRepository.findAll());
            model.addAttribute("update", member);
            return "admin/update-member";
        } else {
            model.addAttribute("errormessage", "Data Tidak Ada, Masukan Data Lain");
            System.out.println("Error Mas");
            return "error";
        }
    }

    public String saveUpdateMember(Member member) {
        memberRepository.save(member);
        return "redirect:/admin/";
    }

//  Angkatan PUB

    public String addGen(Model model) {
        Generation gen = new Generation();
        model.addAttribute("add", gen);
        return "admin/add-gen";
    }

    public String saveGen(Generation g, Model model) {
        generationRepository.save(g);
        return "redirect:/admin/";
    }

    public String deleteGen(Long id, Model model) {
        List<Generation> gens = generationRepository.findAll();
        boolean isThere = false;
        for (Generation g : gens) {
            if (id.equals(g.getId())) {
                isThere = true;
                break;
            }
        }
        if (isThere) {
            generationRepository.deleteById(id);
            return "redirect:/admin/";
        } else {
            model.addAttribute("errormessage", "Data Tidak Ada, Masukan Data Lain");
            return "error";
        }
    }

    public String updateGen(Long id, Model model) {
        List<Generation> gens = generationRepository.findAll();
        Generation gen = generationRepository.getReferenceById(id);
        boolean isThere = false;
        for (Generation g:gens) {
            if (g.getId().equals(id)) {
                isThere = true;
                break;
            }
        }
        if (isThere) {
            model.addAttribute("update", gen);
            return "admin/update-gen";
        } else {
            model.addAttribute("errormessage", "Data Tidak Ada, Masukan Data Lain");
            return "error";
        }
    }

    public String saveUpdateGen(Generation gen) {
        generationRepository.save(gen);
        return "redirect:/admin/";
    }
}
