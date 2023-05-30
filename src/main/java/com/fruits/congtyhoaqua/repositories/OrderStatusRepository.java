package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.Knowledge;
import com.fruits.congtyhoaqua.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
}
