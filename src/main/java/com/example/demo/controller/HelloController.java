package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://127.0.0.1:5173")  // Optional, if you want controller-specific CORS

@RestController
public class HelloController {

	@GetMapping("/")
	public String home() {
		return "Greetings from Spring Boot!";
	}

}