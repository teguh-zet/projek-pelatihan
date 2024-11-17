package com.penjualan.pasar.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.penjualan.pasar.entity.Akun;
import com.penjualan.pasar.entity.Kategori;
import com.penjualan.pasar.entity.Menu;
import com.penjualan.pasar.repositories.AkunAdminRepository;
import com.penjualan.pasar.repositories.AkunRepository;
import com.penjualan.pasar.repositories.KategoriRepository;
import com.penjualan.pasar.repositories.MenuRepository;
import com.penjualan.pasar.repositories.PenjualanRepository;

@Controller
public class AdminController {
    @Autowired
    private PenjualanRepository penjualanRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private KategoriRepository kategoriRepository;

    @Autowired
    AkunAdminRepository akunAdminRepository;

    @Autowired
    AkunRepository akunRepository;

    @GetMapping("/show-barang")
    public String showBarang(Model model){
        if(akunAdminRepository.findAll().get(0).getKeterangan().equals("sudah login")){
            model.addAttribute("data", menuRepository.findAll());
            return "show-barang";
        }
        else{
            return "notif-login";
        }
    }
    @GetMapping("/add-barang")
    public String addBarang(Model model){
        if(akunAdminRepository.findAll().get(0).getKeterangan().equals("sudah login")){
            Menu menu = new Menu();
            model.addAttribute("data", menu);
            model.addAttribute("kategori", kategoriRepository.findAll());
            return "add-barang";
        }
        else{
            return "notif-login";
        }
    }

    @GetMapping("/penjualan")
    public String penjualan(Model model){
        if(akunAdminRepository.findAll().get(0).getKeterangan().equals("sudah login")){
            model.addAttribute("data", penjualanRepository.findAll());
            return "show-penjualan";
        }
        else{
            return "notif-login";
        }
    }

    @GetMapping("/update-barang/{id}")
    public String updateBarang(Model model,@PathVariable Integer id){
        if(akunAdminRepository.findAll().get(0).getKeterangan().equals("sudah login")){
            Menu menu = menuRepository.getReferenceById(id);
            model.addAttribute("data", menu);
            model.addAttribute("kategori",kategoriRepository.findAll());
            return "update-barang";
        }
        else{
            return "notif-login";
        }
        
    }

    @PostMapping("/save-barang")
    public String saveBarang(Menu menu,@RequestPart("file") MultipartFile file){
        if(akunAdminRepository.findAll().get(0).getKeterangan().equals("sudah login")){
            if(menuRepository.findAllByName(menu.getName()).isEmpty() || menuRepository.findByKategori(menu.getKategori()).isEmpty() || menuRepository.getReferenceById(menu.getId()).getStok() != menu.getStok() || menuRepository.getReferenceById(menu.getId()).getHarga() != menu.getHarga()){
                try {
                    Path targetPath = Paths.get(System.getProperty("user.dir"), "src","main","resources", "static", file.getOriginalFilename());
                    file.transferTo(targetPath.toFile());
                    String url = "http://localhost:8080/"+file.getOriginalFilename();
                    menu.setGambar(url);
                    menuRepository.save(menu);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return "redirect:/show-barang";
            }
            else{
                return "save-barang-gagal";
            }
        }
        else{
            return "notif-login";
        }
        
    }

    @GetMapping("/delete-barang/{id}")
    public String deleteBarang(@PathVariable Integer id){
        if(akunAdminRepository.findAll().get(0).getKeterangan().equals("sudah login")){
            Menu menu = menuRepository.getReferenceById(id);
            if(penjualanRepository.findByMenu(menu).isEmpty()){
                menuRepository.deleteById(id);
                return "redirect:/show-barang";
            }
            else{
                return "hapus-gagal";
            }
        }
        else{
            return "notif-login";
        }
    }

    @GetMapping("/delete-riwayat/{id}")
    public String deleteRiwayat(@PathVariable Integer id){
        if(akunAdminRepository.findAll().get(0).getKeterangan().equals("sudah login")){
            penjualanRepository.deleteById(id);
            return "reidrect:/show-penjualan";
        }
        else{
            return "notif-login";
        }
    }

    @GetMapping("/delete-all-riwayat")
    public String deleteAllRiwayat(){
        if(akunAdminRepository.findAll().get(0).getKeterangan().equals("sudah login")){
            penjualanRepository.deleteAll();
            return "redirect:/show-barang";
        }
        else{
            return "notif-login";
        }
    }

    @GetMapping("/add-kategori")
    public String addKategori(Model model){
        if(akunAdminRepository.findAll().get(0).getKeterangan().equals("sudah login")){
            Kategori kategori = new Kategori();
            model.addAttribute("data", kategori);
            return "add-kategori";
        }
        else{
            return "notif-login";
        }
    }

    @PostMapping("save-kategori")
    public String saveKategori(Kategori kategori){
        if(akunAdminRepository.findAll().get(0).getKeterangan().equals("sudah login")){
            Kategori ktg = kategoriRepository.findByName(kategori.getName());
            if(ktg == null){
                kategoriRepository.save(kategori);
                return "redirect:/show-kategori";
            }
            else{
                return "save-kategori-gagal";
            }
        }
        else{
            return "notif-login";
        }
    }

    @GetMapping("/show-kategori")
    public String ShowKategori(Model model){
        if(akunAdminRepository.findAll().get(0).getKeterangan().equals("sudah login")){
            model.addAttribute("data", kategoriRepository.findAll());
            return "show-kategori";
        }
        else{
            return "notif-login";
        }
    }

    @GetMapping("/update-kategori/{id}")
    public String updateKategori(@PathVariable Integer id,Model model){
        if(akunAdminRepository.findAll().get(0).getKeterangan().equals("sudah login")){
            Kategori kategori = kategoriRepository.getReferenceById(id);
            model.addAttribute("data", kategori);
            return "update-kategori";
        }
        else{
            return "notif-login";
        }
    }

    @GetMapping("/delete-kategori/{id}")
    public String deleteKategori(@PathVariable Integer id){
        if(akunAdminRepository.findAll().get(0).getKeterangan().equals("sudah login")){
            Kategori kategori = kategoriRepository.getReferenceById(id);
            if(menuRepository.findByKategori(kategori).isEmpty()){
                kategoriRepository.deleteById(id);
                return "redirect:/show-kategori";
            }
            else{
                return "hapus-gagal";
            }
        }
        else{
            return "notif-login";
        }
    }

    @GetMapping("/search-home")
    public String searchHome(Model model,@RequestParam(name = "src") String search){
        if(akunAdminRepository.findAll().get(0).getKeterangan().equals("sudah login")){
            Kategori kategori = kategoriRepository.findByName(search);
            if(menuRepository.findAllByName(search).isEmpty()){
                model.addAttribute("data", menuRepository.findByKategori(kategori));
                return "show-barang";
            }
            else{
                model.addAttribute("data",menuRepository.findAllByName(search));
                return "show-barang";
            }
        }
        else{
            return "notif-login";
        }
    }

    @GetMapping("/search-penjualan")
    public String searchPenjualan(Model model,@RequestParam(name = "search") String search){
        if(akunAdminRepository.findAll().get(0).getKeterangan().equals("sudah login")){
            Menu menu = menuRepository.findByName(search);
            Akun akun = akunRepository.findByName(search);
            if(penjualanRepository.findByAkun(akun).isEmpty()){
                model.addAttribute("data", penjualanRepository.findByMenu(menu));
                return "show-penjualan";
            }
            else{
                model.addAttribute("data", penjualanRepository.findByAkun(akun));
                return "show-penjualan";
            }
        }
        else{
            return "notif-login";
        }
    }

    @GetMapping("/search-kategori")
    public String searchKategori(Model model,@RequestParam(name = "search") String search){
        if(akunAdminRepository.findAll().get(0).getKeterangan().equals("sudah login")){
            model.addAttribute("data", kategoriRepository.findAllByName(search));
            return "show-barang";
        }
        else{
            return "notif-login";
        }
    }
}