package com.project.hirehive.controller.api;

import com.project.hirehive.model.User;
import com.project.hirehive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("Registered Successfully");
    }

}

