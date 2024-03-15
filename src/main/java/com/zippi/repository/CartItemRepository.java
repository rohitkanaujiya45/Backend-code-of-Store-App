package com.zippi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zippi.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
