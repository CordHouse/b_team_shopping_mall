package com.example.b_team_shopping_mall.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class ProductCreateRequestDto {

    @NotNull(message = "상품의 이름을 입력해주세요")
    private String item;

    @NotNull(message = "상품의 가격을 입력해주세요")
    private Long price;

    private String category;
}
