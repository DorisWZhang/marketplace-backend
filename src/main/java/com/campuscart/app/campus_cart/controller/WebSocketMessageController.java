package com.campuscart.app.campus_cart.controller;

import com.campuscart.app.campus_cart.model.Message;
import com.campuscart.app.campus_cart.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class WebSocketMessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")  // matches /app/chat.sendMessage
    public void sendMessage(Message message) {
        // save to DB
        message.setTimestamp(LocalDateTime.now());
        Message saved = messageRepository.save(message);

        // send to subscriber (receiver)
        String destination = "/topic/messages/" + message.getReceiverId();
        messagingTemplate.convertAndSend(destination, saved);
    }
}
 
