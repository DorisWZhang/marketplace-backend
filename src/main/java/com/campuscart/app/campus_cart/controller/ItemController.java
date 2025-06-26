package com.campuscart.app.campus_cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import com.campuscart.app.campus_cart.model.Item;
import com.campuscart.app.campus_cart.model.User;
import com.campuscart.app.campus_cart.repository.ItemRepository;
import com.campuscart.app.campus_cart.repository.UserRepository;
import com.cloudinary.http44.api.Response;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


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

    @PutMapping("/updateitem/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable Long id, @RequestBody Item item) {
        Item savedItem = itemRepository.findById(id).orElse(null);
        if (savedItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found."); 
        } else {
            savedItem.setTitle(item.getTitle());
            savedItem.setDescription(item.getDescription()); 
            savedItem.setPrice(item.getPrice());
            savedItem.setLocation(item.getLocation());  
            savedItem.setSold(item.isSold()); // Update the sold status
            savedItem.setImage(item.getImage()); // Update the image
            itemRepository.save(savedItem);
        }
        
        return ResponseEntity.ok(savedItem);
    }

    @DeleteMapping("/deleteitem/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            itemRepository.delete(item.get());
            return ResponseEntity.ok("Item deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        }
    }

    @GetMapping("/getitembyid/{id}")
    public ResponseEntity<?> getItemsByTitle(@PathVariable Long id) {
        try {
            Optional<Item> item = itemRepository.findById(id);
            if (item.isPresent()) {
                return ResponseEntity.ok(item);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null);
        }
    }

    @GetMapping("/getitembytitle/{title}")
    public ResponseEntity<List<Item>> getItemsByTitle(@PathVariable String title) {
        try {
            // return all items that contain the title
            System.out.println("Searching for items with title containing: " + title);
            List<Item> items = itemRepository.findByTitleContaining(title);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null);
        }
    }
    
    
}
