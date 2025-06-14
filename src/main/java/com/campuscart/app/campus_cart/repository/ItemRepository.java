package com.campuscart.app.campus_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campuscart.app.campus_cart.model.Item;
import java.util.List;
import java.util.Optional;
public interface ItemRepository extends JpaRepository<Item, String> {
    
    // Method to find items by seller ID
    List<Item> findBySellerID(String sellerID);
    
    // Method to find items by title
    List<Item> findByTitleContaining(String title);
    
    // Method to find items by location
    List<Item> findByLocation(String location);
    
    // Method to find items that are not sold
    List<Item> findBySoldFalse();
    
    // Method to find items by ID
    Optional<Item> findById(String id);
    
}
