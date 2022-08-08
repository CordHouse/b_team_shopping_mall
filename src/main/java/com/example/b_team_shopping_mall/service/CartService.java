package com.example.b_team_shopping_mall.service;

import com.example.b_team_shopping_mall.dto.Cart.*;
import com.example.b_team_shopping_mall.entity.Cart;
import com.example.b_team_shopping_mall.entity.Register;
import com.example.b_team_shopping_mall.repository.CartRepository;
import com.example.b_team_shopping_mall.repository.RegisterRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final RegisterRepository registerRepository;
    //전체조회

    @Transactional(readOnly = true)
    public List<CartListResponseDto> FindAllCart(){
        return cartRepository.findAll().stream().map(s -> new CartListResponseDto().toDto(s)).collect(Collectors.toList());
    }

    //구매
    @Transactional
    public Cart BuyObj(Cart cart){
        return cartRepository.save(cart);
    }

    //삭제
    @Transactional
    public void DeleteCartObj(Long id){
        cartRepository.deleteById(id);
    }

    //전체삭제
    @Transactional
    public void DeleteAllCartObj(){
        cartRepository.deleteAll();
    }
}
