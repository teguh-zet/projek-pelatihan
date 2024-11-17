package com.moslemwear.bismillahproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.moslemwear.bismillahproject.repositories.HistoryRepository;
import com.moslemwear.bismillahproject.repositories.UserRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;

// import com.moslemwear.bismillahproject.models.History;
// import com.moslemwear.bismillahproject.models.User;
// import com.moslemwear.bismillahproject.repositories.HistoryRepository;
// import com.moslemwear.bismillahproject.repositories.UserRepository;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HistoryController {
    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/show-history")
    public String showHistory(Model model) {
        model.addAttribute("histories", historyRepository.findAll());
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(historyRepository.findAll());
        return "/history";
    }

}
