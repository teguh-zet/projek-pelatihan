package com.teazautogarage.teazauto.Controller;

// import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// import com.teazautogarage.teazauto.Model.Brand;
import com.teazautogarage.teazauto.Model.Car;
import com.teazautogarage.teazauto.Repositories.BrandRepository;
import com.teazautogarage.teazauto.Repositories.CarRepository;
import com.teazautogarage.teazauto.Repositories.UserLoginRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class CarController {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private UserLoginRepository userLoginRepository;

    @GetMapping("home")
    public String homeLayout(Model model) {
        userLoginRepository.deleteAll();
        List<Car> availableCars = carRepository.findByStatue("available");
        model.addAttribute("cars", availableCars);
        model.addAttribute("brand", brandRepository.findAll());
        return "layout";
    }
    
  
    @PostMapping("add-car")
    public String addCar(
            @RequestParam("file") MultipartFile file, Model model, Car car) {
        try {
            Path targetPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static",
                    file.getOriginalFilename());
            file.transferTo(targetPath.toFile());
            String url = "http://localhost:8080/" + file.getOriginalFilename();

            car.setImagePath(url);
            carRepository.save(car);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/add";
        }
        return "redirect:/show-car";
    }

    @PostMapping("/save-car")
    public String saveCar(@ModelAttribute("car") Car car) {
        carRepository.save(car);
        return "redirect:/add";
    }

    @GetMapping("/edit/{id}")
    public String showEditCarForm(@PathVariable("id") Long id, Model model) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        model.addAttribute("car", car);
        model.addAttribute("brands", brandRepository.findAll());
        return "editCar";
    }

    @PostMapping("edit-car")
    public String postEditForm(@ModelAttribute("car") Car updateCar, @RequestParam("file") MultipartFile file)
            throws IllegalStateException, IOException {
        Car car = carRepository.findById(updateCar.getId())
                .orElseThrow(() -> new IllegalArgumentException("invalid car Id :" + updateCar.getId()));
        car.setBrand(updateCar.getBrand());
        car.setColor(updateCar.getColor());
        car.setModel(updateCar.getModel());
        car.setPrice(updateCar.getPrice());
        car.setKm(updateCar.getKm());
        Path targetPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static",
                file.getOriginalFilename());
        file.transferTo(targetPath.toFile());
        String url = "http://localhost:8080/" + file.getOriginalFilename();
        car.setImagePath(url);
        carRepository.save(car);
        return "redirect:/show-car";

    }

    @GetMapping("/delete-car/{id}")
    public String deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
        return "redirect:/show-car";
    }

}
