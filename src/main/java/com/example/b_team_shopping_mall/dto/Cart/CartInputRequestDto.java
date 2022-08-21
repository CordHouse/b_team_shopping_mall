package com.example.b_team_shopping_mall.dto.Cart;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartInputRequestDto {
    @NotBlank(message = "구매하실 품목을 입력해주세요.")
    private String item;
    @NotNull(message = "구매하실 품목의 개수를 입력해주세요.")
    private int count;
}
