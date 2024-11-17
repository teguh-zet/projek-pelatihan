package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Barang;
import com.example.demo.repository.BarangRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class AdminController {
    int idlas;

    @Autowired
    private BarangRepository barangRepository;

    @GetMapping("/home-admin")
    public String allBarang(Model model) {
        model.addAttribute("barangs", barangRepository.findAll());
        return "index";
    }

    @GetMapping("/add-barang")
    public String addBarang(Model model) {
        Barang barang = new Barang();
        model.addAttribute("barangs", barang);

        return "add-barang";
    }

    @PostMapping("/simpan-barang")
    public String savebarang(@ModelAttribute("barangs") Barang barang) {
        barangRepository.save(barang);
        idlas = barang.getId();

        return "forward:/upload";
    }

    @GetMapping("/update-barang/{id}")
    public String updateBarang(@PathVariable(value = "id") Integer id, Model model) {
        Barang barang = barangRepository.getReferenceById(id);
        model.addAttribute("barangs", barang);
        return "updateBarang";
    }

    @GetMapping("/delete-barang/{id}")
    public String hapusBarang(@PathVariable(value = "id") Integer id, Model model) {
        barangRepository.deleteById(id);
        return "redirect:/home-admin";
    }

    @GetMapping("/upload-form")
    public String showUploadForm() {
        return "uploadForm"; // uploadForm adalah nama halaman Thymeleaf (uploadForm.html)
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestPart("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Pilih berkas untuk diunggah");
            return "uploadStatus";
        }

        try {
            // Set tempat menyimpan file dalam proyek
            Path targetPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static",
                    file.getOriginalFilename());

            // Simpan file di dalam proyek
            file.transferTo(targetPath.toFile());

            // Simpan URL atau path di dalam database
            String url = "http://localhost:8080/" + file.getOriginalFilename();
            Barang barang = barangRepository.getReferenceById(idlas);
            // Barang barang = new Barang();
            barang.setGambar(url);

            barangRepository.save(barang);

            model.addAttribute("message", "Berhasil mengunggah");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Gagal mengunggah berkas");
            return "uploadStatus";
        }

        return "uploadStatus";
    }
}
