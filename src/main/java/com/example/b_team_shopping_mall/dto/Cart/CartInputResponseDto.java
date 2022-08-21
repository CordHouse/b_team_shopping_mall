package com.example.b_team_shopping_mall.dto.Cart;

import com.example.b_team_shopping_mall.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartInputResponseDto {
    private String item;
    private int count;
    private int price;

    public CartInputResponseDto toDto(Product product, CartInputRequestDto cartInputRequestDto){
        return new CartInputResponseDto(product.getItem(), cartInputRequestDto.getCount(), product.getPrice() * cartInputRequestDto.getCount());
    }
}
