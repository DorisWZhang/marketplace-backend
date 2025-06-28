package com.campuscart.app.campus_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.campuscart.app.campus_cart.model.Message;
import com.campuscart.app.campus_cart.model.User;
import java.util.List;

// interface extends JpaRepository, which provides methods for CRUD operations on Message entities.
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByTimestampAsc(
        Long senderId, Long receiverId, Long receiverId2, Long senderId2
    );

    Message findTopBySenderIdAndReceiverIdOrderByTimestampDesc(Long senderId, Long receiverId);

    @Query("SELECT m FROM Message m WHERE (m.senderId = :userId OR m.receiverId = :userId) AND m.timestamp IN " +
       "(SELECT MAX(m2.timestamp) FROM Message m2 WHERE (m2.senderId = :userId OR m2.receiverId = :userId) GROUP BY " +
       "CASE WHEN m2.senderId = :userId THEN m2.receiverId ELSE m2.senderId END)")
    List<Message> findLatestConversations(@Param("userId") Long userId);
}
