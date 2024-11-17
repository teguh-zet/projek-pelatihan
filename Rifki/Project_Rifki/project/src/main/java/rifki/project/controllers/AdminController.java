package rifki.project.controllers;

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

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import rifki.project.models.Product;
import rifki.project.repository.LoginAdminRepository;
import rifki.project.repository.ProductRepository;
import rifki.project.repository.UserRepository;

@Controller
public class AdminController {

    @Autowired
    private LoginAdminRepository loginAdminRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/home_admin")
    public String HomeAdmin(Model model) {
        List<Product> AllProduct = productRepository.findAll();
        model.addAttribute("products", AllProduct);
        model.addAttribute("users", userRepository.findAll());
        if (loginAdminRepository.findAll().isEmpty()) {
            return "redirect:/login";
        }
        return "home_admin";

    }

    @GetMapping("/add_product")
    public String addProduct(Model model) {
        if (loginAdminRepository.findAll().isEmpty()) {
            return "redirect:/login";
        }
        Product product = new Product();
        model.addAttribute("product", product);
        return "add_product";
    }

    @PostMapping("/add_product")
    public String saveProduct(Product product, @RequestPart("file") MultipartFile file, Model model) {

        try {
            Path targetPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static",
                    file.getOriginalFilename());

            file.transferTo(targetPath.toFile());

            String url = "http://localhost:8090/" + file.getOriginalFilename();

            product.setImage_product(url);

            productRepository.save(product);
            // for (Product product2 : AllProduct) {
            // if (product) {
            // duplicate = true;
            // break;
            // }
            // }
            // if (!duplicate) {
            // productRepository.save(product);
            // }
        } catch (IOException e) {
            e.printStackTrace();
            return "add_product";
        }
        return "redirect:/home_admin";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Integer id, Model model) {
        productRepository.deleteById(id);
        return "redirect:/home_admin";
    }

    @GetMapping("/update_product/{id}")
    public String updateProduct(@PathVariable Integer id, Model model) {
        if (loginAdminRepository.findAll().isEmpty()) {
            return "redirect:/login";
        }
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product Id" + id));
        model.addAttribute("product", product);
        return "update_product";
    }

    @PostMapping("/saveUpdate")
    public String postEditForm(@ModelAttribute("product") Product updateProduct, @RequestParam("file") MultipartFile file,
            Model model) throws IllegalStateException, IOException {
        Product product = productRepository.findById(updateProduct.getId())
                .orElseThrow(() -> new IllegalArgumentException("invalid product Id :" + updateProduct.getId()));
        product.setName_product(updateProduct.getName_product());
        product.setPrice(updateProduct.getPrice());
        Path targetPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static",
                file.getOriginalFilename());
        file.transferTo(targetPath.toFile());
        String url = "http://localhost:8090/" + file.getOriginalFilename();
        product.setImage_product(url);
        productRepository.save(product);
        return "redirect:/home_admin";
    }
}
