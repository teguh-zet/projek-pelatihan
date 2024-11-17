package com.salim.systempub.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salim.systempub.config.AuthConfig;
import com.salim.systempub.models.Auth;
import com.salim.systempub.models.Generation;
import com.salim.systempub.models.Member;
import com.salim.systempub.repository.AuthRepository;
import com.salim.systempub.repository.MemberRepository;
import com.salim.systempub.repository.divitionrepository.divkeasramaan.MemberDivKeasramaanRepository;
import com.salim.systempub.repository.divitionrepository.divkeasramaan.MemberDormitoryRepository;
import com.salim.systempub.repository.divitionrepository.divkebersihan.MemberDivKebersihanRepository;
import com.salim.systempub.repository.divitionrepository.divkebersihan.PiketRepository;
import com.salim.systempub.repository.divitionrepository.divkerohanian.DataPUBRepository;
import com.salim.systempub.repository.divitionrepository.divkerohanian.MemberDivkerohanianRepository;
import com.salim.systempub.repository.divitionrepository.divkesehatan.MemberDivKesehatanRepository;
import com.salim.systempub.repository.divitionrepository.divkesejahteraan.MemberDivKesejahteraanRepository;
import com.salim.systempub.repository.divitionrepository.divpelatihaninggris.MemberDivPelatihanInggrisRepository;
import com.salim.systempub.repository.divitionrepository.divpelatihaninggris.VocabRepository;
import com.salim.systempub.repository.divitionrepository.divpendidikan.MemberDivPendidikanRepository;
import com.salim.systempub.repository.ppmb.PPMBRepository;
import com.salim.systempub.services.AdminService;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/admin")
public class AdminController extends AuthConfig {

    @Autowired
    private PPMBRepository ppmbRepository;
    @Autowired
    private MemberDivKeasramaanRepository memberDivKeasramaanRepository;
    @Autowired
    private MemberDivKebersihanRepository memberDivKebersihanRepository;
    @Autowired
    private MemberDivkerohanianRepository memberDivkerohanianRepository;
    @Autowired
    private MemberDivKesehatanRepository memberDivKesehatanRepository;
    @Autowired
    private MemberDivKesejahteraanRepository memberDivKesejahteraanRepository;
    @Autowired
    private MemberDivPelatihanInggrisRepository memberDivPelatihanInggrisRepository;
    @Autowired
    private MemberDivPendidikanRepository memberDivPendidikanRepository;
    @Autowired
    private PiketRepository piketRepository;
    @Autowired
    private MemberDormitoryRepository memberDormitory;
    @Autowired
    private DataPUBRepository dataPUBRepository;
    @Autowired
    private VocabRepository vocabRepository;
     @Autowired
     private AdminService adminService;
     private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

     @GetMapping("/")
     public String home(Model model) {
          model.addAttribute("genlist",genRepository.findAll());
          model.addAttribute("pubs",memberRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
          model.addAttribute("countPUB",memberRepository.count());
          model.addAttribute("totalAccount",authRepository.count());
          model.addAttribute("accounts",authRepository.findAll());
          if(!AuthConfig.messageAdmin.equals("")){
               model.addAttribute("name",AuthConfig.contact);
               model.addAttribute("number",AuthConfig.number);
               model.addAttribute("message",AuthConfig.messageAdmin);
          }
          return "admin/index";
     }
     @GetMapping("/add/")
     public String addAuth(Model model) {
          return authService.add(model);
     }
     @PostMapping("/save")
     public String saveAuth(Auth user,Model model) {
          return authService.save(user,model);
     }
     @GetMapping("/update/{id}")
     public String updateAuth(@PathVariable("id")Long id,Model model){
          Auth auth=authRepository.getReferenceById(id);
          auth.setPassword("");
          model.addAttribute("update",auth);
          return "admin/update-auth";
     }
     @PostMapping("/save-update")
     public String saveUpdateAuth(@ModelAttribute("add")Auth auth){
          String oldPass=auth.getPassword();
          String oldPassHashed=authRepository.getReferenceById(auth.getId()).getPassword();
          if(passwordEncoder.matches(oldPass,oldPassHashed)){
               // System.out.println(newpass);
               auth.setPassword(passwordEncoder.encode(oldPass));
               authRepository.save(auth);
               return "redirect:/admin/";
          }
          System.out.println("Password Not Matches");
          return "redirect:/admin/";
     }
     @GetMapping("/delete/{id}")
     public String deleteAuth(@PathVariable("id")Long id){
          authRepository.deleteById(id);
          return "redirect:/admin/";
     }

     //     Member PUB

     @GetMapping("/add-member/")
     public String add(Model model) {
          return adminService.addMember(model);
     }

     @PostMapping("/save-member")
     public String save(Member member, Model model) {
          return adminService.saveMember(member, model);
     }

     @GetMapping("/delete-member/{id}")
     public String delete(@PathVariable(value = "id") Long id, Model model) {
          return adminService.deleteMember(id, model);
     }

     @GetMapping("/update-member/{id}")
     public String update(@PathVariable(name = "id") Long id, Model model) {
          return adminService.updateMember(id, model);
     }

     @PostMapping("/saveupdate-member")
     public String saveUpdaString(Member member) {
          return adminService.saveUpdateMember(member);
     }

     //     Angkatan PUB

     @GetMapping("/add-gen/")
     public String addGen(Model model) {
          return adminService.addGen(model);
     }

     @PostMapping("/save-gen")
     public String saveGen(Generation gen, Model model) {
          return adminService.saveGen(gen, model);
     }

     @GetMapping("/delete-gen/{id}")
     public String deleteGen(@PathVariable(value = "id") Long id, Model model) {
          return adminService.deleteGen(id, model);
     }

     @GetMapping("/update-gen/{id}")
     public String updateGen(@PathVariable(name = "id") Long id, Model model) {
          return adminService.updateGen(id, model);
     }

     @PostMapping("/saveupdate-gen")
     public String saveUpdaString(Generation gen) {
          return adminService.saveUpdateGen(gen);
     }
}