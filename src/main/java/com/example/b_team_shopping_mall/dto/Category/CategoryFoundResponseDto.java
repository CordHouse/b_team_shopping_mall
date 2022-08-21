package com.example.b_team_shopping_mall.dto.Category;

import com.example.b_team_shopping_mall.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryFoundResponseDto {
    private String category;

    public CategoryFoundResponseDto toDto(Category category) {
        return new CategoryFoundResponseDto(category.getCategory());
    }
}
