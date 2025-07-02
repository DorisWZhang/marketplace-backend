package com.campuscart.app.campus_cart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.DestinationVariable;

import com.campuscart.app.campus_cart.model.Message;
import com.campuscart.app.campus_cart.repository.MessageRepository;

import java.time.LocalDateTime;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // Enables in-memory message broker
        config.setApplicationDestinationPrefixes("/app"); // prefix for messages from client, this will map the message to a springmethod with annotation @MessageMapping
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // WebSocket endpoint for the frontend to connect to
                .setAllowedOriginPatterns("*") // Allows CORS from all origins (change in prod)
                .withSockJS(); // Fallback option for browsers that don't support WebSocket
    }


 }
