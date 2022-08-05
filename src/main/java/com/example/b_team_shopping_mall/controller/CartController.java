package com.example.b_team_shopping_mall.controller;

import com.example.b_team_shopping_mall.entity.Cart;
import com.example.b_team_shopping_mall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CartController {
    private final CartService cartService;
    //장바구니 전체 조회
    @GetMapping("/api/carts")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(cartService.FindAllCart(), HttpStatus.OK);
    }

    //장바구니 물품들 모두 구매.
    @PostMapping("/api/carts")
    public ResponseEntity<?> writeBoard(@RequestBody Cart cart){
        return new ResponseEntity<>(cartService.BuyObj(cart), HttpStatus.OK);
    }

    //장바구니 물품 삭제
    @DeleteMapping("/api/carts/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id){
        cartService.DeleteCartObj(id);
        return new ResponseEntity<>("게시글 삭제 완료", HttpStatus.OK);
    }

    //장바구니 물품 전체 삭제
    @DeleteMapping("/api/carts")
    public ResponseEntity<?> deleteAllBoard(){
        cartService.DeleteAllCartObj();
        return new ResponseEntity<>("모든 물품이 장바구니에서 삭제되었습니다.", HttpStatus.OK);
    }
}