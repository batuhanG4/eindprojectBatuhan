package com.batuhan.eindprojectBatuhan.service;

import com.batuhan.eindprojectBatuhan.model.User;
import com.batuhan.eindprojectBatuhan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void loginUser(String email, String rawPassword) {
        // Niet meer nodig; authenticatie wordt door Spring Security afgehandeld.
    }

}
