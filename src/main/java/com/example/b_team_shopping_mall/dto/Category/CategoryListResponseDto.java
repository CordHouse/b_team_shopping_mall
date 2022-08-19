package com.example.b_team_shopping_mall.dto.Category;

import com.example.b_team_shopping_mall.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

//품목 전체 조회
public class CategoryListResponseDto {
    private String item;
    private String category;
    private int price;

    public CategoryListResponseDto toDto(Category category) {
        return new CategoryListResponseDto(category.getItem(), category.getCategory(), category.getPrice());
    }
}
