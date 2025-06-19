package com.campuscart.app.campus_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


import com.campuscart.app.campus_cart.model.Favourite;
import java.util.List;
import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    List<Favourite> findByUserId(Long userId);
    Optional<Favourite> findByUserIdAndItemId(Long userId, Long itemId);

    @Modifying
    @Transactional
    void deleteByUserIdAndItemId(Long userId, Long itemId);
}
