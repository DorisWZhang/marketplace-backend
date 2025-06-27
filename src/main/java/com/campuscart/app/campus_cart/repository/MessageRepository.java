package com.campuscart.app.campus_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campuscart.app.campus_cart.model.Message;
import com.campuscart.app.campus_cart.model.User;
import java.util.List;

// interface extends JpaRepository, which provides methods for CRUD operations on Message entities.
public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findBySenderAndReceiver(User sender, User receiver);
    
    List<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByTimestampAsc(
        Long senderId, Long receiverId, Long receiverId2, Long senderId2
    );
    
}
