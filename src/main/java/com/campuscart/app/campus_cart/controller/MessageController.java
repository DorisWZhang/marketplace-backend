package com.campuscart.app.campus_cart.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.campuscart.app.campus_cart.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.campuscart.app.campus_cart.model.Message;
import com.campuscart.app.campus_cart.model.User;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        try {
            Message savedMessage = messageRepository.save(message);
            return ResponseEntity.ok(savedMessage);
        } catch (Exception e) {
            e.printStackTrace(); // Log to console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to send message: " + e.getMessage());
        }
    }

    @GetMapping("/conversation/{user1Id}/{user2Id}")
    public List<Message> getConversation(@PathVariable Long user1Id, @PathVariable Long user2Id) {
        return messageRepository.findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByTimestampAsc(
            user1Id, user2Id, user1Id, user2Id
        );
    }

    @GetMapping("/getLatestMessages/{userId}")
    public ResponseEntity<?> getLatestMessages(@PathVariable Long userId) {
        try {
            List<Message> latestMessages = messageRepository.findAll();
            if (latestMessages.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No messages found for user: " + userId);
            }
            return ResponseEntity.ok(latestMessages);
        } catch (Exception e) {
            e.printStackTrace(); // Log to console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to retrieve latest messages: " + e.getMessage());
        }
    }

    @GetMapping("/getLatestMessage/{senderId}/{receiverId}")
    public ResponseEntity<?> getLatestMessage(@PathVariable Long senderId, @PathVariable Long receiverId) {
        try {
            Message latestMessage = messageRepository.findTopBySenderIdAndReceiverIdOrderByTimestampDesc(senderId, receiverId);
            if (latestMessage != null) {
                return ResponseEntity.ok(latestMessage);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No messages found between the users.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log to console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to retrieve latest message: " + e.getMessage());
        }   
    }
    
}
