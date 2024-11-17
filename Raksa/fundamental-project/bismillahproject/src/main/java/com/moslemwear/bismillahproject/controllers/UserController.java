package com.moslemwear.bismillahproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.moslemwear.bismillahproject.models.Admin;
import com.moslemwear.bismillahproject.models.History;
import com.moslemwear.bismillahproject.models.Item;
import com.moslemwear.bismillahproject.models.User;
import com.moslemwear.bismillahproject.models.UserLogin;
import com.moslemwear.bismillahproject.repositories.HistoryRepository;
import com.moslemwear.bismillahproject.repositories.ItemRepository;
import com.moslemwear.bismillahproject.repositories.UserLoginRepository;
import com.moslemwear.bismillahproject.repositories.UserRepository;
import com.moslemwear.bismillahproject.repositories.VarietyRepository;

@Controller
public class UserController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private VarietyRepository varietyRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @GetMapping("/regist-user")
    public String userRegist(Model model) {
        User regist = new User();
        model.addAttribute("regist", regist);
        return "regist-user";
    }

    @PostMapping("/save-regist")
    public String isRegist(@ModelAttribute("regist") User userRegist) {
        Boolean isExisting = false;
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(userRegist.getUsername())) {
                isExisting = true;
            }
        }
        if (isExisting == true) {
            // User is Existing
            return "redirect:/login-user";
        } else {
            userRepository.save(userRegist);
            return "redirect:/login-user";
        }
    }

    @GetMapping("/login-user")
    public String loginUser(Model model, UserLogin userLogin) {
        model.addAttribute("userLogin", userLogin);
        return "logining";
    }

    @PostMapping("/is-login")
    public String isLoginUser(@ModelAttribute("userLogin") UserLogin userLogin) {

        List<User> users = userRepository.findAll();
        boolean isLogin = false;
        for (User user : users) {
            if (user.getUsername().equals(userLogin.getUsername())
                    && user.getPassword().equals(userLogin.getPassword())) {
                isLogin = true;
                // Set supaya id nya sama
                userLogin.setId(user.getId());
                break;
            }
        }

        if (isLogin) {
            userLoginRepository.deleteAll();
            userLoginRepository.save(userLogin);
            return "redirect:/home-user";
        } else {
            return "redirect:/login-user";
        }
    }

    @GetMapping("/home-user")
    public String addVariety(Model model) {
        if (userLoginRepository.findAll().isEmpty()) {
            UserLogin userLogin = new UserLogin();
            model.addAttribute("userLogin", userLogin);
            return "redirect:/login-user";
        }
        model.addAttribute("items", itemRepository.findAll());
        return "/home-user";
    }

    @GetMapping("/user-detail")
    public String showDetail(Model model) {
        if (userLoginRepository.findAll().isEmpty()) {
            UserLogin userLogin = new UserLogin();
            model.addAttribute("userLogin", userLogin);
            return "redirect:/login-user";
        }
        return "/user-detail";
    }

    @GetMapping("/user-money")
    public String showMoney(Model model) {
        if (userLoginRepository.findAll().isEmpty()) {
            UserLogin userLogin = new UserLogin();
            model.addAttribute("userLogin", userLogin);
            return "redirect:/login-user";
        }
        List<UserLogin> usersLogin = userLoginRepository.findAll();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            for (UserLogin userLogin : usersLogin) {
                if (user.getUsername().equals(userLogin.getUsername())) {
                    model.addAttribute("user", user);
                    break;
                }
            }
        }
        return "/user-money";
    }

    @PostMapping("/save-money")
    public String saveMoney(@RequestParam(name = "money") Integer money, Model model) {
        List<UserLogin> usersLogin = userLoginRepository.findAll();
        Integer moneyTotal;
        List<User> users = userRepository.findAll();
        for (User user : users) {
            for (UserLogin userLogin : usersLogin) {
                if (user.getUsername().equals(userLogin.getUsername())) {
                    if (user.getMoney() != null) {
                        moneyTotal = user.getMoney() + money;
                        user.setMoney(moneyTotal);
                    } else {
                        user.setMoney(money);
                    }
                    userRepository.save(user);
                    model.addAttribute("user", user);
                }
            }
        }
        return "redirect:/user-money";
    }

    @GetMapping("/transaction/{id}")
    public String transaction(@PathVariable("id") Integer id, Model model) {
        List<UserLogin> usersLogin = userLoginRepository.findAll();
        List<User> users = userRepository.findAll();

        if (userLoginRepository.findAll().isEmpty()) {
            UserLogin userLogin = new UserLogin();
            model.addAttribute("userLogin", userLogin);
            return "redirect:/login-user";
        }
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Item Id:" + id));

        for (User user : users) {
            for (UserLogin userLogin : usersLogin) {
                if (user.getUsername().equals(userLogin.getUsername())) {
                    model.addAttribute("user", user);
                    break;
                }
            }
        }
        model.addAttribute("item", item);
        model.addAttribute("variety", varietyRepository.findAll());
        return "/transaction";
    }

    @PostMapping("/final-transaction")
    public String finalTransaction(@RequestParam(name = "pay") Integer qty, @RequestParam(name = "id") Integer id,
            Model model, History history) {
        Integer change = 0;
        Integer itemPrice = 0;
        Integer itemStock = 0;
        Integer total = 0;

        List<UserLogin> usersLogin = userLoginRepository.findAll();
        List<User> users = userRepository.findAll();
        List<Item> items = itemRepository.findAll();

        for (Item item : items) {
            if (item.getId().equals(id)) {
                itemPrice = item.getPrice();
                total = qty * itemPrice;
                itemStock = item.getStock();
                break;
            }
        }

        for (User user : users) {
            for (UserLogin userLogin : usersLogin) {
                if (user.getUsername().equals(userLogin.getUsername())) {
                    change = user.getMoney();
                    if (user.getMoney() == null || user.getMoney() < total) {
                        model.addAttribute("message",
                                "Uang Anda Tidak Mencukupi, Silahkan Top Up Saldo Terlebih Dahulu");
                        return "redirect:/user-money";
                    } else if (itemStock < qty) {
                        model.addAttribute("message", "Stoknya Abis, Silahkan Komplen Ke Admin");
                        return "redirect:/user-detail";
                    } else if (user.getMoney() == total) {
                        change -= total;
                        itemStock = itemStock - qty;
                        model.addAttribute("message",
                                "Uang Anda Pas, Sampai Jumpa Kembali !!");
                        user.setMoney(change);

                    } else {
                        change -= total;
                        itemStock = itemStock - qty;
                        user.setMoney(change);
                    }

                    userRepository.save(user);
                    model.addAttribute("user", user);
                    history.setIdUser(user);
                    break;
                }
            }
        }

        for (Item item : items) {
            if (item.getId().equals(id)) {
                item.setStock(itemStock);
                itemRepository.save(item);
                model.addAttribute("item", item);
                history.setIdItem(item);
            }
        }

        history.setPayment(total);
        historyRepository.save(history);
        return "/notification";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        if (userLoginRepository.findAll().isEmpty()) {
            UserLogin userLogin = new UserLogin();
            model.addAttribute("userLogin", userLogin);
            return "redirect:/login-user";
        }
        userLoginRepository.deleteAll();
        return "redirect:/moslemwear.com";
    }
}
