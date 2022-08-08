package com.example.b_team_shopping_mall.repository;

import com.example.b_team_shopping_mall.entity.Cart;
import com.example.b_team_shopping_mall.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
   //유저의 모든 물품 조회
    Cart findAllByRegister(Register register);
    //유저의 모든 물품 삭제
    Cart deleteAllByRegister(Register regitser);
    // 유저의 특정물품 삭제
    Cart deleteByRegisterAndObject(Register register, Object object);
}
