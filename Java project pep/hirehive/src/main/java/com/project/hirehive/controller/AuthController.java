package com.project.hirehive.controller;

import com.project.hirehive.model.User;
import com.project.hirehive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerPage() { return "register"; }

    @PostMapping("/register")
    public String registerUser(User user) {
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() { return "login"; }

    @GetMapping("/dashboard")
    public String dashboard() { return "dashboard"; }
}


