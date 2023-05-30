package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.Bill;
import com.fruits.congtyhoaqua.models.Cart;
import com.fruits.congtyhoaqua.models.CartDetail;
import com.fruits.congtyhoaqua.models.id.CartDetailId;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, CartDetailId> {

    @Query("select c from CartDetail c where c.cartDetailId.idCart = ?1 order by c.cartDetailId.idFruit")
    List<CartDetail> findAllByCart(Integer idCart);

}
