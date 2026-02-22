package com.project.hirehive.service;

import com.project.hirehive.model.User;
import com.project.hirehive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// @Service
// public class UserService {

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     public void saveUser(User user) {
//         // user.setPassword(passwordEncoder.encode(user.getPassword()));
//         userRepository.save(user);
//     }

//     // ✅ FULLY IMPLEMENTED METHOD
//     public User findByEmail(String email) {
//         return userRepository.findByEmail(email)
//                 .orElse(null);   // return null if not found
//     }
// }

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}