package com.moslemwear.bismillahproject.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.moslemwear.bismillahproject.models.Admin;
import com.moslemwear.bismillahproject.models.AdminLogin;
import com.moslemwear.bismillahproject.models.Item;
import com.moslemwear.bismillahproject.models.User;
import com.moslemwear.bismillahproject.repositories.AdminLoginRepository;
import com.moslemwear.bismillahproject.repositories.AdminRepository;
import com.moslemwear.bismillahproject.repositories.ItemRepository;
import com.moslemwear.bismillahproject.repositories.UserRepository;
import com.moslemwear.bismillahproject.repositories.VarietyRepository;

@Controller
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private VarietyRepository varietyRepository;

    @Autowired
    private AdminLoginRepository adminLoginRepository;

    // @Autowired   
    // private AdminRepository adminRepository;

    // @Autowired
    // private UserRepository userRepository;

    @GetMapping("/moslemwear.com")
    private String moslemWear(Model model) {
        // model.addAttribute(null, model)
        return "/moslemwear.com";
    }

    @GetMapping("/add-item")
    public String addItem(Model model) {
        if (adminLoginRepository.findAll().isEmpty()) {
            AdminLogin adminLogin = new AdminLogin();
            model.addAttribute("adminLogin", adminLogin);
            return "redirect:/login-admin";
        }
        Item item = new Item();
        model.addAttribute("item", item);
        model.addAttribute("variety", varietyRepository.findAll());
        return "/add-item";
    }

    @GetMapping("/edit/{id}")
    public String showEditItemForm(@PathVariable("id") Integer id, Model model) {
        if (adminLoginRepository.findAll().isEmpty()) {
            AdminLogin adminLogin = new AdminLogin();
            model.addAttribute("adminLogin", adminLogin);
            return "redirect:/login-admin";
        }
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Item Id:" + id));
        model.addAttribute("item", item);
        model.addAttribute("variety", varietyRepository.findAll());
        return "/edit-item";
    }

    @PostMapping("/save-edit-item")
    public String postEditForm(@ModelAttribute("item") Item updateItem, @RequestParam("file") MultipartFile file,
            Model model)
            throws IllegalStateException, IOException {
        Item item = itemRepository.findById(updateItem.getId())
                .orElseThrow(() -> new IllegalArgumentException("invalid item Id :" + updateItem.getId()));
        item.setName(updateItem.getName());
        item.setPrice(updateItem.getPrice());
        item.setStock(updateItem.getStock());
        item.setDetail(updateItem.getDetail());
        item.setVariety(updateItem.getVariety());
        Path targetPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static",
                file.getOriginalFilename());
        file.transferTo(targetPath.toFile());
        String url = "http://localhost:8080/" + file.getOriginalFilename();
        item.setImagePath(url);
        itemRepository.save(item);
        return "redirect:/home-admin";
    }

    // @PostMapping("/save-item")
    // public String saveItem(@ModelAttribute("item") Item item, Model model) {
    // itemRepository.save(item);
    // return "/upload";
    // }

    @PostMapping("/upload")
    public String handleFileUpload(Item item, @RequestPart("file") MultipartFile file, Model model) {
        try {
            // Set tempat menyimpan file dalam proyek
            Path targetPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static",
                    file.getOriginalFilename());

            // Simpan file di dalam proyek
            file.transferTo(targetPath.toFile());

            // Simpan URL atau path di dalam database

            String url = "http://localhost:8080/" + file.getOriginalFilename();
            item.setImagePath(url);
            itemRepository.save(item);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/add-item";
        }
        return "redirect:/home-admin";
    }

    @GetMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable Integer id, Model model) {
        if (adminLoginRepository.findAll().isEmpty()) {
            AdminLogin adminLogin = new AdminLogin();
            model.addAttribute("adminLogin", adminLogin);
            return "redirect:/login-admin";
        }
        itemRepository.deleteById(id);
        return "redirect:/home-admin";
    }

    @GetMapping("/search")
    public String searchByName(@RequestParam(name = "search") String name, Model model) {
        if (adminLoginRepository.findAll().isEmpty()) {
            AdminLogin adminLogin = new AdminLogin();
            model.addAttribute("adminLogin", adminLogin);
            return "redirect:/login-admin";
        }
        List<Item> items = itemRepository.findByNameContainingIgnoreCase(name);
        model.addAttribute("item", items);
        return "/home-admin";
    }

    

}
