package com.penjualan.pasar.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.penjualan.pasar.entity.AkunAdmin;
import com.penjualan.pasar.entity.Akun;
import com.penjualan.pasar.entity.Tampungan;
import com.penjualan.pasar.repositories.AkunAdminRepository;
import com.penjualan.pasar.repositories.AkunRepository;
import com.penjualan.pasar.repositories.TampunganRepository;

@Controller
public class AkunController {
    @Autowired
    private AkunRepository akunRepository;

    @Autowired 
    private TampunganRepository tampunganRepository;

    @Autowired
    private AkunAdminRepository adminRepository;

    @GetMapping("/")
    public String home(){
        if(tampunganRepository.findAll().isEmpty() || adminRepository.findAll().get(0).getKeterangan().equals("belum login")){
            return "index";
        }
        else{
            return "redirect:/barang";
        }
    }

    @GetMapping("/login-usehhhr")
    public String login(Model model){
        if(tampunganRepository.findAll().isEmpty()){
            return "login";
        }
        else{
            return "redirect:/barang";
        }
    }
     
    @PostMapping("/cek-login")
    public String cekLogin(@RequestParam(name = "username") String username,@RequestParam(name = "password") String pass,Model model){
        ArrayList<Akun> akuns = new ArrayList<>();
        akuns.addAll(akunRepository.findAll());
        int idx = -1;
        for(int i = 0; i < akuns.size(); i++){
            if(username.equals(akuns.get(i).getGmail()) && pass.equals(akuns.get(i).getPassword())){
                idx = i;
            }
        }
        if(idx == -1){
            return "login-gagal";
        }
        else{
            Akun akun = akunRepository.findByGmail(username);
            Tampungan tmp = new Tampungan();
            tmp.setGmail(akun.getGmail());
            tmp.setId(akun.getId());
            tmp.setName(akun.getName());
            tmp.setPassword(akun.getPassword());
            tmp.setTtl(akun.getTtl());
            tmp.setSaldo(akun.getSaldo());
            tampunganRepository.save(tmp);
            return "redirect:/barang";
        }
    }

    @GetMapping("/daftar")
    public String daftar(Model model){
        if(tampunganRepository.findAll().isEmpty()){
            Akun akun = new Akun();
            model.addAttribute("data", akun);
            return "daftar-akun";
        }
        else{
            return "redirect:/barang";
        }
    }

    @PostMapping("/cek-akun")
    public String cekAkun(Akun akun,@RequestParam(name = "gmail") String gmail){
        if(tampunganRepository.findAll().isEmpty()){
            if(akunRepository.findGmailByGmail(gmail).isEmpty()){
                akun.setGmail(gmail);
                akun.setSaldo(0);
                akunRepository.save(akun); 
                return "daftar-berhasil";
            }
            else{
                return "daftar-gagal";
            }
        }
        else{
            return "redirect:/barang";
        }
    }

    @GetMapping("/keluar-user")
    public String keluarUser(){
        if(tampunganRepository.findAll().isEmpty()){
            return "notif-login";
        }
        else{
            tampunganRepository.deleteAll();
            return "redirect:/";
        }
    }

    @GetMapping("/keluar-admin")
    public String keluar(Model model){
        AkunAdmin admin = adminRepository.findAll().get(0);
        admin.setKeterangan("belum login");
        adminRepository.save(admin);
        return "redirect:/";
    }

    @GetMapping("/gmail-hilang")
    public String searchGmail(@RequestParam(name = "gmail") String gmail){
        Akun akun = akunRepository.findByGmail(gmail);
        Boolean cek = akun.getGmail().isEmpty();
        if(cek == true){
            return "redirect:/";
        }
        else{
            return "redirect:/barang";
        }
    }
    
    @GetMapping("/ttl-hilang")
    public String searchTtl(@RequestParam(name = "gmail") String gmail){
        Akun akun = akunRepository.findByGmail(gmail);
        if(akun.getGmail().isEmpty()){
            return "redirect:/";
        }
        else{
            return "redirect:/barang";
        }
    }

    @GetMapping("login-admin")
    public String loginAdmin(){
        if(adminRepository.findAll().get(0).getKeterangan().equals("belum login")){
            return "login-admin";
        }
        else{
            return "redirect:/show-barang";
        }
    }

    @PostMapping("periksa-admin")
    public String periksaAdmin(@RequestParam(name = "pin") String pin){
        if(adminRepository.findAll().get(0).getKeterangan().equals("belum login")){
            AkunAdmin admin = adminRepository.findAdminByPin(pin);
            if (admin == null) {
                return "login-gagal";
            }
            else{
                if(admin.equals(adminRepository.findAdminByPin(pin))){
                    admin.setKeterangan("sudah login");
                    adminRepository.save(admin);
                    return "redirect:/show-barang";
                }
                else{
                    return "login-gagal";
                }
            }
        }
        else{
            return "redirect:/show-barang";
        }        
    }
}