package com.example.dpms_research_sample;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("")
    public String viewHomePage() {
        return "Home";
    }
    @GetMapping("/SignUp")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "SignUp";
    }
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "SignUp_Success";
    }
    @GetMapping("/LoginSuccess")
    public String Welcomepage(Model model) {
        List<User> listUsers = userRepo.findAll();
       model.addAttribute("listUsers", listUsers);

        return "Welcome";
    }

}
