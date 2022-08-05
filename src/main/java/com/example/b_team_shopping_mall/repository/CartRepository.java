package com.example.b_team_shopping_mall.repository;

import com.example.b_team_shopping_mall.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
