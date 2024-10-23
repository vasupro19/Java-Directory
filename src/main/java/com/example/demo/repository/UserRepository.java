package com.example.demo.repository;
import java.util.Optional;


import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // You can define custom query methods here if needed
    Optional<User> findByEmail(String email);
}
