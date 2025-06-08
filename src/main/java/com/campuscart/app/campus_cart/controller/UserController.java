package com.campuscart.app.campus_cart.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.campuscart.app.campus_cart.model.User;
import com.campuscart.app.campus_cart.repository.UserRepository;

import java.util.List;




@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    @PostMapping("/createuser")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully.");
    }
    
    

}
