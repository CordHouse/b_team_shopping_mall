package com.example.b_team_shopping_mall.dto.Product;

import com.example.b_team_shopping_mall.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductListDto {
    private Long id;

    private String item;

    private Long price;

    private String name;

    private String category;

    public ProductListDto toDto(Product product) {
        return new ProductListDto(product.getId(), product.getItem(), product.getPrice(), product.getRegister().getName(), product.getCategory().getCategory());
    }
}
