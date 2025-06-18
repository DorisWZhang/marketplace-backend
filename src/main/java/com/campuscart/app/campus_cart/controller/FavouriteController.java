package com.campuscart.app.campus_cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.campuscart.app.campus_cart.model.Favourite;
import com.campuscart.app.campus_cart.repository.FavouriteRepository;   



@RestController
@RequestMapping("/favourites")
public class FavouriteController {


    private FavouriteRepository favouriteRepository;
    
    public FavouriteController(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> addFavourite(@RequestBody Favourite favourite) {
        try {
            System.out.println("Adding favourite: " + favourite);
            Favourite savedFavourite = favouriteRepository.save(favourite);
            return ResponseEntity.ok(savedFavourite);
        } catch (Exception e) {
            e.printStackTrace(); // Log to console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to add favourite: " + e.getMessage());
        }
    }

    @GetMapping("/getfavourites/{userId}")
    public ResponseEntity<List<Favourite>> getFavouritesByUser(@PathVariable Long userId) {
        try {
            List<Favourite> favourites = favouriteRepository.findByUserId(userId);
            return ResponseEntity.ok(favourites);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null);
        }
    }

    @DeleteMapping("/remove/{userId}/{itemId}")
    public ResponseEntity<?> removeFavourite(@PathVariable Long userId, @PathVariable Long itemId) {
        try {
            Optional<Favourite> favourite = favouriteRepository.findByUserIdAndItemId(userId, itemId);
            if (favourite.isPresent()) {
                favouriteRepository.deleteByUserIdAndItemId(userId, itemId);
                return ResponseEntity.ok("Favourite removed successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Favourite not found for user: " + userId + " and item: " + itemId); 
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log to console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to remove favourite: " + e.getMessage());
        }
    }
    
    
}
