package com.project.hirehive.controller.api;

import com.project.hirehive.model.User;
import com.project.hirehive.repository.UserRepository;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthRestController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body("Wrong password");
        }

        return ResponseEntity.ok("Login Success");
    }
}
