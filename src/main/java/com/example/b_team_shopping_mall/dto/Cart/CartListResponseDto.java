package com.example.b_team_shopping_mall.dto.Cart;

import com.example.b_team_shopping_mall.entity.Cart;
import com.example.b_team_shopping_mall.entity.Register;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartListResponseDto {
    private String item; //이름변경
    private int count;
    private int price;
    private String username;
    public CartListResponseDto toDto(Cart cart) {
        return new CartListResponseDto(cart.getItem(), cart.getCount(), cart.getPrice(), cart.getRegister().getUsername());
    }
}
