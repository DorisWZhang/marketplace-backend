package com.campuscart.app.campus_cart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.campuscart.app.campus_cart.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.campuscart.app.campus_cart.model.Message;
import com.campuscart.app.campus_cart.model.User;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    
}
