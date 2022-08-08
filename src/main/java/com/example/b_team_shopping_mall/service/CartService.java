package com.example.b_team_shopping_mall.service;

import com.example.b_team_shopping_mall.dto.Cart.*;
import com.example.b_team_shopping_mall.entity.Cart;
import com.example.b_team_shopping_mall.entity.Register;
import com.example.b_team_shopping_mall.exception.CartNotFoundException;
import com.example.b_team_shopping_mall.repository.CartRepository;
import com.example.b_team_shopping_mall.repository.RegisterRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final RegisterRepository registerRepository;

    //유저가 가진 품목 전체조회
    @Transactional(readOnly = true)
    public List<CartListResponseDto> FindAllCart(String memberid){
        Register register = registerRepository.findBymemberid(memberid);//유저찾기
        Cart cart = cartRepository.findAllByRegister(register);//찾은 유저의 품목 전체 조회하기
        List<CartListResponseDto> cartListResponseDtos = new LinkedList<>(); //Dto리스트 만들기
        cart.forEach(i-> cartListResponseDtos.add(new CartListResponseDto().toDto(i))); //Dto리스트에 품목 리스트 넣기
       return cartListResponseDtos; //반환하기
    }

    //유저가 담은 품목 전체 구매
    @Transactional
    public List<CartBuyResponseDto> BuyObj(String memberid){
        Register register = registerRepository.findBymemberid(memberid);//유저찾기
        List<Cart> cart = cartRepository.findAllByRegister(register);//찾은 유저의 품목 전체 조회하기
        List<CartBuyResponseDto> cartBuyResponseDtos = new LinkedList<>();
        cart.forEach(i-> cartBuyResponseDtos.add(new CartBuyResponseDto().toDto(i)));
        return cartBuyResponseDtos;
    }

    //유저의 물품 중 선택한 물품 전체삭제
    @Transactional
    public void DeleteCartObj(String memberid, String object){
        Register register = registerRepository.findBymemberid(memberid);//유저찾기
        cartRepository.deleteByRegisterAndObject(register,object);
    }

    //전체삭제
    @Transactional
    public void DeleteAllCartObj(String memberid){
        Register register = registerRepository.findBymemberid(memberid);//유저찾기
        cartRepository.deleteAllByRegister(register);
    }
}
