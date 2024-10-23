package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

// import org.hibernate.engine.jdbc.env.internal.LobCreationLogging_.logger; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")


public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        // Your logic remains unchanged
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

@PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            // Attempt to login the user
            User loginUser = userService.login(loginRequestDTO);
            // Return the user details with HTTP 200 OK status
            return new ResponseEntity<>(loginUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Handle authentication errors
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}


