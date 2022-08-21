package com.example.b_team_shopping_mall.dto.Category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryCreateRequestDto {

    @NotBlank(message = "카테고리를 입력해주세요")
    private String category;
}
