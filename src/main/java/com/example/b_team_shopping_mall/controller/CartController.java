package com.example.b_team_shopping_mall.controller;

import com.example.b_team_shopping_mall.dto.Cart.CartInputRequestDto;
import com.example.b_team_shopping_mall.response.Response;
import com.example.b_team_shopping_mall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class CartController {
    private final CartService cartService;
    //장바구니 전체 조회
    @GetMapping("/carts")
    @ResponseStatus(HttpStatus.OK)
    public Response findAllCart(){
        return  Response.success(cartService.findAllCart());
    }

    @PostMapping("/product/items")
    @ResponseStatus(HttpStatus.OK)
    public Response inputCart(@RequestBody @Valid CartInputRequestDto cartInputRequestDto){
        return Response.success(cartService.inputCart(cartInputRequestDto));
    }

    //장바구니 물품 삭제
    @DeleteMapping("/carts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteCartItem(@PathVariable Long id){
        cartService.deleteCartItem(id);
        return Response.success("물품 삭제 완료");
    }

    //장바구니 물품 전체 삭제
    @DeleteMapping("/carts")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteAllCartItem(){
        cartService.deleteAllCartItem();
        return Response.success("모든 물품이 장바구니에서 삭제되었습니다.");
    }
}