package com.campuscart.app.campus_cart.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "favourites")
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long itemId;
    
    public Favourite() {
    }

    public Favourite(Long userId, Long itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId(){
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }


    
}
