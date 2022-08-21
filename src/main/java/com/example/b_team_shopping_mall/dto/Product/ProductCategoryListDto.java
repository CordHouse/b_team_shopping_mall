package com.example.b_team_shopping_mall.dto.Product;

import com.example.b_team_shopping_mall.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCategoryListDto {
    private String item;

    private Long price;

    private String name;

    public ProductCategoryListDto toDto(Product product) {
        return new ProductCategoryListDto(product.getItem(), product.getPrice(), product.getRegister().getName());
    }
}