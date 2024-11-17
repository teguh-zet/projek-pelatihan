package com.teazautogarage.teazauto.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teazautogarage.teazauto.Model.Car;
// import com.teazautogarage.teazauto.Model.UserLogin;
import com.teazautogarage.teazauto.Repositories.BrandRepository;
import com.teazautogarage.teazauto.Repositories.CarRepository;
// import com.teazautogarage.teazauto.Repositories.UserLoginRepository;
// import com.teazautogarage.teazauto.Repositories.UserRepository;

@Controller
public class AppController {
    // @Autowired 
    // private UserLoginRepository userLoginRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("index")
    public String selectRole(){
        return "index";
    } 

    @GetMapping("/user-home")
    public String userHome(Model model){    
        // List<UserLogin> userLogin = userLoginRepository.findByUsername("eweer");
        // model.addAttribute("userLogin", userLoginRepository.findByUsername("teguh1"));
        List<Car> availableCars = carRepository.findByStatue("available");
        
        model.addAttribute("cars", availableCars);
        model.addAttribute("brand", brandRepository.findAll());
        return "home-user";
    }

    @GetMapping("/search-by-price-asc-layout")
    public String searchByPriceAsc(Model model){
        List<Car> cars = carRepository.findAllByOrderByPriceAsc();
        model.addAttribute("brand", brandRepository.findAll());
        model.addAttribute("cars", cars);
        return "layout";
    }
    @GetMapping("/search-by-price-asc-user")
    public String searchByPriceAscUser(Model model){

        List<Car> cars = carRepository.findAllByOrderByPriceAsc();
        // List<Car> availableCars = carRepository.findByStatue("available");
        model.addAttribute("brand", brandRepository.findAll());
        model.addAttribute("cars", cars);
        return "home-user";
    }
    @GetMapping("/search-by-model-layout")
    public String searchByModel(@RequestParam(name = "search")String modelCar,Model model){
        if (modelCar == null) {
            model.addAttribute("error", "You request not Found");
            model.addAttribute("cars", carRepository.findAll());
            return "layout";
        }
        List<Car> cars = carRepository.findByModelContainingIgnoreCase(modelCar);
        model.addAttribute("cars", cars);
        return "layout";

    }
    @GetMapping("/search-by-model-user")
    public String searchByModelUser(@RequestParam(name = "search")String modelCar,Model model){
        if (modelCar == null) {
            model.addAttribute("error", "You request not Found");
            model.addAttribute("cars", carRepository.findAll());
            return "layout";
        }
        List<Car> cars = carRepository.findByModelContainingIgnoreCase(modelCar);
        model.addAttribute("cars", cars);
        return "layout";

    }
    @GetMapping("/search-by-price-desc-layout")
    public String searchByPriceDesc(Model model){
        List<Car> cars = carRepository.findAllByOrderByPriceDesc();
        model.addAttribute("brand", brandRepository.findAll());
        model.addAttribute("cars", cars);
        return "layout";
    }
    @GetMapping("/search-by-price-desc-user")
    public String searchByPriceDescUser(Model model){
        List<Car> cars = carRepository.findAllByOrderByPriceDesc();
        model.addAttribute("brand", brandRepository.findAll());
        model.addAttribute("cars", cars);
        return "home-user";
    }

    
}

