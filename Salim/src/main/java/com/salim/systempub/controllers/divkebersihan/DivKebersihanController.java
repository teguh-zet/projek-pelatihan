package com.salim.systempub.controllers.divkebersihan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salim.systempub.models.divkebersihan.MemberDivKebersihan;
import com.salim.systempub.models.divkebersihan.Piket;
import com.salim.systempub.models.pasim.Room;
import com.salim.systempub.repository.MemberRepository;
import com.salim.systempub.repository.RoomRepository;
import com.salim.systempub.repository.divitionrepository.divkebersihan.MemberDivKebersihanRepository;
import com.salim.systempub.repository.divitionrepository.divkebersihan.PiketRepository;

@Controller
@RequestMapping("/divisi/kebersihan")
public class DivKebersihanController {
    @Autowired
    private MemberDivKebersihanRepository memberDivKebersihanRepository;
    @Autowired
    private PiketRepository piketRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/")
    public String home(Model model){
        if(roomRepository.findAll().isEmpty()){
            roomRepository.save(new Room(1L,"2A",null));
            roomRepository.save(new Room(2L,"2B",null));
            roomRepository.save(new Room(3L,"2C",null));
            roomRepository.save(new Room(4L,"2E",null));
            roomRepository.save(new Room(5L,"2D",null));
            roomRepository.save(new Room(6L,"B.2.1",null));
            roomRepository.save(new Room(7L,"B.2.2",null));
            roomRepository.save(new Room(8L,"B.2.3",null));
            roomRepository.save(new Room(9L,"B.2.4",null));
            roomRepository.save(new Room(10L,"B.2.5",null));
            roomRepository.save(new Room(11L,"B.2.6",null));
            roomRepository.save(new Room(12L,"3B",null));
            roomRepository.save(new Room(13L,"B.3.1",null));
            roomRepository.save(new Room(14L,"B.3.2",null));
            roomRepository.save(new Room(15L,"B.3.3",null));
            roomRepository.save(new Room(16L,"Lorong Lt. 2",null));
            roomRepository.save(new Room(17L,"Lorong Lt. 3",null));
            roomRepository.save(new Room(18L,"Sampah Lt. 2",null));
            roomRepository.save(new Room(19L,"Sampah Lt. 3",null));
            roomRepository.save(new Room(20L,"Parkiran Atas",null));
            roomRepository.save(new Room(21L,"Parkiran Bawah",null));
            roomRepository.save(new Room(22L,"Kantin",null));
            roomRepository.save(new Room(23L,"Depan Perpus",null));
            roomRepository.save(new Room(24L,"Halaman Depan",null));
        }
        model.addAttribute("addMember", new MemberDivKebersihan());
        model.addAttribute("memberDiv", memberDivKebersihanRepository.findAll());
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("piket", piketRepository.findAll());
        model.addAttribute("addPiket", new Piket());
        model.addAttribute("room", roomRepository.findAll());
        return "divkebersihan/index";
    }
    @PostMapping("/save")
    public String saveMember(MemberDivKebersihan member){
        memberDivKebersihanRepository.save(member);
        return "redirect:/divisi/kebersihan/";
    }
    @GetMapping("/delete/{id}")
    public String deleteString(@PathVariable(value = "id")Long id,Model model){
        memberDivKebersihanRepository.deleteById(id);
        return "redirect:/divisi/kebersihan/";
    }
    @GetMapping("/update/{id}")
    public String updateString(@PathVariable(value = "id")Long id,Model model){
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("update", memberDivKebersihanRepository.getReferenceById(id));
        return "divkebersihan/update";
    }
    @PostMapping("/save-piket")
    public String savePiket(Piket piket){
        piketRepository.save(piket);
        return "redirect:/divisi/kebersihan/";
    }
    @GetMapping("/delete-piket/{id}")
    public String deletePiket(@PathVariable(value = "id")Long id,Model model){
        piketRepository.deleteById(id);
        return "redirect:/divisi/kebersihan/";
    }
    @GetMapping("/update-piket/{id}")
    public String updatePiket(@PathVariable(value = "id")Long id,Model model){
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("update", memberDivKebersihanRepository.getReferenceById(id));
        return "divkebersihan/update-piket";
    }
}
