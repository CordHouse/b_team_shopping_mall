package com.example.b_team_shopping_mall.service;

import com.example.b_team_shopping_mall.entity.Cart;
import com.example.b_team_shopping_mall.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    //전체조회
    @Transactional(readOnly = true)
    public List<Cart> FindAllCart(){
        List<Cart> carts =  cartRepository.findAll();

        return carts;
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
