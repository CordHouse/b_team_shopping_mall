package com.example.b_team_shopping_mall.dto.Cart;

import com.example.b_team_shopping_mall.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartListResponseDto {
    private String Object;
    private int count;
    private int price;
    private String user;

    public CartListResponseDto toDto(Cart cart  ) {
        return new CartListResponseDto(cart.getObject(), cart.getCount(),cart.getPrice(), cart.getRegister().getMembername());
    }
}
