package com.example.demo.controller;



import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://127.0.0.1:5173",allowCredentials = "true")  // Optional, if you want controller-specific CORS

@RestController
public class CsrfController {

    @GetMapping("/csrf-token")
    public CsrfToken csrfToken(HttpServletRequest request, HttpServletResponse response) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        
        if (csrfToken != null) {
            // Set the SameSite attribute and other cookie attributes
            response.addHeader("Set-Cookie", "XSRF-TOKEN=" + csrfToken.getToken() + "; Path=/; SameSite=None; Secure; HttpOnly");
        }
        
        return csrfToken;
    }
}

