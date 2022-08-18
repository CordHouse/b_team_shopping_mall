package com.example.b_team_shopping_mall.dto.Category;

import com.example.b_team_shopping_mall.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

// 품목별 조회
public class CategoryGetResponseDto {
    private String item;
    private String category;
    private int price;

    public CategoryGetResponseDto toDto(Category category) {
        return new CategoryGetResponseDto(category.getItem(), category.getCategory(), category.getPrice());
    }
}
