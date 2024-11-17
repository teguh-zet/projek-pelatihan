package com.salim.systempub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salim.systempub.config.AuthConfig;
import com.salim.systempub.models.Auth;
import com.salim.systempub.models.Member;
import com.salim.systempub.models.PPMB;
import com.salim.systempub.models.divkeasramaan.MemberDivKeasramaan;
import com.salim.systempub.models.divkeasramaan.MemberDormitory;
import com.salim.systempub.models.divkebersihan.MemberDivKebersihan;
import com.salim.systempub.models.divkebersihan.Piket;
import com.salim.systempub.models.divkerohanian.DataPUB;
import com.salim.systempub.models.divkerohanian.MemberDivKerohanian;
import com.salim.systempub.models.divkesehatan.MemberDivKesehatan;
import com.salim.systempub.models.divkesejahteraan.MemberDivKesejahteraan;
import com.salim.systempub.models.divpelatihaninggris.MemberDivPelatihanInggris;
import com.salim.systempub.models.divpelatihaninggris.Vocab;
import com.salim.systempub.models.divpendidikan.MemberDivPendidikan;
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

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AuthRepository authRepository;
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

    @GetMapping("/")
    public String home(Model model) {
        Member member=new Member();
        for (Auth auth : authRepository.findAll()) {
            if(auth.getUsername().equals(AuthConfig.username)){
                AuthConfig.buffId=auth.getIdMember().getId();
                member=memberRepository.getReferenceById(AuthConfig.buffId);
                break;
            }
        }
        for (PPMB ppmb : ppmbRepository.findAll()) {
            if(ppmb.getIdMember().getId().equals(AuthConfig.buffId)){
                model.addAttribute("datappmb",ppmb.getTpa_area());
                break;
            }
        }
        for (DataPUB dataPUB : dataPUBRepository.findAll()) {
            if(dataPUB.getIduser().getId()==AuthConfig.buffId){
                model.addAttribute("poin",dataPUB.getPointleft());
                model.addAttribute("hafalan",dataPUB.getHafalan());
                break;
            }
        }
        for (Piket piket : piketRepository.findAll()) {
            if(piket.getIdmember().getId().equals(AuthConfig.buffId)){
                model.addAttribute("piketday",piket.getDay());
                model.addAttribute("piketroom",piket.getIdroom().getRoom());
                break;
            }
        }
        for (Vocab vocab : vocabRepository.findAll()) {
            if(vocab.getId_member().getId().equals(AuthConfig.buffId)){
                model.addAttribute("vocab",vocab.getVocab());
                break;
            }
        }
        for (MemberDormitory memberDormitory : memberDormitory.findAll()) {
            if(memberDormitory.getIdmember().getId().equals(AuthConfig.buffId)){
                model.addAttribute("dataasrama",memberDormitory.getDorm());
                break;
            }
        }
        for (MemberDivKeasramaan memberDivKeasramaan : memberDivKeasramaanRepository.findAll()) {
            if(memberDivKeasramaan.getIdMember().getId().equals(AuthConfig.buffId)){
                model.addAttribute("datadivkeasramaan",memberDivKeasramaan.getRole());
                break;
            }
        }
        for (MemberDivKebersihan memberDivKebersihan : memberDivKebersihanRepository.findAll()) {
            if(memberDivKebersihan.getIdMember().getId().equals(AuthConfig.buffId)){
                model.addAttribute("datadivkebersihan",memberDivKebersihan.getRole());
                break;
            }
        }
        for (MemberDivKerohanian memberDivKerohanian : memberDivkerohanianRepository.findAll()) {
            if(memberDivKerohanian.getIdMember().getId().equals(AuthConfig.buffId)){
                model.addAttribute("datadivkerohanian",memberDivKerohanian.getRole());
                break;
            }
        }
        for (MemberDivKesehatan memberDivKesehatan : memberDivKesehatanRepository.findAll()) {
            if(memberDivKesehatan.getIdMember().getId().equals(AuthConfig.buffId)){
                model.addAttribute("datadivkesehatan",memberDivKesehatan.getRole());
                break;
            }
        }
        for (MemberDivPelatihanInggris memberDivPelatihanInggris : memberDivPelatihanInggrisRepository.findAll()) {
            if(memberDivPelatihanInggris.getId_member().getId().equals(AuthConfig.buffId)){
                model.addAttribute("datadivpelatihaninggris",memberDivPelatihanInggris.getRole());
                break;
            }
        }
        for (MemberDivPendidikan memberDivPendidikan : memberDivPendidikanRepository.findAll()) {
            if(memberDivPendidikan.getIdMember().getId().equals(AuthConfig.buffId)){
                model.addAttribute("datadivpendidikan",memberDivPendidikan.getRole());
                break;
            }
        }
        for (MemberDivKesejahteraan memberDivKesejahteraan : memberDivKesejahteraanRepository.findAll()) {
            if(memberDivKesejahteraan.getIdMember().getId().equals(AuthConfig.buffId)){
                model.addAttribute("datadivkesejahteraan",memberDivKesejahteraan.getRole());
                break;
            }
        }
        if(member.equals(null))return "redirect:/";
        model.addAttribute("member",member);
        return "user/index";
    }
}
