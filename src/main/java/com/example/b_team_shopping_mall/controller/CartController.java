package com.example.b_team_shopping_mall.controller;

import com.example.b_team_shopping_mall.response.Response;
import com.example.b_team_shopping_mall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CartController {
    private final CartService cartService;
    //장바구니 전체 조회
    @GetMapping("/api/carts")
    @ResponseStatus(HttpStatus.OK)
    public Response findAll(String memberid){
        return  Response.success(cartService.FindAllCart(memberid));
    }
//RequsetBody, PathVariable/??
    //장바구니 물품들 모두 구매.
    @PostMapping("/api/carts")
    @ResponseStatus(HttpStatus.OK)
    public Response writeBoard(String memberid){
        return Response.success(cartService.BuyObj(memberid));
    }

    //장바구니 물품 삭제
    @DeleteMapping("/api/carts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteBoard(String memberid, String object){
        cartService.DeleteCartObj(memberid, object);
        return Response.success("물품 삭제 완료");
    }

    //장바구니 물품 전체 삭제
    @DeleteMapping("/api/carts")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteAllBoard(String memberid){
        cartService.DeleteAllCartObj(memberid);
        return Response.success("모든 물품이 장바구니에서 삭제되었습니다.");
    }
}