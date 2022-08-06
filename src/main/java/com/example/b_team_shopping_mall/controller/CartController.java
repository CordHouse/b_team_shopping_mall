package com.example.b_team_shopping_mall.controller;

import com.example.b_team_shopping_mall.entity.Cart;
import com.example.b_team_shopping_mall.response.Response;
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
    @ResponseStatus(HttpStatus.OK)
    public Response findAll(){
        return  Response.success(cartService.FindAllCart());
    }

    //장바구니 물품들 모두 구매.
    @PostMapping("/api/carts")
    @ResponseStatus(HttpStatus.OK)
    public Response writeBoard(@RequestBody Cart cart){
        return Response.success(cartService.BuyObj(cart));
    }

    //장바구니 물품 삭제
    @DeleteMapping("/api/carts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteBoard(@PathVariable Long id){
        cartService.DeleteCartObj(id);
        return Response.success("물품 삭제 완료");
    }

    //장바구니 물품 전체 삭제
    @DeleteMapping("/api/carts")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteAllBoard(){
        cartService.DeleteAllCartObj();
        return Response.success("모든 물품이 장바구니에서 삭제되었습니다.");
    }
}