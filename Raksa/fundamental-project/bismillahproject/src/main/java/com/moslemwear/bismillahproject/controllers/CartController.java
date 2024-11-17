package com.moslemwear.bismillahproject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.moslemwear.bismillahproject.models.Cart;
import com.moslemwear.bismillahproject.models.Item;
import com.moslemwear.bismillahproject.models.User;
import com.moslemwear.bismillahproject.models.UserLogin;
import com.moslemwear.bismillahproject.models.Variety;
import com.moslemwear.bismillahproject.repositories.CartRepository;
import com.moslemwear.bismillahproject.repositories.ItemRepository;
import com.moslemwear.bismillahproject.repositories.UserLoginRepository;
import com.moslemwear.bismillahproject.repositories.UserRepository;
import com.moslemwear.bismillahproject.repositories.VarietyRepository;

@Controller
public class CartController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserLoginRepository userLoginRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    VarietyRepository varietyRepository;

    @GetMapping("/cart/{id}")
    public String showCart(@PathVariable("id") Integer id, Model model, Cart cartEuy) {
        if (userLoginRepository.findAll().isEmpty()) {
            UserLogin userLogin = new UserLogin();
            model.addAttribute("userLogin", userLogin);
            return "redirect:/login-user";
        }

        List<UserLogin> usersLogin = userLoginRepository.findAll();
        List<User> users = userRepository.findAll();
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));

        Cart cart = new Cart();
        for (User user : users) {
            for (UserLogin userLogin : usersLogin) {
                if (user.getUsername().equals(userLogin.getUsername())) {
                    cart.setIdUser(user);
                    break;
                }
            }
        }

        // System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        // System.out.println(carts);

        // Cart cartActive = cartRepository.findById(id)
        // .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
        // model.addAttribute("cart", cartActive);
        // System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        // System.out.println(cartActive);

        // model.addAttribute("carts", carts);

        return "redirect:/home-user";
    }

    @GetMapping("/show-cart")
    public String showSortCart(Model model) {
        List<User> users = userRepository.findAll();
        List<UserLogin> userLogins = userLoginRepository.findAll();
        UserLogin userLogin = userLogins.get(0);
        System.out.println(userLogin.getId());
        List<Cart> carts = new ArrayList<>();
        for (Cart cart : cartRepository.findAll()) {
            if (cart.getIdUser().getId().equals(userLogin.getId())) {
                System.out.println("-----------------------"+cart.getIdUser().getId());
                carts.add(cart);
            }
        }
        model.addAttribute("carts",carts);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        // System.out.println(carts);
        return "show-cart";
    }

}
