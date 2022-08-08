package com.example.b_team_shopping_mall.dto.Cart;

import com.example.b_team_shopping_mall.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartBuyResponseDto {
    private String Object;
    private int count;
    private int price;
    private String user;
    public CartBuyResponseDto toDto(Cart cart) {
        return new CartBuyResponseDto(cart.getObject(), cart.getCount(), cart.getPrice(),cart.getRegister().getMembername());
    }
}
