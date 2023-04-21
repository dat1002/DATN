package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.Cart;
import com.fruits.congtyhoaqua.models.CartDetail;
import com.fruits.congtyhoaqua.models.id.CartDetailId;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, CartDetailId> {
    Set<CartDetail> findAllByCart(Cart cart);
}
