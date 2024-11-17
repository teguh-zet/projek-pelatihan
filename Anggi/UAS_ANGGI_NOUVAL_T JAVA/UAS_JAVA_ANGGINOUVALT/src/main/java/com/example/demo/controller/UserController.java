package com.example.demo.controller;

import java.util.ArrayList;
// import java.sql.Date;
// import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Admin;
import com.example.demo.model.Barang;
import com.example.demo.model.Keranjang;
import com.example.demo.model.User;
import com.example.demo.model.UserLogin;
import com.example.demo.repository.BarangRepository;
import com.example.demo.repository.KeranjangRepository;
import com.example.demo.repository.UserLoginRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private BarangRepository barangRepository;

    @Autowired
    private KeranjangRepository keranjangRepository;

    boolean cekakun = true;
    boolean cek = true;
    boolean cekadmin = false;
    Integer akun_id;
    Integer tamp_id_barang;

    @GetMapping("/admin")
    public String tampilanAdmin(Model model) {
        return "home-admin";
    }

    @GetMapping("/home-user")
    public String allBarang(Model model) {
        User user = userRepository.getReferenceById(akun_id);
        user.setUtang(1);
        userRepository.save(user);
        model.addAttribute("barangs", barangRepository.findAll());
        model.addAttribute("fashion",barangRepository.findByTipe("Fashion"));
        model.addAttribute("elektronik",barangRepository.findByTipe("Electronik"));
        return "index-user";
    }

    @GetMapping("/")
    public String home(Model model) {

        if (userLoginRepository.findAll().isEmpty()) {
            return "redirect:/login";
        }
        return "index";
    }

    @GetMapping("/daftar")
    public String daftar(Model model) {
        if (cekakun) {
            User user = new User();
            model.addAttribute("user", user);
            return "daftar";
        } else {
            cekakun = true;
            User user = new User("PERINGATAN !!", "akun dengan username tersebut sudah digunakan !!  ");
            model.addAttribute("user", user);
            return "daftar2";
        }
    }

    @PostMapping("/daftar")
    public String daftar(@ModelAttribute("user") User user) {

        List<User> allUsers = userRepository.findAll();

        for (User a : allUsers) {

            if (a.getUsername().equals(user.getUsername())) {
                cekakun = false;
                return "redirect:/daftar";
            }
        }
        user.setAlamat("o");
        user.setUang(0);
        
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        if (cekadmin) {
            return "redirect:/home-admin";
        }
        if (cek) {
            return "login";
        } else {
            return "login2";
        }
    }

    @PostMapping("/login")
    public String isLogin(UserLogin user) {
        Admin admin = new Admin("admin1", "anggi123");
        List<User> users = userRepository.findAll();
        boolean cekLogin = false;
        for (User user2 : users) {
            if (user2.getUsername().equals(user.getUsername()) && user2.getPassword().equals(user.getPassword())) {
                
                cekLogin = true;
                // Set supaya id nya sama
                user.setId(user2.getId());
                break;
            }
        }
        if (admin.getUsername().equals(user.getUsername()) && admin.getPassword().equals(user.getPassword())) {
            cekadmin = true;

            return "redirect:/login";
        }
        if (cekLogin) {
            userLoginRepository.deleteAll();
            userLoginRepository.save(user);
            
            akun_id = user.getId();
            return "redirect:/home-user";
        } else {
            cek = false;
            return "redirect:/login";
        }
    }

    @GetMapping("/keranjang/{id}")
    public String saveKeranjang(@PathVariable(value = "id") Integer id, Model model) {
        Barang barang = barangRepository.getReferenceById(id);
        User d = userRepository.getReferenceById(akun_id);
        List<Keranjang> cek = keranjangRepository.findAll(); 
        for (Keranjang x: cek) {
            if(x.getId_Barang().getId() == id && x.getId_user().getId()== akun_id){
                 model.addAttribute("message", "Barang Sudah Ada di Keranjang");
                 return "statusKeranjang";
            }
        }

        Keranjang keran = new Keranjang();
        keran.setBanyak(1);
        keran.setId_Barang(barang);
        keran.setId_user(d);

        keran.setTotalHarga(barang.getHarga());

        keranjangRepository.save(keran);

        model.addAttribute("message", "Barang berhasil ditambahkan dikeranjang");
        return "statusKeranjang";
    }

    @GetMapping("/isi-keranjang")
    public String allkeranjang(Model model) {
        List<Keranjang> keran = keranjangRepository.findAll();
        List<Keranjang> kur = new ArrayList<>();
        for (Keranjang x : keran) {
                if(x.getId_user().getId() == akun_id){
                    kur.add(x);
                }
        }
        model.addAttribute("keranjangs",kur);
        return "keranjang";
    }

    @GetMapping("/logout")
    public String logut(Model mode) {
        userLoginRepository.deleteAll();
        cekadmin = false;
        return "redirect:/login";
    }

    @GetMapping("/search")
    public String searchByName(@RequestParam(name = "search") String nama, Model model) {
        List<Barang> barangs = barangRepository.findByNamaContainingIgnoreCase(nama);
        if (barangs.isEmpty()) {
            barangs = barangRepository.findByTipeContainingIgnoreCase(nama);

        }
        if (barangs.isEmpty()) {
            barangs = barangRepository.findByDeskripsiContainingIgnoreCase(nama);
        }

        model.addAttribute("barangs", barangs);
        return "cari-barang";
    }

       @GetMapping("/searchh")
    public String searchByNam(@RequestParam(name = "search") String nama, Model model) {
        List<Barang> barangs = barangRepository.findByNamaContainingIgnoreCase(nama);
        if (barangs.isEmpty()) {
            barangs = barangRepository.findByTipeContainingIgnoreCase(nama);

        }
        if (barangs.isEmpty()) {
            barangs = barangRepository.findByDeskripsiContainingIgnoreCase(nama);
        }

        model.addAttribute("barangs", barangs);
        return "cari-user";
    }
 
 
    @GetMapping("/delete-keranjang/{id}")
    public String hapusBarang(@PathVariable(value = "id") Integer id, Model model) {
        keranjangRepository.deleteById(id);
        return "redirect:/isi-keranjang";
    }

    @GetMapping("/update-keranjang/{id}")
    public String updateKeranjang(@PathVariable(value = "id") Integer id, Model model) {
        Keranjang keran = keranjangRepository.getReferenceById(id);
      
        model.addAttribute("keranjangs", keran);
        return "update-banyak";
    }
  @GetMapping("/beli-barang/{id}")
    public String belibarang(@PathVariable(value = "id") Integer id, Model model) {
        
       Barang barang = barangRepository.getReferenceById(id);
       
       User user = userRepository.getReferenceById(akun_id);
        if(user.getAlamat().equals("o")){

        return "redirect:/isi-data-diri";
        }
       if(barang.getHarga() > user.getUang()){
        model.addAttribute("message", "saldo tidak cukup periksa saldo kembali saldo anda");
        return "statusKeranjang";

       }
       user.setUang(user.getUang()-barang.getHarga());
       userRepository.save(user);
       barang.setStok(barang.getStok()-1);
       barangRepository.save(barang);
      
        model.addAttribute("message", "Pembayaran Berhasil Semoga HariMu Menyenangkan !! :)");
        return "statusKeranjang";

    }
    @PostMapping("/simpan-keranjang")
    public String  savebarang(@ModelAttribute("keranjangs") Keranjang keranjang) {
        Integer goku;
        User user = userRepository.getReferenceById(akun_id);
        Barang barang = barangRepository.getReferenceById(keranjang.getId_Barang().getId());
        goku = barang.getHarga();
        keranjang.setTotalHarga(goku * keranjang.getBanyak());
        keranjang.setId_user(user);
        keranjangRepository.save(keranjang);

        return "redirect:/isi-keranjang";
    }

    @GetMapping("/keranjang-bayar")
    public String bayarall( Model model) {
        Integer all_harga=0;
        List<Keranjang> keran = keranjangRepository.findAll();
        User user =  userRepository.getReferenceById(akun_id);
        for (Keranjang keranjang : keran) {
            all_harga+=keranjang.getTotalHarga();
        }
        user.setUtang(all_harga);
        userRepository.save(user);
      List<Keranjang> kur = keranjangRepository.findAll();
      List<Keranjang> hutang = new ArrayList<>();
      for (Keranjang x : kur) {
            if(x.getId_user().getId() == akun_id){
                hutang.add(x);
            }
      }

        model.addAttribute("user", user);
        model.addAttribute("keranjangs", hutang);
        return "bayar-barang";
    }


    @GetMapping("/pembayaran")
    public String pembayaran(Model model){
        List<Keranjang> keran = keranjangRepository.findAll();
        User user =  userRepository.getReferenceById(akun_id);

        if(user.getAlamat().isEmpty()){
             model.addAttribute("message", "ISI DATA DIRI ANDA TERLEBIH DAHULU");
        return "statusKeranjang";
        }
     
      


        if(user.getUtang() > user.getUang()){
        model.addAttribute("message", "saldo tidak cukup periksa saldo kembali saldo anda");
        return "statusKeranjang";

        }
    for (Keranjang x : keran) {
         if(x.getId_user().getId() == akun_id){
                keranjangRepository.deleteById(x.getId());
            }
        }
        for (Keranjang y : keran) {
        
        Barang barang = barangRepository.getReferenceById(y.getId_Barang().getId());
        if(barang.getStok() < y.getBanyak()){

            model.addAttribute("message", "Stok kurang periksa kemabli stok sebelum membeli barang");
            return "statusKeranjang";
        }
       }
    for (Keranjang y : keran) {
        
        Barang barang = barangRepository.getReferenceById(y.getId_Barang().getId());
        barang.setStok(barang.getStok()-y.getBanyak());
       }
    user.setUang(user.getUang()-user.getUtang()  );
    userRepository.save(user);
    
    model.addAttribute("message", "Pembayaran Berhasil Semoga HariMu Menyenangkan !! :)");
    return "statusKeranjang";
}

@GetMapping("/isi-data-diri")
public String isidatadiri(Model model) {
    User user = new User();
    model.addAttribute("users", user);

    return "add-data-diri";
}

 @PostMapping("/save-data-diri")
    public String isidata(@ModelAttribute("users") User user) {
       User input = userRepository.getReferenceById(akun_id);
        input.setAlamat(user.getAlamat());
        input.setLahir(user.getLahir());
        input.setStatus(user.getStatus());
        userRepository.save(input);
        return "redirect:/home-user";
    }

    @GetMapping("/all-info")
    public String alldata(Model model){
        User user = userRepository.getReferenceById(akun_id);
        if(user.getAlamat().equals("o")){
            return "redirect:/isi-data-diri";
        }
        model.addAttribute("users", user);
        return "all-data";
    }

    @GetMapping("/top-up")
    public String topup(Model model){
        User user= new User();
    
        model.addAttribute("top", user);
        return "top-up";
    }

    @PostMapping("/top-up")
    public String topup(@ModelAttribute("top") User user , Model model){
     User x= userRepository.getReferenceById(akun_id);
        x.setUang(user.getUang()+x.getUang());
        userRepository.save(x);
    model.addAttribute("message", "ANDA BERHASIL TOP UP");
    return "statusKeranjang";
    }



 }

// }




