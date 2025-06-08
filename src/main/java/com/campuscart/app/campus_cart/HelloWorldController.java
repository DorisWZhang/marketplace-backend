package com.campuscart.app.campus_cart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloWorldController {
    
    @GetMapping("/")
    public void print() {
        System.out.println("Hello World");
    }
    
}
