package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.Bill;
import com.fruits.congtyhoaqua.models.Cart;
import com.fruits.congtyhoaqua.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
@Repository

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUser(User user);
    Set<Cart> findAllByCreatedAtBetween(LocalDate start, LocalDate end);
}
