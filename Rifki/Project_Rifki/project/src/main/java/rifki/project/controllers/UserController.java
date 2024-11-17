package rifki.project.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import rifki.project.models.*;
import rifki.project.repository.*;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginUserRepository loginUserRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private LoginAdminRepository loginAdminRepository;

    private Integer idUserActv;

    @GetMapping("/login")
    public String login(Model model) {
        User user = new User();
        loginAdminRepository.deleteAll();
        loginUserRepository.deleteAll();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String isLogin(LoginUser user) {
        List<User> users = userRepository.findAll();
        Admin admin = adminRepository.getReferenceById(1);
        LoginAdmin admin2 = new LoginAdmin();
        admin2.setUsername(user.getUsername());
        admin2.setPassword(user.getPassword());
        boolean isAdmin = true;
        boolean isLogin = false;
        loginAdminRepository.deleteAll();
        loginUserRepository.deleteAll();
        for (User user2 : users) {
            if (user2.getUsername().equals(user.getUsername()) && user2.getPassword().equals(user.getPassword())) {
                isLogin = true;
                // Set supaya id nya sama
                user.setId(user2.getId());
                isAdmin = false;
                idUserActv = user.getId();
                break;
            }
        }
        if (isAdmin) {
            if (user.getUsername().equals(admin.getUsername()) && user.getPassword().equals(admin.getPassword())) {
                admin2.setId(admin.getId());
                loginAdminRepository.save(admin2);
                return "redirect:/home_admin";
            } else {
                return "redirect:/login";
            }
        } else if (isLogin) {
            loginUserRepository.save(user);
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/save_user")
    public String register(User user) {
        List<User> AllUser = userRepository.findAll();
        Admin admin = adminRepository.getReferenceById(1);
        boolean duplicate = false;
        for (User user2 : AllUser) {
            if (user.getUsername().equals(user2.getUsername()) || (user.getUsername().equals(admin.getUsername())
                    && user.getPassword().equals(admin.getPassword()))) {
                duplicate = true;
            }
        }
        if (!duplicate) {
            userRepository.save(user);
            return "redirect:/login";
        } else {
            return "register";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        List<Cart> AllCart = cartRepository.findAll();
        List<Cart> CartUser = new ArrayList<>();
        List<LoginUser> accountUser = loginUserRepository.findAll();
        List<User> AllUser = userRepository.findAll();
        // List<Product> AllProduct = productRepository.findAll();
        // List<Product> product = new ArrayList<>();
        User userActv = new User();
        Integer totalPay = 0;
        if (loginUserRepository.findAll().isEmpty()) {
            return "redirect:/login";
        }
        for (User User2 : AllUser) {
            for (LoginUser loginUser : accountUser) {
                if (User2.getId().equals(loginUser.getId())) {
                    userActv.setId(User2.getId());
                    userActv.setUsername(User2.getUsername());
                    userActv.setPassword(User2.getPassword());
                    userActv.setNoTelp(User2.getNoTelp());
                    userActv.setAddress(User2.getAddress());
                    break;
                }
            }
        }
        for (Cart cart : AllCart) {
            if (cart.getId_user().getId().equals(userActv.getId())) {
                CartUser.add(cart);
                totalPay += cart.getTotal();
            }
        }
        model.addAttribute("total", totalPay);
        model.addAttribute("carts", CartUser);
        model.addAttribute("userActv", userActv);
        return "cart";
    }

    @GetMapping("/")
    public String home_user(Model model) {
        List<Product> AllProduct = productRepository.findAll();
        List<User> AllUser = userRepository.findAll();
        List<LoginUser> accountLogin = loginUserRepository.findAll();
        List<Cart> carts = new ArrayList<>();
        model.addAttribute("cart", carts);
        model.addAttribute("products", AllProduct);
        model.addAttribute("user", AllUser);
        User userActv = new User();

        if (loginUserRepository.findAll().isEmpty()) {
            return "redirect:/login";
        }
        for (User User2 : AllUser) {
            for (LoginUser loginUser : accountLogin) {
                if (User2.getId().equals(loginUser.getId())) {
                    userActv.setId(User2.getId());
                    userActv.setUsername(User2.getUsername());
                    userActv.setPassword(User2.getPassword());
                    userActv.setNoTelp(User2.getNoTelp());
                    userActv.setAddress(User2.getAddress());
                    break;
                }
            }
        }
        model.addAttribute("userActv", userActv);
        return "home";
    }

    @GetMapping("/sale/{total}")
    public String sales(@PathVariable Integer total, Model model) {
        model.addAttribute("total", total);
        User user = userRepository.findById(idUserActv)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID" + idUserActv));

        model.addAttribute("userActv", user);
        return "notification";
    }

    @GetMapping("/confirm")
    public String confirm(Model model) {
        List<Cart> AllCart = cartRepository.findAll();
        for (Cart cart : AllCart) {
            if (cart.getId_user().getId().equals(idUserActv)) {
                cartRepository.delete(cart);
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/about")
    public String About(Model model) {
        List<User> AllUser = userRepository.findAll();
        List<LoginUser> accountLogin = loginUserRepository.findAll();
        User userActv = new User();

        if (loginUserRepository.findAll().isEmpty()) {
            return "redirect:/login";
        }
        for (User User2 : AllUser) {
            for (LoginUser loginUser : accountLogin) {
                if (User2.getId().equals(loginUser.getId())) {
                    userActv.setId(User2.getId());
                    userActv.setUsername(User2.getUsername());
                    userActv.setPassword(User2.getPassword());
                    userActv.setNoTelp(User2.getNoTelp());
                    userActv.setAddress(User2.getAddress());
                    break;
                }
            }
        }
        model.addAttribute("userActv", userActv);
        return "about";
    }

    @GetMapping("/addCart/{id}")
    public String addCart(@PathVariable Integer id, Model model) {
        List<Cart> AllCart = cartRepository.findAll();
        boolean duplicate = false;
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product Id" + id));
        User user = userRepository.findById(idUserActv)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID" + idUserActv));
        Cart cart = new Cart();
        cart.setId_product(product);
        cart.setId_user(user);
        cart.setQty(1);
        Integer total = product.getPrice() * cart.getQty();
        cart.setTotal(total);
        model.addAttribute("cart", cart);
        for (Cart cart2 : AllCart) {
            if (cart.getId_user().equals(cart2.getId_user()) && cart.getId_product().equals(cart2.getId_product())) {
                duplicate = true;
            }
        }
        if (!duplicate) {
            cartRepository.save(cart);
        }
        return "redirect:/";
    }

    @GetMapping("/remove/{id}")
    public String deleteProduct(@PathVariable Integer id, Model model) {
        cartRepository.deleteById(id);
        return "redirect:/cart";
    }

    @GetMapping("/flash_sale/{id}")
    public String sale(@PathVariable Integer id, Model model) {
        User userActv = userRepository.findById(idUserActv)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product Id" + id));
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product Id" + id));
        model.addAttribute("userActv", userActv);
        model.addAttribute("product", product);

        return "flash_sale";
    }

    @GetMapping("/update_user/{id}")
    public String updateUser(@PathVariable Integer id, Model model) {
    
        if (loginUserRepository.findAll().isEmpty()) {
            return "redirect:/login";
        }
        User userActv = userRepository.findById(idUserActv)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product Id" + id));

        model.addAttribute("userActv", userActv);
        return "update_user";
    }

    @PostMapping("save_update_user")
    public String saveUpdateUser(@ModelAttribute("product") User user, Model model) {
        User userUpdate = new User();
        userUpdate.setId(idUserActv);
        userUpdate.setUsername(user.getUsername());
        userUpdate.setPassword(user.getPassword());
        userUpdate.setAddress(user.getAddress());
        userRepository.save(user);
        return "redirect:/";
    }
}
