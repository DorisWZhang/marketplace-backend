package com.campuscart.app.campus_cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import com.campuscart.app.campus_cart.model.Item;
import com.campuscart.app.campus_cart.model.User;
import com.campuscart.app.campus_cart.repository.ItemRepository;
import com.campuscart.app.campus_cart.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostMapping("/createitem")
    public ResponseEntity<?> createItem(@RequestBody Item item) {
        try {
            System.out.println("Creating item: " + item);
            Item savedItem = itemRepository.save(item);
            return ResponseEntity.ok(savedItem);
        } catch (Exception e) {
            e.printStackTrace(); // Log to console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Failed to create item posting: " + e.getMessage());
        }
    }

    @GetMapping("/getitems")
    public ResponseEntity<List<Item>> getAllItems() {
        try {
            List<Item> items = itemRepository.findAll();
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null);
        }
    }

    @GetMapping("/getitemsbyseller/{sellerID}")
    public ResponseEntity<List<Item>> getItemsBySeller(@PathVariable Long sellerID) {
        try {
            List<Item> items = itemRepository.findBySellerID(sellerID);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null);
        }
    }

    
}
