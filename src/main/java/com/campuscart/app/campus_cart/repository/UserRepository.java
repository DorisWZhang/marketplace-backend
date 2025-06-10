
package com.campuscart.app.campus_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campuscart.app.campus_cart.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);


}
