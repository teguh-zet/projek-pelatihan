package com.penjualan.pasar.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.penjualan.pasar.entity.Akun;
import com.penjualan.pasar.entity.Keranjang;
import com.penjualan.pasar.entity.Menu;
import com.penjualan.pasar.entity.Penjualan;
import com.penjualan.pasar.entity.Tampungan;
import com.penjualan.pasar.repositories.AkunRepository;
import com.penjualan.pasar.repositories.KeranjangRepository;
import com.penjualan.pasar.repositories.MenuRepository;
import com.penjualan.pasar.repositories.PenjualanRepository;
import com.penjualan.pasar.repositories.TampunganRepository;

@Controller
public class MenuController {
    @Autowired 
    private MenuRepository menuRepository;

    @Autowired
    private TampunganRepository tampunganRepository;

    @Autowired
    private AkunRepository akunRepository;

    @Autowired
    private KeranjangRepository keranjangRepository;

    @Autowired
    private PenjualanRepository penjualanRepository;

    @GetMapping("/barang")
    public String makanan(Model model){
        if(tampunganRepository.findAll().isEmpty()){
            return "notif-login";
        }
        else{
            model.addAttribute("data", menuRepository.findAll());
            return "barang";
        }
    }

    @GetMapping("/beli-sekarang/{id}")
    public String beliSekarang(Model model,@PathVariable Integer id){
        if(tampunganRepository.findAll().isEmpty()){
            return "notif-login";
        }
        else{
            Menu mn = menuRepository.getReferenceById(id);
            model.addAttribute("data", mn);
            return "beli-sekarang";
        }
    }

    @PostMapping("/bayar/{id}")
    public String bayar(Model model ,@PathVariable Integer id,@RequestParam(name = "jumlah") int jlh){
        if(tampunganRepository.findAll().isEmpty()){
            return "notif-login";
        }
        else{
            Menu mn = menuRepository.getReferenceById(id);
            if(mn.getStok() < jlh){
                return "stok-kurang";
            }
            else{
                int total = 0;
                int jumlah = 0;
                jumlah = jlh * mn.getHarga();
                List<Tampungan> tamp =  tampunganRepository.findAll();
                Tampungan tampungan =  tamp.get(0);
                Akun akun = new Akun();
                akun.setGmail(tampungan.getGmail());
                akun = akunRepository.findByGmail(akun.getGmail());
                total = akun.getSaldo() - jumlah;
                if(total >= 0){
                    akun.setSaldo(total);
                    akun.setName(tampungan.getName());
                    akun.setPassword(tampungan.getPassword());
                    akun.setTtl(tampungan.getTtl());
                    akunRepository.save(akun);
                    tampungan.setSaldo(total);
                    tampunganRepository.save(tampungan);
                    Penjualan penjualan = new Penjualan();
                    penjualan.setAkun(akun);
                    penjualan.setJumlah(jlh);
                    penjualan.setMenu(mn);
                    penjualanRepository.save(penjualan);
                    mn.setStok(mn.getStok()-jlh);
                    menuRepository.save(mn);
                    return "pop-up-bayar-berhasil";
                }else{
                    return "pop-up-gagal-bayar";
                }
            }
        }
    }

    @GetMapping("/tambah-keranjang/{id}")
    public String keranjang(@PathVariable Integer id,Model model){
        if(tampunganRepository.findAll().isEmpty()){
            return "notif-login";
        }
        else{
            Menu menu = menuRepository.getReferenceById(id);
            model.addAttribute("data",menu);
            return "jumlah-keranjang";
        }
    }

    @PostMapping("/save-keranjang/{id}")
    public String saveKeranjang(@RequestParam(name = "jumlah") int jumlah,@PathVariable Integer id){
        if(tampunganRepository.findAll().isEmpty()){
            return "notif-login";
        }else{
            Menu menu = menuRepository.getReferenceById(id);
            if(menu.getStok() < 0 || menu.getStok() - jumlah < 0){
                return "stok-kurang";
            }
            else{
                List<Tampungan> tamp =  tampunganRepository.findAll();
                Tampungan tampungan =  tamp.get(0);
                Akun akun = new Akun();
                akun.setGmail(tampungan.getGmail());
                akun = akunRepository.findByGmail(akun.getGmail());
                Keranjang keranjang = new Keranjang();
                keranjang.setAkun(akun);
                keranjang.setJumlah(jumlah);
                keranjang.setMenu(menu);
                keranjangRepository.save(keranjang);
                menu.setStok(menu.getStok()-jumlah);
                menuRepository.save(menu);
                return "redirect:/barang";
            }
        }
    }

    @GetMapping("/top-up")
    public String topUp(Model model){
        if(tampunganRepository.findAll().isEmpty()){
            return "notif-login";
        }
        else{
            model.addAttribute("data",tampunganRepository.findAll().get(0));
            return "top-up";
        }
    }

    @PostMapping("/save-top-up")
    public String saveTopUp(@RequestParam(name = "jumlah") int jumlah){
        if(tampunganRepository.findAll().isEmpty()){
            return "notif-login";
        }
        else{
            List<Tampungan> tampungans = tampunganRepository.findAll();
            Tampungan tampungan = tampungans.get(0);
            tampungan.setSaldo(tampungan.getSaldo() + jumlah - 500);
            tampunganRepository.save(tampungan);
            Akun akun = akunRepository.findByGmail(tampungan.getGmail());
            akun.setSaldo(tampungan.getSaldo());
            akunRepository.save(akun);
            return "top-up-success";
        }
    }

    @GetMapping("/search-barang")
    public String searchBarang(@RequestParam(name = "src") String value,Model model){
        if(tampunganRepository.findAll().isEmpty()){
            return "notif-login";
        }
        else{
            model.addAttribute("data", menuRepository.findAllByName(value));
            return "barang";
        }
    }

    @GetMapping("/bayar-keranjang")
    public String bayarKeranjang(Model model){
        if(tampunganRepository.findAll().isEmpty()){
            return "notif-login";
        }
        else{
            if(keranjangRepository.findAll().isEmpty()){
                return "keranjang-kosong";
            }
            else{
                List<Tampungan> listTamp = tampunganRepository.findAll();
                Tampungan tampungan = listTamp.get(0);
                Akun akun = akunRepository.findByGmail(tampungan.getGmail());
                model.addAttribute("akun", akunRepository.findAll());
                model.addAttribute("menu", menuRepository.findAll());
                model.addAttribute("data", keranjangRepository.findByAkun(akun));
                return "bayar-keranjang";
            }
        }
    }
    
    @GetMapping("/bayar-krjg")
    public String bayarKrjg(Model model){
        if(tampunganRepository.findAll().isEmpty()){
            return "notif-login";
        }
        else{
            List<Tampungan> listTamp = tampunganRepository.findAll();
            Tampungan tampungan = listTamp.get(0);
            Akun akun = akunRepository.findByGmail(tampungan.getGmail());
            List<Keranjang> keranjangs = keranjangRepository.findByAkun(akun);
            int total = 0,jumlah = 0;
            List<Penjualan> penjualan = new ArrayList<>();
            for(int i = 0; i < keranjangs.size(); i++){
                Menu menu = menuRepository.findByName(keranjangs.get(i).getMenu().getName());
                total += keranjangs.get(i).getJumlah() * menu.getHarga();
                Penjualan penjualan2 = new Penjualan();
                penjualan2.setAkun(akun);
                penjualan2.setMenu(menu);
                penjualan2.setJumlah(keranjangs.get(i).getJumlah());
                penjualan.add(penjualan2);
            }
            jumlah = tampungan.getSaldo() - total;
            if(jumlah >= 0){
                keranjangRepository.deleteAll();
                akun.setSaldo(jumlah);
                tampungan.setSaldo(jumlah);
                akunRepository.save(akun);
                tampunganRepository.save(tampungan);
                penjualanRepository.saveAll(penjualan);
                return "pop-up-bayar-berhasil";
            }
            else{
                return "pop-up-gagal-bayar";
            }
        }
    }

    @GetMapping("/delete-keranjang/{id}")
    public String deleteKeranjang(@PathVariable Integer id,Model model){
        if(tampunganRepository.findAll().isEmpty()){
            return "notif-login";
        }
        else{
            Keranjang keranjang = keranjangRepository.getReferenceById(id);
            Menu menu = menuRepository.findByName(keranjang.getMenu().getName());
            menu.setStok(keranjang.getJumlah() + menu.getStok());
            menuRepository.save(menu);
            keranjangRepository.deleteById(id);
            model.addAttribute("data", keranjangRepository.findAll());
            return "bayar-keranjang";
        }
    }
    
}