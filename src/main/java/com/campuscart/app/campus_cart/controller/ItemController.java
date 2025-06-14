package com.campuscart.app.campus_cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import com.campuscart.app.campus_cart.model.Item;
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
    
}
