
package com.campuscart.app.campus_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campuscart.app.campus_cart.model.User;

//Spring Data repository interface that acts as an abstraction layer between your Java application and the actual database (like Postgres).

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);




}
