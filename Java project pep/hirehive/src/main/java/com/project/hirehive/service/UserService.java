package com.project.hirehive.service;

import com.project.hirehive.model.User;
import com.project.hirehive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findByEmail(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }
}

