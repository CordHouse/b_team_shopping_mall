package com.example.b_team_shopping_mall.dto.Category;

import com.example.b_team_shopping_mall.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CategoryCreateResponseDto {
    private Long id;
    private String category;

    public CategoryCreateResponseDto toDto(Category category) {
        return new CategoryCreateResponseDto(category.getId(), category.getCategory());
    }
}
