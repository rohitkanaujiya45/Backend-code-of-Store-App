package com.zippi.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zippi.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
