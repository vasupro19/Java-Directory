package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        // You might want to hash the password here before saving
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public User login(LoginRequestDTO loginRequestDTO) {
        String userEmail = loginRequestDTO.getEmail();
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);

        // Check if user is present
        if (optionalUser.isPresent()) {
            User foundUser = optionalUser.get();

            // Compare the hashed password
            if (BCrypt.checkpw(loginRequestDTO.getPassword(), foundUser.getPassword())) {
                return foundUser; // Authentication successful
            } else {
                throw new RuntimeException("Invalid password"); // Invalid password
            }
        } else {
            throw new RuntimeException("User not found"); // User not found
        }
    }
}
