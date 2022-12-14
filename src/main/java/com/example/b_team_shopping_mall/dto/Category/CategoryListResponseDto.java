package com.example.b_team_shopping_mall.dto.Category;

import com.example.b_team_shopping_mall.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryListResponseDto {

    private String category;

    public CategoryListResponseDto toDto(Category category) {
        return new CategoryListResponseDto(category.getCategory());
    }
}
