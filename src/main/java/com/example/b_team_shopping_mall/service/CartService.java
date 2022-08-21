package com.example.b_team_shopping_mall.service;

import com.example.b_team_shopping_mall.dto.Cart.*;
import com.example.b_team_shopping_mall.entity.Cart;
import com.example.b_team_shopping_mall.entity.Product;
import com.example.b_team_shopping_mall.entity.Register;
import com.example.b_team_shopping_mall.exception.*;
import com.example.b_team_shopping_mall.repository.CartRepository;
import com.example.b_team_shopping_mall.repository.ProductRepository;
import com.example.b_team_shopping_mall.repository.RegisterRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final RegisterRepository registerRepository;

    private final ProductRepository productRepository;

    //유저가 가진 품목 전체조회
    @Transactional(readOnly = true)
    public List<CartListResponseDto> findAllCart(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Cart> cart = cartRepository.findAllByRegister(registerRepository.findByUsername(authentication.getName()).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 장바구니에 품목이 없습니다.");
        }));
        List<CartListResponseDto> cartListResponseDtos = new LinkedList<>();
        cart.forEach(i -> cartListResponseDtos.add(new CartListResponseDto().toDto(i)));
        if(cart.isEmpty())
            throw new CartNotFoundItemListException();
        return cartListResponseDtos; //반환하기
    }

    @Transactional
    public CartInputResponseDto inputCart(CartInputRequestDto cartInputRequestDto){
        Product product = productRepository.findByItem(cartInputRequestDto.getItem()).orElseThrow(() -> {
            throw new ProductNotFoundItemException();
        });
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Register register = registerRepository.findByUsername(authentication.getName()).orElseThrow(() -> {
            throw new RegisterNotFoundSearchUsernameException();
        });

        List<Cart> cartCheck = cartRepository.findByItem(cartInputRequestDto.getItem());

        if(cartCheck.isEmpty()) {
            Cart cart = Cart.builder()
                    .item(cartInputRequestDto.getItem())
                    .count(cartInputRequestDto.getCount())
                    .price(product.getPrice() * cartInputRequestDto.getCount())
                    .register(register)
                    .product(product)
                    .build();

            cartRepository.save(cart);

            return new CartInputResponseDto().toDto(product, cartInputRequestDto);
        }
        throw new CartOverlapException();
    }

    //유저의 물품 중 선택한 물품 전체삭제
    @Transactional
    public void deleteCartItem(Long id){
        if(cartRepository.findById(id).isPresent())
            cartRepository.deleteById(id);//유저찾기
        else
            throw new CartNotFoundItemException();
    }

    //전체삭제
    @Transactional
    public void deleteAllCartItem(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        cartRepository.deleteAllByRegister(registerRepository.findByUsername(authentication.getName()).orElseThrow(() -> {
            throw new IllegalArgumentException("장바구니에 품목리스트가 존재하지 않습니다.");
        }));//유저찾기
    }
}
