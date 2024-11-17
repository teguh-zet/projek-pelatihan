package com.example.hotel.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.hotel.Model.Admin;
import com.example.hotel.Model.Booking;
import com.example.hotel.Model.LoginAdmin;
import com.example.hotel.Model.Room;
import com.example.hotel.Repository.AdminRepository;
import com.example.hotel.Repository.BookingRepository;
import com.example.hotel.Repository.LoginAdminRepository;
import com.example.hotel.Repository.RoomRepository;

@Controller
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private LoginAdminRepository loginAdminRepository;

    // @Autowired
    // private BookingRepository customerRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired RoomRepository roomRepository;
    @GetMapping("/login")
    public String login(Model model) {
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "login";
    }
    @PostMapping("/login")
    public String isLogin(LoginAdmin admin) {
        List<Admin> admins = adminRepository.findAll();
        boolean isLogin = false;
        for (Admin admin2 : admins) {
            if (admin2.getUsername().equals(admin.getUsername()) && admin2.getPassword().equals(admin.getPassword())) {
                isLogin = true;
                // Set supaya id nya sama
                admin.setId(admin2.getId());
                break;
            }
        }

        if (isLogin) {
            loginAdminRepository.deleteAll();
            loginAdminRepository.save(admin);
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/")
    public String home(Model model) {
        // model sengaja tidak diisi biar contohnya makin simple
        if (loginAdminRepository.findAll().isEmpty()) {
            return "redirect:/login";
        }
        Room kamar= new Room();
        model.addAttribute("room",kamar);
        // Customer pelanggan= new Customer();
        model.addAttribute("pelanggan",kamar);
        model.addAttribute("listroom", roomRepository.findAll());
        model.addAttribute("customer", bookingRepository.findAll());
        return "admin";
    }

    @PostMapping("/customer")
    public String customer(Booking booking){
        bookingRepository.save(booking);
        return"customer";
    }
    
     @PostMapping("/booking")
    public String kong(Booking booking){
        bookingRepository.save(booking);
        return"booking";
    }
    @GetMapping("/dashboard")
    public String dashboard(){
        return"admin";
    }
     @GetMapping("/customer")
    public String customer(Model model){
        model.addAttribute("booking", bookingRepository.findAll());
        return"customer";
    }
       @GetMapping("/bookingg")
    public String boooking(Model model){
        // model.addAttribute("room", roomRepository.findAll());
        model.addAttribute("datanya", bookingRepository.findAll());
        System.out.println(bookingRepository.findAll());
        return"booking";
    }
}
