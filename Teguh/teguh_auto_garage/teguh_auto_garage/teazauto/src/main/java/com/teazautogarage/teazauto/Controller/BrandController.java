package com.teazautogarage.teazauto.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.teazautogarage.teazauto.Model.Brand;
import com.teazautogarage.teazauto.Repositories.BrandRepository;



@Controller
public class BrandController {
    @Autowired 
    private BrandRepository brandRepository;


    
    @GetMapping("/add-brand")
    public String addBrandCar(Model model){
        
        Brand Brand = new Brand();
        model.addAttribute("brands", Brand);
        return "add-brand";
    }
    @PostMapping("/save-brand")
    public String saveBrandCar(Brand Brand){
        brandRepository.save(Brand);
        return "redirect:/add-brand";
    }

    @GetMapping("/delete-brand/{id}")
    public String deleteBrand(@PathVariable(value = "id") Long id){
        brandRepository.deleteById(id);;
        return "redirect:/show-brand";

    }
   
    @PostMapping("post-brand")
    public String postBrand(@ModelAttribute("brand") Brand updateBrand ){
        Brand brand = brandRepository.getReferenceById(updateBrand.getId());
        brand.setName(updateBrand.getName());
        brandRepository.save(brand);
        return "redirect:/show-brand";
    }
    @GetMapping("update-brand/{id}")
    public String updateBrand(@PathVariable(value = "id")Long id, Model model){
        Brand Brand = brandRepository.getReferenceById(id);
        model.addAttribute("brand", Brand);
        return "editBrand";
    }
}

