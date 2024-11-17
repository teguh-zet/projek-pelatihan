package com.teazautogarage.teazauto.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teazautogarage.teazauto.Model.AdminLogin;
import com.teazautogarage.teazauto.Model.Brand;
import com.teazautogarage.teazauto.Model.Car;
import com.teazautogarage.teazauto.Repositories.AdminLoginRepository;
import com.teazautogarage.teazauto.Repositories.BrandRepository;
import com.teazautogarage.teazauto.Repositories.CarRepository;
import com.teazautogarage.teazauto.Repositories.TransactionsRepository;
import com.teazautogarage.teazauto.Repositories.UserRepository;
@Controller
public class AdminController {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private AdminLoginRepository adminLoginRepository;
    @GetMapping("show-user")
    public String listUser(Model model){
        if(adminLoginRepository.findAll().isEmpty()){
            AdminLogin adminLogin = new AdminLogin();
            model.addAttribute("adminLogin", adminLogin);
            return "admin-login";
        }
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }
    @GetMapping("show-transaction")
    public String listTransaction(Model model){
        if(adminLoginRepository.findAll().isEmpty()){
            AdminLogin adminLogin = new AdminLogin();
            model.addAttribute("adminLogin", adminLogin);
            return "admin-login";
        }
        
        model.addAttribute("transactions", transactionsRepository.findAll());
        return "transationList";
    }
    @GetMapping("show-brand")
    public String listBrand(Model model) {

        if(adminLoginRepository.findAll().isEmpty()){
            AdminLogin adminLogin = new AdminLogin();
            model.addAttribute("adminLogin", adminLogin);
            return "admin-login";
        }
        
        model.addAttribute("brand", brandRepository.findAll());
        return "brand-list";
    }
    
    //car
    @GetMapping("show-car")
    public String listCars(Model model) {
        if(adminLoginRepository.findAll().isEmpty()){
            AdminLogin adminLogin = new AdminLogin();
            model.addAttribute("adminLogin", adminLogin);
            return "admin-login";
        }
        model.addAttribute("brand", brandRepository.findAll());
        model.addAttribute("cars", carRepository.findAll());
        return "carList";
    }

    @GetMapping("/find-by-brand")
    public String findByBrand(@RequestParam(name = "brand") Brand brand, Model model) {
        if(adminLoginRepository.findAll().isEmpty()){
            AdminLogin adminLogin = new AdminLogin();
            model.addAttribute("adminLogin", adminLogin);
            return "admin-login";
        }
        List<Car> car = carRepository.findByBrand(brand);
        model.addAttribute("car", car);
        return "home";

    }

    @GetMapping("/search-by-model")
    public String searchByName(@RequestParam(name = "search") String modelCar, Model model) {
        if(adminLoginRepository.findAll().isEmpty()){
            AdminLogin adminLogin = new AdminLogin();
            model.addAttribute("adminLogin", adminLogin);
            return "admin-login";
        }
        if (modelCar == null) {
            model.addAttribute("error", "You request not Found");
            model.addAttribute("cars", carRepository.findAll());
            return "carList";
        }
        List<Car> cars = carRepository.findByModelContainingIgnoreCase(modelCar);
        model.addAttribute("cars", cars);
        return "carList";
    }

    @GetMapping("/search-by-brand")
    public String searchByBrand(@RequestParam(name = "brand", required = false) Brand brand, Model model) {
        if(adminLoginRepository.findAll().isEmpty()){
            AdminLogin adminLogin = new AdminLogin();
            model.addAttribute("adminLogin", adminLogin);
            return "admin-login";
        }

        List<Car> cars = carRepository.findByBrand(brand);
        model.addAttribute("brand", brandRepository.findAll());
        model.addAttribute("cars", cars);
        return "carList";
    }

    @GetMapping("/search-by-color")
    public String searchByColor(@RequestParam(name = "color", required = false) String color, Model model) {
        if(adminLoginRepository.findAll().isEmpty()){
            AdminLogin adminLogin = new AdminLogin();
            model.addAttribute("adminLogin", adminLogin);
            return "admin-login";
        }
        List<Car> cars = carRepository.findByColorContainingIgnoreCase(color);
        model.addAttribute("brand", brandRepository.findAll());
        model.addAttribute("cars", cars);
        return "carList";

    }

    @GetMapping("/search-by-price-asc")
    public String searchByPriceAsc(Model model) {
        if(adminLoginRepository.findAll().isEmpty()){
            AdminLogin adminLogin = new AdminLogin();
            model.addAttribute("adminLogin", adminLogin);
            return "admin-login";
        }
        List<Car> cars = carRepository.findAllByOrderByPriceAsc();
        model.addAttribute("brand", brandRepository.findAll());
        model.addAttribute("cars", cars);
        return "carList";
    }
 
    @GetMapping("/add")
    public String showAddCarForm(Model model) {
        if(adminLoginRepository.findAll().isEmpty()){
            AdminLogin adminLogin = new AdminLogin();
            model.addAttribute("adminLogin", adminLogin);
            return "admin-login";
        }
        Car car = new Car();
        model.addAttribute("car", car);
        model.addAttribute("brand", brandRepository.findAll());
        return "addCar";
    }

}
