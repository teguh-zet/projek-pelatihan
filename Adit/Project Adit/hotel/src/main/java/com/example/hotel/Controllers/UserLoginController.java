package com.example.hotel.Controllers;

import com.example.hotel.Model.Room;
import com.example.hotel.Model.User;
import com.example.hotel.Model.UserLogin;
import com.example.hotel.Repository.BookingRepository;
import com.example.hotel.Repository.UserLoginRepository;
import com.example.hotel.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserLoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLoginRepository userLoginRepository;

     @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/login-user")
    public String login(Model model) {
        UserLogin userLogin = new UserLogin();
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("userLogin", userLogin);
        return "loginUser";
    }

    @PostMapping("/login-user")
    public String isLogin(UserLogin userLogin) {
        List<User> users = userRepository.findAll();
        boolean isLogin = false;

        for (User user2 : users) {
            if (user2.getUsername().equals(userLogin.getUsername())
                    && user2.getPassword().equals(userLogin.getPassword())) {
                isLogin = true;
                userLogin.setId(user2.getId());
                break;
            }
        }
        if (isLogin) {
            userLoginRepository.deleteAll();
            userLoginRepository.save(userLogin);
            return "redirect:/kamar";
        } else {
            return "redirect:/login-user";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "loginUser";
    }
     @GetMapping("/kamar")
    public String home() {
        if (userLoginRepository.findAll().isEmpty()) {
            return "redirect:/login-user";
        }
        return "rooms";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user) {
        List<User> existingUsers = userRepository.findAll();

        for (User existingUser : existingUsers) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                // Username already exists, handle accordingly (e.g., show an error message)
                return "redirect:/kamar";
            }
        }

        userRepository.save(user);
        return "redirect:/login-user"; // Redirect to login-user.html after successful registration
    }
    @GetMapping("/update-user/{id}")
    public String updateBuku(@PathVariable(value = "id") Integer id, Model model) {
        User users = userRepository.getReferenceById(id);
        model.addAttribute("user", users);
        return "updateUser";
    }
    @GetMapping("/profil")
    public String Profil(Model model) {
        List<UserLogin> userLogins = userLoginRepository.findAll();
        User user = userRepository.getReferenceById(userLogins.get(0).getId());
        model.addAttribute("data",user);
        model.addAttribute("booking", bookingRepository.findByUser(user));
        return "profil";
    }
    @GetMapping("/logout")
    public String logout(Model model) {
        userLoginRepository.deleteAll();
        return "redirect:/login-user";
    }
}
