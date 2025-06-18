package com.campuscart.app.campus_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campuscart.app.campus_cart.model.Favourite;
import java.util.List;
import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    List<Favourite> findByUserId(Long userId);
    Optional<Favourite> findByUserIdAndItemId(Long userId, Long itemId);
    void deleteByUserIdAndItemId(Long userId, Long itemId);
}
