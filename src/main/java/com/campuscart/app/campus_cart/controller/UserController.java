package com.campuscart.app.campus_cart.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import com.campuscart.app.campus_cart.model.User;
import com.campuscart.app.campus_cart.repository.UserRepository;

import java.util.List;




@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/createuser")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            e.printStackTrace(); // Log to console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Failed to create user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        System.out.println(loginRequest);
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        User user = userRepository.findByEmail(email);

        if (user != null) {
            if (password.equals(user.getPassword())) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }


    @GetMapping("/{email}")
    public User getUser(@PathVariable String email) {
        return userRepository.findByEmail(email);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully.");
    }
    
}
