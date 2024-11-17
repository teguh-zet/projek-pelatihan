package com.teazautogarage.teazauto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.teazautogarage.teazauto.Model.Admin;
import com.teazautogarage.teazauto.Model.AdminLogin;
import com.teazautogarage.teazauto.Model.Car;
import com.teazautogarage.teazauto.Model.Transactions;
import com.teazautogarage.teazauto.Model.User;
import com.teazautogarage.teazauto.Model.UserLogin;
import com.teazautogarage.teazauto.Repositories.AdminLoginRepository;
import com.teazautogarage.teazauto.Repositories.AdminRepository;
import com.teazautogarage.teazauto.Repositories.BrandRepository;
import com.teazautogarage.teazauto.Repositories.CarRepository;
import com.teazautogarage.teazauto.Repositories.TransactionsRepository;
import com.teazautogarage.teazauto.Repositories.UserLoginRepository;
import com.teazautogarage.teazauto.Repositories.UserRepository;

import java.util.List;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestParam; cekUser;
// import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private TransactionsRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminLoginRepository adminLoginRepository;

    Long cek;
    long ceklogin;
    long cektransaction;

    @GetMapping("/login-admin")
    public String loginAdmin(Model model) {
        adminLoginRepository.deleteAll();
        userLoginRepository.deleteAll();
        AdminLogin adminLogin = new AdminLogin();
        model.addAttribute("adminLogin", adminLogin);
        return "admin-login";
    }

    @PostMapping("/save-login-admin")
    public String loginAdmin(@ModelAttribute("adminLogin") AdminLogin adminLogin) {
        List<Admin> admins = adminRepository.findAll();
        boolean adminExits = false;
        for (Admin x : admins) {
            if (x.getUsername().equals(adminLogin.getUsername()) && x.getPassword().equals(adminLogin.getPassword())) {
                adminExits = true;

                adminLogin.setId(x.getId());
                break;
            }
        }
        if (adminExits) {
            adminLoginRepository.deleteAll();
            adminLoginRepository.save(adminLogin);
            return "redirect:/show-car"; // Redirect to rooms.html after successful login
        } else {
            // Handle login failure, e.g., show an error message
            return "redirect:/login-admin";
        }
    }
    @GetMapping("/login-users")
    public String loginUser(Model model) {
        adminLoginRepository.deleteAll();
        userLoginRepository.deleteAll();
        UserLogin userLogin = new UserLogin();
        model.addAttribute("userLogin", userLogin);
        return "user-login";
    }
    @PostMapping("/save-login-user")
    public String loginUser(@ModelAttribute("userLogin") UserLogin userLogin) {
        List<User> users = userRepository.findAll();
        boolean userExists = false;
        for (User user : users) {
            if (user.getUsername().equals(userLogin.getUsername())
                    && user.getPassword().equals(userLogin.getPassword())) {
                userExists = true;
                // Set the id or any other necessary fields in UserLogin
                userLogin.setId(user.getId());
                ceklogin = user.getId();
                break;
            }
        }
        if (userExists) {
            userLoginRepository.deleteAll();
            userLoginRepository.save(userLogin);
            return "redirect:/user-home"; // Redirect to rooms.html after successful login
        } else {
            // Handle login failure, e.g., show an error message
            return "redirect:/login-users";
        }
    }
    

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-regis";
    }

    @PostMapping("/save-register")
    public String register(@ModelAttribute("user") User user, Model model) {
        List<User> existingUsers = userRepository.findAll();

        for (User existingUser : existingUsers) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                model.addAttribute("message", "username telah terdaftar");
                return "redirect:/register";
            }
        }

        userRepository.save(user);
        return "redirect:/login-users";
    }

    

    @GetMapping("/form-transaction/{id}")
    public String transactionFormString(@PathVariable("id") Long id, Model model) {

        if (userLoginRepository.findAll().isEmpty()) {
            return "redirect:/login-users";
        }
        cek = id;
        Car car = carRepository.getReferenceById(id);
        // .orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));

        model.addAttribute("idCar", id);

        model.addAttribute("cars", car);
        model.addAttribute("user", userRepository.findAll());
        model.addAttribute("brand", brandRepository.findAll());
        model.addAttribute("transactions", new Transactions());
        return "transaction-form";
    }

    @PostMapping("/save-form-pembelian")
    public String saveTransaction(@ModelAttribute Transactions transactions) {
        User user = userRepository.getReferenceById(ceklogin);
        Car selectedCar = carRepository.findById(cek)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id"));
        transactions.setPrice(selectedCar.getPrice());
        transactions.setCar(selectedCar);
        transactions.setUser(user);
        selectedCar.setStatue("sold");
        carRepository.save(selectedCar);
        
        transactionRepository.save(transactions);
        cektransaction = transactions.getId();
        return "redirect:/thanks";
    }
     @GetMapping("thanks")
    public String thankForPay(Model model) {
        Transactions transactions = transactionRepository.getReferenceById(cektransaction);
        model.addAttribute("transactions", transactions);
        return "thankgiving";
    }
    
    

}