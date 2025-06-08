package com.campuscart.app.campus_cart.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // table name in the database
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    private String location;

}
